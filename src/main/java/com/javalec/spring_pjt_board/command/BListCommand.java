package com.javalec.spring_pjt_board.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javalec.spring_pjt_board.dao.BDao;

import com.javalec.spring_pjt_board.dto.BDto;

public class BListCommand implements BCommand
{
	public static String blist;
	public BListCommand(String listtype) 
	{
		blist = listtype;
	}
	@Override
	public void execute(Model model) 
	{
		// TODO Auto-generated method stub
		BDao dao = new BDao();		
		
		ArrayList<BDto>dtos = dao.list(blist);
		model.addAttribute(blist,dtos);
	}
	
}
