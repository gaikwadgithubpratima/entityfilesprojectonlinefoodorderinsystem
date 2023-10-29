package com.sb.foodsystem.service;

import java.util.List;

import com.sb.foodsystem.model.MenuDTO;

public interface MenuService {

    MenuDTO createMenu(MenuDTO menuDTO);

    MenuDTO getMenuById(Long id);

    String deleteMenu(Long id);


	List<MenuDTO> getAllMenu(Long menuId);

	MenuDTO updateMenu(Long id, MenuDTO menuDTO);
}