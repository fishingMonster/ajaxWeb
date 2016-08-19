package com.PracticeWeb.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class Factorize extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2173292584590465377L;
	private String number;
	
	public void setNumber(String number)
	{
		this.number=new String(number);
	}
	public String getNumber()
	{
		return this.number;
	}
	
    public String execute() throws IOException{  
        
        HttpServletResponse response = ServletActionContext.getResponse();  
        response.setHeader("Content-type","text/html;charset=utf-8"); 
        PrintWriter writer = response.getWriter();
        try{
        	writer.print(Integer.parseInt(number));
        }catch(NumberFormatException e){
        	writer.print(" ‰»Î∏Ò Ω¥ÌŒÛ");
        }
        return NONE;  
    }  
}
