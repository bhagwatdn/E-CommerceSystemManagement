package com.app.testcontroller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.controller.OrderController;
import com.app.entity.Order;
import com.app.service.OrderService;
import com.app.utility.OrderStatus;
import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(OrderController.class)
public class OrderControllerTest {

	@MockBean
	private OrderService orderService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void test_PlaceaNewOrder() throws Exception {
		SimpleDateFormat simpleformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:s+ss");
		Date date = simpleformat.parse("2019-12-24 01:22:05+00");
		Order mockOrder = new Order();
		mockOrder.setOrderId(2);
		mockOrder.setCustomerName("B");
		mockOrder.setOrderDate(date);
		mockOrder.setTotalAmount((200.5f));
		mockOrder.setOrderStatus(OrderStatus.SHIPPED);
		when(orderService.placeNewOrder(mockOrder)).thenReturn(mockOrder);
		mockMvc.perform(post("/api/saveOrder")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(mockOrder)))
		.andExpect(status().isCreated());

	}

     @Test
	public void test_getOrdrById() throws JsonProcessingException, Exception{
		SimpleDateFormat simpleformat = new
				SimpleDateFormat("yyyy-MM-dd hh:mm:s+ss"); Date date =
				simpleformat.parse("2019-12-24 01:22:05+00"); Order mockOrder = new Order();
				mockOrder.setOrderId(2);
				mockOrder.setCustomerName("Bhagwat");
				mockOrder.setOrderDate(date);
				mockOrder.setTotalAmount((200.5f));
				mockOrder.setOrderStatus(OrderStatus.SHIPPED);
				when(orderService.getOrderById(2)).thenReturn(mockOrder);
				mockMvc.perform(get("/api/getOrdrById/{orderId}",2)
						.contentType(MediaType.APPLICATION_JSON))
				        .andExpect(status().isOk())
			          	.andExpect(jsonPath("$.customerName").value("Bhagwat"));


	}
     @Test
     public void getAllOrders() throws Exception{
    	 SimpleDateFormat simpleformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:s+ss");
    	   Date date =simpleformat.parse("2019-12-24 01:22:05+00");
 				Order mockOrder1 = new Order();
 				mockOrder1.setOrderId(2);
 				mockOrder1.setCustomerName("Bhagwat");
 				mockOrder1.setOrderDate(date);
 				mockOrder1.setTotalAmount((200.5f));
 				mockOrder1.setOrderStatus(OrderStatus.SHIPPED);
 				
 				Order mockOrder2 = new Order();
 				mockOrder2.setOrderId(3);
 				mockOrder2.setCustomerName("Vignesh");
 				mockOrder2.setOrderDate(date);
 				mockOrder2.setTotalAmount((30.5f));
 				mockOrder2.setOrderStatus(OrderStatus.SHIPPED);
 			
 				List<Order> orderList=new ArrayList<>();
 				orderList.add(mockOrder1);
 				orderList.add(mockOrder1);
 				 				
 				when(orderService.getAllOrders()).thenReturn((List<Order>) orderList);
 				mockMvc.perform(get("/api/getAllOrders")
 						.contentType(MediaType.APPLICATION_JSON)) 
 				        .andExpect(status().isOk())
 				        .andExpect(jsonPath("$[0].customerName").value("Bhagwat"));

     }
     @Test
 	public void test_updateOrderStatus() throws  Exception{
 				
 				Order mockOrder = new Order();
 			    OrderStatus newStatus = OrderStatus.SHIPPED;
 				Integer orderId = 2;
 				mockOrder.setOrderId(orderId);
 				mockOrder.setOrderStatus(newStatus);
 				when(orderService.updateOrderStatus(orderId,newStatus)).thenReturn(mockOrder);
 				mockMvc.perform(put("/api/updateOrderStatus/{orderId}/newStatus",orderId)
 						.param("newStatus", newStatus.toString()))
 				        .andExpect(status().isOk())
 				        .andExpect(jsonPath("$.orderId").value(orderId))
                        .andExpect(jsonPath("$.orderStatus").value(newStatus.toString()));


 	}
	

}
