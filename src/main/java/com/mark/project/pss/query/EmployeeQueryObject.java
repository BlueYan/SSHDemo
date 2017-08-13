package com.mark.project.pss.query;

import com.mark.project.pss.util.CommonUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Mark_Yan on 2017/8/13.
 */
public class EmployeeQueryObject extends QueryObject {

	@Setter
	@Getter
	private String keyword;

	@Getter
	@Setter
	private Long deptId;

	public void handleParams() {

		if ( CommonUtil.isNotEmpty(keyword) ) {
			//关键字不为空
			conditions.add("obj.name LIKE ? OR obj.email LIKE ?");
			params.add("%" + keyword +"%");
			params.add("%" + keyword +"%");
		}
		if ( deptId != null && deptId != -1 ) {
			conditions.add("obj.dept.id = ?");
			params.add(deptId);
		}
//		if ( currentPage != null ) {
//			conditions.add("LIMIT ?, ?");
//			params.add(currentPage);
//		}
//		if ( pageSize != null ) {
//			params.add(pageSize);
//		}
	}
}
