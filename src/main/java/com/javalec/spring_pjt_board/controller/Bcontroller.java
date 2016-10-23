package com.javalec.spring_pjt_board.controller;

import javax.servlet.http.HttpServletRequest;

//import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javalec.spring_pjt_board.command.BCommand;
import com.javalec.spring_pjt_board.command.BContentCommand;
import com.javalec.spring_pjt_board.command.BDeleteCommand;
import com.javalec.spring_pjt_board.command.BListCommand;
import com.javalec.spring_pjt_board.command.BModifyCommand;
import com.javalec.spring_pjt_board.command.BReplyCommand;
import com.javalec.spring_pjt_board.command.BReplyViewCommand;
import com.javalec.spring_pjt_board.command.BWriteCommand;
import com.javalec.spring_pjt_board.command.ULoginCommand;
import com.javalec.spring_pjt_board.dao.BDao;
import com.javalec.spring_pjt_board.dto.UDto;

@Controller
public class Bcontroller
{
	BCommand command;
	
	@RequestMapping("/home")
	public String home(Model model)
	{
		System.out.println("home()");
		return "home";
	}
	
	@RequestMapping("/board_view")
	public String board_view(Model model)
	{
		System.out.println("board_view()");

			return "board_view";
	}
	
	@RequestMapping("/list")
	public String list(Model model)
	{
		BListCommand listcommand = null;
		listcommand.blist = "list";
		System.out.println("list()");
		
		command = new BListCommand("list");
		command.execute(model);
		
		return "list";
		
	}

	@RequestMapping("/viplist")
	public String viplist(Model model)
	{
		BListCommand listcommand = null;
		listcommand.blist = "viplist";
		System.out.println("viplist()");
		
		command = new BListCommand("viplist");
		command.execute(model);

		return "viplist";
		
	}

	@RequestMapping("/write_view")
	public String write_view(Model model)
	{
		System.out.println("write_view()");
		
		return "write_view";
	}
	
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model)
	{
		System.out.println("write()");
		
		model.addAttribute("request", request);
		
		command = new BWriteCommand();
		command.execute(model);
		
		BListCommand listcommand = null;
		
		if(listcommand.blist == "list")
		{
			return "redirect:list";
		}
		else
		{
			return "redirect:viplist";
		}
	}
	

	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model)
	{
		System.out.println("content_view()");
		
		model.addAttribute("request", request);
		
		System.out.println("request in controller = " + request.getParameter("bNum"));
		command = new BContentCommand();
		command.execute(model);
		
		return "content_view";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/modify")
	public String modify(HttpServletRequest request, Model model)
	{
		System.out.println("modify()");
		
		model.addAttribute("request", request);
		command = new BModifyCommand();
		command.execute(model);
		
		BListCommand listcommand = null;
		
		
		if(listcommand.blist == "list")
		{
			return "redirect:list";
		}
		else
		{
			return "redirect:viplist";
		}
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model)
	{
		System.out.println("reply_view()");
		
		model.addAttribute("request", request);
		command = new BReplyViewCommand();
		command.execute(model);
		
		
		return "reply_view";
	}
	
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model)
	{
		System.out.println("reply()");
		
		model.addAttribute("request", request);
		command = new BReplyCommand();
		command.execute(model);
		
		BListCommand listcommand = null;
		
		
		if(listcommand.blist == "list")
		{
			return "redirect:list";
		}
		else
		{
			return "redirect:viplist";
		}
		
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model)
	{
		System.out.println("Delete()");
		
		model.addAttribute("request", request);
		command = new BDeleteCommand();
		command.execute(model);
		
		BListCommand listcommand = null;
		
		
		if(listcommand.blist == "list")
		{
			return "redirect:list";
		}
		else
		{
			return "redirect:viplist";
		}
	}
	

}
