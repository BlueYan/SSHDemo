package com.mark.project.pss.web.action;

import com.mark.project.pss.domain.Employee;
import com.opensymphony.xwork2.ActionContext;
import lombok.Setter;

/**
 * Created by Mark_Yan on 2017/8/15.
 */
public class LoginAction extends BaseAction {

	@Setter
	private String username;
	@Setter
	private String password;

	//执行登录
	@Override
	public String execute() throws Exception {
		Employee user = employeeService.queryForLogin(username, password);
		if ( user == null ) {
			//表示用户不存在
			addActionError("用户账号或者密码有误");
			return "login";
		}
		//将数据放入到session中
		ActionContext.getContext().getSession().put("USER_IN_SESSION", user);
		//查询出用户对应的权限
		ActionContext.getContext().getSession().put("PERMISSION_IN_SESSION", employeeService.queryForPermission(user));
		return SUCCESS;
	}
}
