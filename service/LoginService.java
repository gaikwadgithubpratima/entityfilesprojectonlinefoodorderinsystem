package com.sb.foodsystem.service;

import java.util.List;

import com.sb.foodsystem.model.LoginDTO;

public interface LoginService {

    LoginDTO createLogin(LoginDTO loginDTO);

    LoginDTO getLoginById(Long id);

    LoginDTO updateLogin(Long id, LoginDTO loginDTO);
	
	List<LoginDTO> getAllLogin(Long loginId);

	void deleteLogin(Long loginId);

	

    
}