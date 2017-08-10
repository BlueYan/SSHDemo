package com.mark.project.pss.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by Mark_Yan on 2017/8/10.
 */
@Setter
@Getter
public class Employee {

	private Long id;

	private String name;

	private Integer age;

	private Date hireDate;

	private Department dept;

	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				", hireDate=" + hireDate +
				'}';
	}
}
