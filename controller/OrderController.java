package com.sb.foodsystem.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

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

import com.sb.foodsystem.converter.OrderConverter;
import com.sb.foodsystem.model.OrderDTO;
import com.sb.foodsystem.service.OrderService;


@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @SuppressWarnings("unused")
	private OrderConverter orderConverter;
     
    public OrderController(OrderService orderService, OrderConverter orderConverter) {
		
		this.orderService = orderService;
		this.orderConverter = orderConverter;
	}

	//Read (Retrieve) all orders
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders(@PathVariable Long orderId){
        List<OrderDTO> orders = orderService.getAllOrders(orderId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    
    //Read (Retrieve) an order by ID
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId) {
        OrderDTO order = orderService.getOrderById(orderId);
       
        if (order == null) {
            throw new EntityNotFoundException("OrderEntity not found with ID: " + orderId);
        }
        return ResponseEntity.ok(order);
    }

    //Create (Add) a new order
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO createOrder = orderService.createOrder(orderDTO);
        return new ResponseEntity<>(createOrder, HttpStatus.CREATED);
    }

    //Update (Edit) an existing order
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long orderId, @RequestBody OrderDTO
    		updatedOrder) {
        OrderDTO updated = orderService.updateOrder(orderId, updatedOrder);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //Delete an order by ID
    
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    
}


