package com.sb.foodsystem.service;

import java.util.List;

import com.sb.foodsystem.model.MenuTypeDTO;

public interface MenuTypeService {

    MenuTypeDTO createMenuType(MenuTypeDTO menuTypeDTO);

    MenuTypeDTO getMenuTypeById(Long id);

    MenuTypeDTO updateMenuType(Long id, MenuTypeDTO menuTypeDTO);

    String deleteMenuType(Long id);

	List<MenuTypeDTO> getAllMenuTypes(Long menuTypeId);
}