package com.mark.project.pss.web.interceptor;

import com.mark.project.pss.domain.Employee;
import com.mark.project.pss.domain.Permission;
import com.mark.project.pss.util.CommonUtil;
import com.mark.project.pss.util.RequiredPermission;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by Mark_Yan on 2017/8/16.
 * 权限拦截器
 */
public class PermissionInterceptor extends AbstractInterceptor {
	/**
	 * Override to handle interception
	 *
	 * @param invocation
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		//获取当前用户
		Employee user = (Employee) invocation.getInvocationContext().getSession().get("USER_IN_SESSION");
		if ( user == null ) {
			//如果用户不存在 返回登录页面
			return "login";
		}
		if ( user.getAdmin() ) {
			//用户是超级管理员 直接放行
			return invocation.invoke();
		}
		//获取当前调用的方法名字
		String methodName = invocation.getProxy().getMethod();
		//获取当前的action类
		Object action = invocation.getProxy().getAction();
		//获取到当前的方法
		Method method = action.getClass().getMethod(methodName);
		RequiredPermission requiredPermission = method.getAnnotation(RequiredPermission.class);
		if ( requiredPermission == null ) {
			//表示当前方法并没有贴上requiredPermission的标签 不受权限控制
			return invocation.invoke();
		}
		//将当前用户所拥有的权限和当前方法上的权限进行匹配
		Set<Permission> permissions = (Set<Permission>) invocation.getInvocationContext().getSession().get("PERMISSION_IN_SESSION");
		if ( permissions.size() == 0 ) {
			//表示用户没有权限
			return "no_permission";
		}
		for (Permission permission : permissions ) {
			if ( permission.getExpression().equals(
					CommonUtil.buildExpression(requiredPermission.getClass().getDeclaredMethod(methodName)))) {
				return invocation.invoke();
			}
		}
		return "no_permission";
	}
}
