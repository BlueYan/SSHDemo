package com.mark.project.pss.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Created by Mark_Yan on 2017/8/16.
 *
 * 登录拦截器
 * 需要在struts.xml文件中配置
 * */
public class LoginInterceptor extends AbstractInterceptor {

	/**
	 * Override to handle interception
	 *
	 * @param invocation
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		//判断是否有session的存在，有的话就放行，没有的话就跳转到登录页面
		boolean key = invocation.getInvocationContext().getSession().containsKey("USER_IN_SESSION");
		if ( key ) {
			invocation.invoke();
		}
		return "login"; //返回登录页面 这是要在struts.xml文件中设置一个全局的result结果用于接收
	}
}
