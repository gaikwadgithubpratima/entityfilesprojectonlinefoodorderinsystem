package com.sb.foodsystem.service;

import java.util.List;

import com.sb.foodsystem.model.OrderDetailsDTO;

public interface OrderDetailsService {

    OrderDetailsDTO createOrderDetails(OrderDetailsDTO orderDetailsDTO);

    OrderDetailsDTO getOrderDetailsById(Long id);

    OrderDetailsDTO updateOrderDetails(Long id, OrderDetailsDTO orderDetailsDTO);

    String deleteOrderDetails(Long id);

	List<OrderDetailsDTO> getAllOrderDetails(Long orderDetailsId);
}