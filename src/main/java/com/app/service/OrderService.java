package com.app.service;

import java.util.List;

import com.app.entity.Order;
import com.app.utility.OrderStatus;

public interface OrderService {

	public Order placeNewOrder(Order order);
	public List<Order> getAllOrders();
	public Order getOrderById(int orderId);
	public Order updateOrderStatus(Integer orderId, OrderStatus newStatus);
	

}
