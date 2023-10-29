package com.sb.foodsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sb.foodsystem.converter.MenuTypeConverter;

import com.sb.foodsystem.model.MenuTypeDTO;
import com.sb.foodsystem.service.MenuTypeService;

@RestController
@RequestMapping("/api/menuType")
public class MenuTypeController {
	
	@Autowired
	private MenuTypeService menuTypeService;
	
	@SuppressWarnings("unused")
	private MenuTypeConverter menuTypeConverter;

	

	public MenuTypeController(MenuTypeService menuTypeService, MenuTypeConverter menuTypeConverter) {
		super();
		this.menuTypeService = menuTypeService;
		this.menuTypeConverter = menuTypeConverter;
	}


	@GetMapping
	 public ResponseEntity<List<MenuTypeDTO>> getAllMenuTypes(@PathVariable Long menuTypeId) {
		 List<MenuTypeDTO> menuType = menuTypeService.getAllMenuTypes(menuTypeId);
		 return new ResponseEntity<>(menuType, HttpStatus.OK);
	 }


     @GetMapping("/{menuTypeId}")
     public MenuTypeDTO getMenuTypeById(@PathVariable Long menuTypeId)
     {
         return menuTypeService.getMenuTypeById(menuTypeId);             
     }

     @PostMapping
     public MenuTypeDTO createMenuType(@RequestBody MenuTypeDTO menuTypeDTO) 
   {
       return menuTypeService.createMenuType(menuTypeDTO);
   }

   @PutMapping("/{menuTypeId}")
    public ResponseEntity<MenuTypeDTO> updateMenuType(@PathVariable Long menuTypeId, @RequestBody MenuTypeDTO menuTypeDTO)
    {
	   MenuTypeDTO updated = menuTypeService.updateMenuType(menuTypeId,menuTypeDTO);
        if (updated != null) {
           return new ResponseEntity<MenuTypeDTO>(updated, HttpStatus.OK);
       } else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
   }
}
