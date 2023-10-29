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

import com.sb.foodsystem.converter.MenuConverter;

import com.sb.foodsystem.model.MenuDTO;
import com.sb.foodsystem.service.MenuService;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@SuppressWarnings("unused")
	private MenuConverter menuConverter;
	
	
	public MenuController(MenuService menuService, MenuConverter menuConverter) {
		super();
		this.menuService = menuService;
		this.menuConverter = menuConverter;
	}

	@GetMapping
	 public ResponseEntity<List<MenuDTO>> getAllMenu(@PathVariable Long menuId) {
        List<MenuDTO> menu = menuService.getAllMenu(menuId);
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }


    @GetMapping("/{menuId}")
    public MenuDTO getMenuById(@PathVariable Long menuId)
    {
        return menuService.getMenuById(menuId);             
    }

    @PostMapping
    public MenuDTO createMenu(@RequestBody MenuDTO menuDTO) 
    {
        return menuService.createMenu(menuDTO);
    }

    @PutMapping("/{menuId}")
    public ResponseEntity<MenuDTO> updateMenu(@PathVariable Long menuId, @RequestBody MenuDTO menuDTO)
    {
    	MenuDTO updated = menuService.updateMenu(menuId,menuDTO);
        if (updated != null) {
            return new ResponseEntity<MenuDTO>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }
}
