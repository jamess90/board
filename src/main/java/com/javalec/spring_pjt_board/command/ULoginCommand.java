package com.javalec.spring_pjt_board.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javalec.spring_pjt_board.dao.BDao;
import com.javalec.spring_pjt_board.dao.UDao;
import com.javalec.spring_pjt_board.dto.BDto;
import com.javalec.spring_pjt_board.dto.UDto;

public class ULoginCommand implements BCommand
{
	UDto dto = null;

	@Override
	public void execute(Model model) 
	{
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String uId = request.getParameter("uId");
		String uPassword = request.getParameter("uPassword");
		
		System.out.println("uId="+uId);
		System.out.println("uPassword="+uPassword);
		System.out.println("request_complete");
		
		UDao.getinstance().check_account(uId, uPassword);
				
		System.out.println("¿©±â´Ù"+UDao.getinstance().check_error);

		if(!UDao.getinstance().check_error)
			dto = UDao.getinstance().getuserinfo(uId);
			model.addAttribute("request", dto);
	}
	
}
