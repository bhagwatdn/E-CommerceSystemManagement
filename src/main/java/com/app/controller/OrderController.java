package com.app.controller;

import java.util.List;

import com.app.globalexceptionhandler.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
	@GetMapping("/getOrderById/{orderId}")
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
	@DeleteMapping("/deleteOrder/{orderId}")
	public ResponseEntity<String> deleteOrderById(@PathVariable Integer orderId) {
		try {
			orderService.deleteOrderById(orderId);
			return ResponseEntity.ok("Order with ID " + orderId + " deleted successfully.");
		} catch (OrderNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
}

