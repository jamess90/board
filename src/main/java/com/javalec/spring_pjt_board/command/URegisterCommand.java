package com.javalec.spring_pjt_board.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javalec.spring_pjt_board.dao.UDao;

public class URegisterCommand implements BCommand
{
	UDao dao = null;
	public URegisterCommand(UDao u) 
	{
		dao = u;
	}
	@Override
	public void execute(Model model) 
	{
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
	
		String uId = request.getParameter("uId");
		String uPassword = request.getParameter("uPassword");
		String uName = request.getParameter("uName");
		
		dao.check_ID(uId);
				
		if(!dao.check_error)
			dao.register(uId, uPassword, uName);
	}
}
