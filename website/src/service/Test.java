package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Test implements UserService{
	
	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/xml;charset=utf-8");
		System.out.println("categoryService execute()");
		
	
		
		return null;
	}
}
