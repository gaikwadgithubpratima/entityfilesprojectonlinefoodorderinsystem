package com.sb.foodsystem.service;

import java.util.List;

import com.sb.foodsystem.model.OrderDTO;

public interface OrderService {

    OrderDTO createOrder(OrderDTO orderDTO);

    OrderDTO getOrderById(Long order_id);

    OrderDTO updateOrder(Long order_id, OrderDTO orderDTO);

    String deleteOrder(Long order_id);

	List<OrderDTO> getAllOrders(Long orderId);
}