package com.tianzhixing.oms.bussiness.backend.web.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.appmall.rpc.bean.CourseDetailBean;
import com.tianzhixing.appmall.rpc.service.RPCCourseDetailService;



/**
 * Created by routine.k on 2018/6/24.
 */
@Service
public class CourseDetailService {

	@Autowired
	private RPCCourseDetailService rpcCourseDetailService;

	public long count(CourseDetailBean courseDetailBean) {
		return rpcCourseDetailService.count(courseDetailBean);
	}

	public List<CourseDetailBean> list(PagerMapping pagerMapping, CourseDetailBean courseDetailBean) {
		return rpcCourseDetailService.list(pagerMapping.getFrom(), pagerMapping.getPageSize(), courseDetailBean);
	}

	public void add(CourseDetailBean courseDetailBean) {
		rpcCourseDetailService.add(courseDetailBean);
	}

	public CourseDetailBean getById(Long id) {
		return rpcCourseDetailService.getById(id);
	}

	public void update(CourseDetailBean courseDetailBean) {
		CourseDetailBean am = rpcCourseDetailService.getById(courseDetailBean.getId());
		if (am == null)
			throw new RequestException("courseDetail.not.found");
		rpcCourseDetailService.update(courseDetailBean);
	}
}
