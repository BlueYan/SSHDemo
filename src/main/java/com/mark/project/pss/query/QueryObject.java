package com.mark.project.pss.query;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.management.AttributeList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/13.
 * 高级查询抽象类
 *
 */
public abstract class QueryObject {

	//存储条件 sql语句中的条件
	@Getter
	protected List<String> conditions = new ArrayList<String>();

	//存储参数 用来补充sql中的占位符
	@Getter
	protected List<Object> params = new AttributeList();

	@Getter
	@Setter
	protected Integer currentPage = 1; //当前页

	@Getter
	@Setter
	protected Integer pageSize = 5; //页面大小

	public String getQuery() {
		handleParams(); //由调用者自己处理条件和参数
		if ( conditions.size() == 0 ) return ""; //没有条件返回空字符串
		StringBuilder sb = new StringBuilder();
		sb.append(" WHERE ").append(StringUtils.join(conditions, " AND ")); //拼接条件
		return sb.toString();
	}

	abstract public void handleParams();



}
