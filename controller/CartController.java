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

import com.sb.foodsystem.converter.CartConverter;
import com.sb.foodsystem.model.CartDTO;
import com.sb.foodsystem.service.CartService;


@RestController
@RequestMapping("/api/cart")
public class CartController {

	    @Autowired
	    private final CartService cartService;
	    
	    @SuppressWarnings("unused")
		@Autowired
	    private CartConverter cartConverter;
	    
	   
	  public CartController(CartService cartService, CartConverter cartConverter) {
			super();
			this.cartService = cartService;
			this.cartConverter = cartConverter;
		}

	//Retrieve a cart item by ID
	    @GetMapping("/{cartItemId}")
	    public ResponseEntity<CartDTO> getCartItemById(@PathVariable Long cartId) {
	    	CartDTO cart = cartService.getCartById(cartId);
	        if (cart != null) {
	            return new ResponseEntity<>(cart, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    //Retrieve all cart items for a specific user
	    @GetMapping("/cart/{cartId}")
	    public ResponseEntity<List<CartDTO>> getAllCarts(@PathVariable Long cartId) {
	        List<CartDTO> cart = cartService.getAllCarts(cartId);
	        return new ResponseEntity<>(cart, HttpStatus.OK);
	    }

	    // Create a new cart item
	    @PostMapping
	    public ResponseEntity<CartDTO> createCart(@RequestBody CartDTO cartDTO) {
	        CartDTO addCart = cartService.createCart(cartDTO);
	        return new ResponseEntity<>(addCart, HttpStatus.CREATED);
	    }

	    // Update an existing cart item
	    @PutMapping("/{cartItemId}")
	    public ResponseEntity<CartDTO> updateCart(@PathVariable Long cartId, @RequestBody CartDTO updatedCart) {
	    	CartDTO updated = cartService.updateCart(cartId, updatedCart);
	        if (updated != null) {
	            return new ResponseEntity<>(updated, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    // Delete a cart item by ID
	    @DeleteMapping("/{cartId}")
	    public ResponseEntity<Void> deleteCart(@PathVariable Long cartId) {
	        cartService.deleteCart(cartId);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	}
