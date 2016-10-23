package com.javalec.spring_pjt_board.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javalec.spring_pjt_board.dao.BDao;
import com.javalec.spring_pjt_board.dto.UDto;

public class BWriteCommand implements BCommand {

	@Override
	public void execute(Model model) 
	{
		UDto dto;
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String uId = request.getParameter("uId");
		
		System.out.println(uId);

		BDao.getinstance().write(bName, bTitle, bContent, uId);
		
	}

}
