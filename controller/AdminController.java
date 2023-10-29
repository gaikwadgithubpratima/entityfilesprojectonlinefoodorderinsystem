package com.sb.foodsystem.controller;


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

import com.sb.foodsystem.converter.AdminConverter;
import com.sb.foodsystem.exception.AdminNotFoundException;
import com.sb.foodsystem.model.AdminDTO;
import com.sb.foodsystem.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
    private final AdminService adminService;
    
	@SuppressWarnings("unused")
	@Autowired
	private AdminConverter adminconverter;
    
    public AdminController(AdminService adminService, AdminConverter adminconverter) {
		super();
		this.adminService = adminService;
		this.adminconverter = adminconverter;
	}

	@GetMapping("/{adminId}")
	public ResponseEntity<AdminDTO> getAdminById(@PathVariable Long id) {
    	AdminDTO admin = adminService.getAdminById(id);
        if (admin == null) {
            throw new AdminNotFoundException("Admin not found with ID: " + id);
        }
        return ResponseEntity.ok(admin);
    }

	@PostMapping("/createAdmin")	
	public ResponseEntity<AdminDTO> createAdmin(@RequestBody AdminDTO adminDTO) {
        AdminDTO addAdmin = adminService.createAdmin(adminDTO);
        return new ResponseEntity<>(addAdmin, HttpStatus.CREATED);
    }

    @PutMapping("/{adminId}")
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable Long id, @RequestBody AdminDTO adminDTO) {
        AdminDTO updatedAdmin = adminService.updateAdmin(id, adminDTO);
        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
    }

	@DeleteMapping("/{adminId}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long adminId) {
        AdminDTO existingAdmin = adminService.getAdminById(adminId);
        if (existingAdmin != null) {
            adminService.deleteAdmin(adminId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
