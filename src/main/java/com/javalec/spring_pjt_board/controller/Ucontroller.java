package com.javalec.spring_pjt_board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javalec.spring_pjt_board.command.BCommand;
import com.javalec.spring_pjt_board.command.BListCommand;
import com.javalec.spring_pjt_board.command.BWriteCommand;
import com.javalec.spring_pjt_board.command.ULoginCommand;
import com.javalec.spring_pjt_board.command.URegisterCommand;
import com.javalec.spring_pjt_board.dao.UDao;

@Controller
public class Ucontroller 
{
	BCommand command;
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model)
	{
		System.out.println("login()");
		model.addAttribute("request", request);

		command =  new ULoginCommand();
		command.execute(model);
		
		if(!UDao.getinstance().check_error)
			return "login_success";
		else
			return "login_view";
		
	}	
	 
	@RequestMapping("/logout")
	public String logout(Model model)
	{
		System.out.println("logout()");
		return "login_view";
	}	
	
	@RequestMapping("/login_view")
	public String login_view(Model model)
	{
		System.out.println("login_view()");
		
		return "login_view";
		
	}	
	
	@RequestMapping("/register_do")
	public String register(HttpServletRequest request, Model model)
	{
		System.out.println("register_do()");	
		model.addAttribute("request", request);
		
		command = new URegisterCommand(UDao.getinstance());
		command.execute(model);
		
		if(UDao.getinstance().check_error)
			return "redirect:register_view";
		else
			return "login_view";
	}
	
	@RequestMapping("/register_view")
	public String register_view(Model model)
	{
		System.out.println("register_view()");
		
		return "register_view";
		
	}
}
