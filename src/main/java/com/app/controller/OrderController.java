package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Order;
import com.app.service.OrderService;
import com.app.utility.OrderStatus;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	private OrderService orderService;


	@PostMapping("/saveOrder")
	public ResponseEntity<Order> placeaNewOrder( @RequestBody  Order order){
		Order placedOrder=orderService.placeNewOrder(order);
		return new ResponseEntity<>(placedOrder,HttpStatus.CREATED);

	}
	@GetMapping("/getAllOrders")
	public ResponseEntity<List<Order>> getAllOrders(){
		List<Order> orders=orderService.getAllOrders();
		return new ResponseEntity<>(orders,HttpStatus.OK);

	}
	@GetMapping("/getOrdrById/{orderId}")
	public ResponseEntity<Order> getOrdrById(@PathVariable Integer orderId){
		Order order=orderService.getOrderById(orderId);
		return ResponseEntity.ok(order);
	}
	
	@PutMapping("/updateOrderStatus/{orderId}/newStatus")
	public ResponseEntity<Order> updateOrderStatus(@PathVariable Integer orderId,@RequestParam OrderStatus newStatus ){
		Order updateOrder=orderService.updateOrderStatus(orderId, newStatus);
		if(updateOrder!=null) {
			return new ResponseEntity<>(updateOrder,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
}

