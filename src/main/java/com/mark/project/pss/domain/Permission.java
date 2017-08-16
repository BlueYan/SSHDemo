package com.mark.project.pss.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Mark_Yan on 2017/8/14.
 * 权限表
 */
@Setter
@Getter
@ToString
public class Permission {

	private Long id;

	private String name; //权限名称

	private String expression; //权限表达式

}
