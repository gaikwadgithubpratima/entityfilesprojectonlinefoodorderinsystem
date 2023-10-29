package com.sb.foodsystem.controller;

import java.util.List;

import javax.validation.ValidationException;

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

import com.sb.foodsystem.converter.OrderDetailsConverter;
import com.sb.foodsystem.model.OrderDetailsDTO;
import com.sb.foodsystem.service.OrderDetailsService;

@RestController
@RequestMapping("/api/orderDetails")
public class OrderDetailsController {

    @Autowired
    private OrderDetailsService orderDetailsService;
    
    @SuppressWarnings("unused")
	private OrderDetailsConverter orderDetailsConverter;
    
    public OrderDetailsController(OrderDetailsService orderDetailsService,
			OrderDetailsConverter orderDetailsConverter) {
		super();
		this.orderDetailsService = orderDetailsService;
		this.orderDetailsConverter = orderDetailsConverter;
	}

	//Read (Retrieve) all order details
    @GetMapping
    public ResponseEntity<List<OrderDetailsDTO>> getAllOrderDetails(@PathVariable Long orderDetailsId) {
        List<OrderDetailsDTO> orderDetails = orderDetailsService.getAllOrderDetails(orderDetailsId);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    //Read (Retrieve) an order detail by ID
  
    @GetMapping("/{orderDetailsId}")
    public ResponseEntity<OrderDetailsDTO> getOrderDetailsById(@PathVariable Long orderDetailsId) {
        OrderDetailsDTO orderDetails = orderDetailsService.getOrderDetailsById(orderDetailsId);
        if (orderDetails != null) 
        {
            return new ResponseEntity<>(orderDetails, HttpStatus.OK);
        } 
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Create (Add) a new order detail
    @PostMapping
    public ResponseEntity<OrderDetailsDTO> createOrderItem(@RequestBody OrderDetailsDTO orderDetailsDTO) {
        OrderDetailsDTO createOrderDetails = orderDetailsService.createOrderDetails(orderDetailsDTO);
        if (createOrderDetails == null) {
            throw new ValidationException("Validation failed for entity creation");
        }
        return ResponseEntity.ok(createOrderDetails);
    }
    
    // Update (Edit) an existing order detail
    @PutMapping("/{orderDetailsId}")
    public ResponseEntity<OrderDetailsDTO> updateOrderDetails(@PathVariable Long orderDetailsId, @RequestBody OrderDetailsDTO updatedOrderDetails) {
    	OrderDetailsDTO updated = orderDetailsService.updateOrderDetails(orderDetailsId, updatedOrderDetails);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //Delete an order detail by ID
    @DeleteMapping("/{orderDetailsId}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long orderDetailsId) {
        orderDetailsService.deleteOrderDetails(orderDetailsId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}


