package com.mark.web.action;

import com.mark.domain.User;
import com.mark.service.IUserService;
import com.mysql.jdbc.jdbc2.optional.SuspendableXAConnection;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/9.
 */
public class UserAction extends ActionSupport {

	//自动注入
	@Autowired
	private IUserService userService;

	@Override
	public String execute() throws Exception {
		List<User> users = userService.list();
		System.out.println(users);
		return SUCCESS;
	}
}
