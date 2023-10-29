package com.sb.foodsystem.service;

import java.util.List;

import com.sb.foodsystem.model.CartDTO;

public interface CartService {

    CartDTO createCart(CartDTO cartDTO);

    CartDTO getCartById(Long id);

    CartDTO updateCart(Long id, CartDTO cartDTO);

	List<CartDTO> getAllCarts(Long userId);

	void deleteCart(Long cartId);


}
