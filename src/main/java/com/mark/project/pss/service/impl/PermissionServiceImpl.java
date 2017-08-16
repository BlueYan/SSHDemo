package com.mark.project.pss.service.impl;

import com.mark.project.pss.dao.IPermissionDAO;
import com.mark.project.pss.dao.IRoleDAO;
import com.mark.project.pss.domain.Permission;
import com.mark.project.pss.domain.Role;
import com.mark.project.pss.page.PageResult;
import com.mark.project.pss.query.PermissionQueryObject;
import com.mark.project.pss.service.IPermissionService;
import com.mark.project.pss.util.CommonUtil;
import com.mark.project.pss.util.RequiredPermission;
import com.mark.project.pss.web.action.BaseAction;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Mark_Yan on 2017/8/14.
 */
public class PermissionServiceImpl implements IPermissionService, ApplicationContextAware{

	@Setter
	private IPermissionDAO permissionDAO;

	@Setter
	private IRoleDAO roleDAO;

	//想要自动注入 需要实现ApplicationContextAware接口
	@Autowired
	private ApplicationContext ctx;

	public void save(Permission permission) {
		permissionDAO.save(permission);
	}

	/**
	 * TODO：hibernate配置文件设置了cascade="all-delete-orphan"但是在做级联操作，会出现外键约束错误
	 *
	 * 删除权限
	 * 由于权限和角色之间是有关联的。多对多的关系。
	 * 所以当我们在删除权限的时候，有一些角色会拥有你当前删除的权限。
	 * 解决的办法：
	 * 先从中间表破坏两者的关系，然后在删除权限，最后去更新角色的权限
	 * @param permission
	 */
	public void delete(Permission permission) {
		//先查询出当前的权限下的角色
		List<Role> roles = permissionDAO.queryRoleByPId(permission);
		for ( Role role : roles ) {
			//取出当前的角色的所有权限
			List<Permission> permissions = role.getPermissions();
			Iterator<Permission> iterator = permissions.iterator();
			while(iterator.hasNext()) {
				//取出当前的权限
				Permission p = iterator.next();
				/**
				 * 这里要修改对象P 也就是我们从数据库查询得到的role(持久化状态)
				 * 中的Permission对象P(也是持久化状态)修改成游离状态.
				 * 然后我们要删除的permission对象是跟当前p对象有着同样的OID，可能内存地址不一致。
				 */
				permissionDAO.evict(p);
				if ( p.getId().equals(permission.getId()) ) {
					iterator.remove(); //删除当前的权限 也就是从角色中所拥有的权限
				}
			}
			roleDAO.update(role); //再更新当前角色 把之前删除的权限在数据库中重新更新一次
		}
		permissionDAO.delete(permission);
	}

	public PageResult<Permission> pageQuery(PermissionQueryObject pqo) {
		return permissionDAO.pageQuery(pqo);
	}

	public List<Permission> list() {
		return permissionDAO.list();
	}

	public void reload() {
		Map<String, BaseAction> actionMap = ctx.getBeansOfType(BaseAction.class);
		for (BaseAction action : actionMap.values()) { //循环取出每一个Action
			//取出每个action的所有方法
			Method[] methods = action.getClass().getDeclaredMethods();
			for ( Method method : methods ) {
				//取出当前方法是否有RequiredPermission的注解
				RequiredPermission permission = method.getAnnotation(RequiredPermission.class);
				if ( permission != null ) {
					Permission p = new Permission();
					p.setName(permission.value());
					p.setExpression(CommonUtil.buildExpression(method));
					//再将其保存起来
					permissionDAO.save(p);
				}
			}
		}
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ctx = applicationContext;
	}
}
