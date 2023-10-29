package com.sb.foodsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sb.foodsystem.converter.LoginConverter;
import com.sb.foodsystem.model.LoginDTO;
import com.sb.foodsystem.service.LoginService;

@RestController
@RequestMapping("/api/login")
public class LoginController {
	
    @Autowired
    private LoginService loginService;
 
    @SuppressWarnings("unused")
	private LoginConverter loginConverter;
    
    
	public LoginController(LoginService loginService, LoginConverter loginConverter) {
		super();
		this.loginService = loginService;
		this.loginConverter = loginConverter;
	}
	//Retrieve a login by ID
    @GetMapping("/{loginId}")
    public ResponseEntity<LoginDTO> getLoginById(@PathVariable Long loginId) {
    	LoginDTO login = loginService.getLoginById(loginId);
        if (login != null) {
            return new ResponseEntity<>(login, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Retrieve all login for a specific user
    @GetMapping("/login/{loginId}")
    public ResponseEntity<List<LoginDTO>> getAllLogins(@PathVariable Long loginId) {
        List<LoginDTO> login = loginService.getAllLogin(loginId);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }

    // Create a new login
    @PostMapping
    public ResponseEntity<LoginDTO> createLogin(@RequestBody LoginDTO loginDTO) {
    	LoginDTO addLogin = loginService.createLogin(loginDTO);
        return new ResponseEntity<>(addLogin, HttpStatus.CREATED);
    }

    // Update an existing login
    @PutMapping("/{loginId}")
    public ResponseEntity<LoginDTO> updateLogin(@PathVariable Long loginId, @RequestBody LoginDTO updatedLogin) {
    	LoginDTO updated = loginService.updateLogin(loginId, updatedLogin);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a login by ID
    @DeleteMapping("/{loginId}")
    public ResponseEntity<Void> deleteLogin(@PathVariable Long loginId) {
        loginService.deleteLogin(loginId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
