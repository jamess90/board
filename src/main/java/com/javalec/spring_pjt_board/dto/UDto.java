package com.javalec.spring_pjt_board.dto;

public class UDto
{
	String uId;
	String uPassword;
	String uName;
	String uGrade;
	
	public UDto(String uId, String uPassword, String uName, String uGrade) 
	{
		this.uId = uId;
		this.uPassword = uPassword;
		this.uName = uName;
		this.uGrade = uGrade;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuGrade() {
		return uGrade;
	}

	public void setuGrade(String uGrade) {
		this.uGrade = uGrade;
	}

}




