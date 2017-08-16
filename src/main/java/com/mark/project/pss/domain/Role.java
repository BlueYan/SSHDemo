package com.mark.project.pss.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/14.
 * 角色实体类
 */
@Getter
@Setter
@ToString(exclude = "permissions")
public class Role {

	private Long id;

	private String name; //角色名字

	private String sn; //角色编号

	//角色 ：权限 = N : N的关系
	private List<Permission> permissions = new ArrayList<Permission>();
}
