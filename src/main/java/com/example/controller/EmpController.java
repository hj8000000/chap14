package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dao.EmpDao;
import com.example.domain.Emp;

@Controller
public class EmpController {
	
	static Log log = LogFactory.getLog(EmpController.class);
	
	@Autowired
	EmpDao dao;
	
	@RequestMapping("/emp/listall") //요청 맵핑
	public String listAll(HttpServletRequest request) {
		
		log.info("###############");
		log.info("###listAll()###");
		log.info("###############");
		List<Emp> list = dao.getAllEmps();
		
		request.setAttribute("emps", list);
		
		return "listall";  	// 뷰의 이름 / jsp 파일의 이름 / META_INF_resources_WEB-INF_listall.jsp 임 / forward 의 기능을 함
							// 설정을 해줘야 함! 그냥 못씀 / application.properties 에 spring.mvc.view.prefix=/WEB-INF/
																					// spring.mvc.view.suffix=.jsp  추가
	}

}
