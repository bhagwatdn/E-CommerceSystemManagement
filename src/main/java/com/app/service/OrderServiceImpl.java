package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.entity.Order;
import com.app.globalexceptionhandler.NoSuchOrderExistsException;
import com.app.globalexceptionhandler.OrderAlreadyExistsException;
import com.app.globalexceptionhandler.OrderNotFoundException;
import com.app.repository.OrderRepository;
import com.app.utility.OrderStatus;

@Service
public class OrderServiceImpl implements OrderService
{
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Order placeNewOrder(Order order)
	{
		return orderRepository.save(order);
	}

	@Override
	public List<Order> getAllOrders()
	{
		
		return orderRepository.findAll();
	}

	@Override
	public Order getOrderById(int orderId) 
	{
		
		Optional<Order> getOrder=orderRepository.findById(orderId);
		if(getOrder.isEmpty()) {
			throw new NoSuchOrderExistsException("NO ORDER PRESENT WITH ID = " + orderId);
		}
		return getOrder.get();
	}

	@Override
	public Order updateOrderStatus(Integer orderId, OrderStatus newStatus)
	{
		Optional<Order> existOrder=orderRepository.findById(orderId);
		if(existOrder.isPresent()) 
		{
			Order order=existOrder.get();
			order.setOrderStatus(newStatus);
			orderRepository.save(order);
			return order;
		}
		else {
			throw new OrderNotFoundException("Order Not Found With ID: " + orderId);
		}
	}
	

	

	

	
}
