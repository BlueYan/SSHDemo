package com.mark.project.pss.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Mark_Yan on 2017/8/14.
 * 权限注解标签
 * 贴在方法上表示该方法需要用权限管理
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredPermission {
	String value();
}
