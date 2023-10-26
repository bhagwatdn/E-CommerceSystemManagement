package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.entity.Order;
import com.app.utility.OrderStatus;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	/*@Query(value="update order set=order_id=:order_id and order_status=:order_status")
	public Order updateOrderStatus(@Param  order_id integer,@);
	
}*/
	/*@Modifying
    @Query("UPDATE order e SET e.orderStatus = :orderStatus WHERE e.orderId = :orderId")
    int updateFieldById(@Param("orderId") Integer orderId, @Param("orderStatus") OrderStatus orderStatus);
}*/}