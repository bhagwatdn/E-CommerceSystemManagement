package com.app;


    

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import com.app.repository.OrderRepository;
import com.app.service.OrderService;
@RunWith(SpringRunner.class)
@SpringBootTest
class ECommerceOrderSystemApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
    private OrderService orderService;
	
	@MockBean
	private OrderRepository orderRepository;
	
	@Test
	public void getAllOrdersTest() {
		
	
	}
}
//when(orderRepository.findAll()).thenReturn(new Order(1, "John Doe", "2023-10-19T08:57:26.475+00:00", 100.5, "SHIPPED")
		//,new Order(2, "B", "2023-10-19T09:43:49.812+00:00", 200.5, "SHIPPED")).