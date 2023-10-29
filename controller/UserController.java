package com.sb.foodsystem.controller;

import java.sql.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sb.foodsystem.entity.User;
import com.sb.foodsystem.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/user")
	public String validate(@RequestBody User user) throws ServletException
	{
		String jwtToken="";
		
		if(user.getFirstName()==null || user.getPassword()==null)
		{
			throw new ServletException("Please Fill in Username and Password");
		}
		
		String userName=user.getFirstName();
		String password=user.getPassword();
		//will check in database whether the login and password is present or not
		user=userService.login(userName, password);
		
		if(user==null)
		{
			throw new ServletException("User not found");
		}
		
		jwtToken=Jwts.builder().setSubject(userName).claim("userName", user.getFirstName()).
			setIssuedAt(new Date(0)).signWith(SignatureAlgorithm.HS256, "secretkey").compact();
		
		return jwtToken;
		
		
	}
	
}
