package com.app.testservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.app.entity.Order;
import com.app.globalexceptionhandler.OrderNotFoundException;
import com.app.repository.OrderRepository;
import com.app.service.OrderService;
import com.app.utility.OrderStatus;

@SpringBootTest
public class OrderServiceTest {

	@MockBean
    OrderRepository orderRepository;
	
	@Autowired
	OrderService orderService;
	
	@Test
	public void test_placeNewOrder() throws Exception {
		SimpleDateFormat simpleformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:s+ss");
		Date date = simpleformat.parse("2019-12-24 01:22:05+00");
		 OrderStatus newStatus = OrderStatus.SHIPPED;
		Order order=new Order(101,"Bhagwat",date,15.3f,newStatus);
		when(orderRepository.save(order)).thenReturn(order);
		assertEquals(order,orderService.placeNewOrder(order));
		
	}
	@Test
	public void test_getOrderById() throws Exception {
		SimpleDateFormat simpleformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:s+ss");
		Date date = simpleformat.parse("2019-12-24 01:22:05+00");
		 OrderStatus newStatus = OrderStatus.SHIPPED;
		Order order=new Order(101,"Bhagwat",date,15.3f,newStatus);
		when(orderRepository.findById(101)).thenReturn(Optional.of(order));
		assertEquals("Bhagwat",orderService.getOrderById(101).getCustomerName());
	}
	@Test
	public void getAllOrders() throws Exception {
		SimpleDateFormat simpleformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:s+ss");
		Date date = simpleformat.parse("2019-12-24 01:22:05+00");
		 OrderStatus newStatus = OrderStatus.SHIPPED;
		 List<Order> orderList=Arrays.asList(new Order (101,"Bhagwat",date,15.3f,newStatus), new  Order (102,"vingnesh",date,15.3f,newStatus));
		 when(orderRepository.findAll()).thenReturn(orderList);
		 assertEquals(2,orderService.getAllOrders().size());

	}
	@Test
	public void updateOrderStatus() throws Exception {
		
		Order mockOrder = new Order();
		    OrderStatus newStatus = OrderStatus.SHIPPED;
			Integer orderId = 2;
			mockOrder.setOrderId(orderId);
			mockOrder.setOrderStatus(newStatus);
			when(orderRepository.findById(orderId)).thenReturn(Optional.of(mockOrder));
            when(orderRepository.save(mockOrder)).thenReturn(mockOrder);
        
        Order updatedOrder = orderService.updateOrderStatus(orderId, newStatus);
        assertThat(updatedOrder).isNotNull();
        assertThat(updatedOrder.getOrderId()).isEqualTo(orderId);
        assertThat(updatedOrder.getOrderStatus()).isEqualTo(newStatus);
	}
}
