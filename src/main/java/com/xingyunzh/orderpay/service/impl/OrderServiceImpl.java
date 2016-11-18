package com.xingyunzh.orderpay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingyunzh.orderpay.model.Order;
import com.xingyunzh.orderpay.model.OrderItem;
import com.xingyunzh.orderpay.model.repository.OrderRepository;
import com.xingyunzh.orderpay.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public List<Order> getAllOrders() {
		return orderRepository.getAllOrders();
	}

	@Override
	public List<Order> getByCustomer(String userid) {
		return orderRepository.getByCustomer(userid);
	}

	@Override
	public Order getById(long orderid) {
		return orderRepository.getById(orderid);
	}

	@Override
	public long createOrder(Order order) {
		// create order
		long orderid = orderRepository.createOrder(order);
		order.setId(orderid);
		
		List<OrderItem> items = order.getOrderItems();
		if (items != null && !items.isEmpty()){
			orderRepository.createOrderItemsForOrder(orderid, items);			
		}
		
		return orderid;
	}

	@Override
	public int deleteOrderById(long orderid) {
		Order order = getById(orderid);
		if (order == null){
			return -1;
		}
		
		if(order.getPayments() != null && !order.getPayments().isEmpty()){
			return -3;
		}
		
		if (order.getOrderItems() != null && !order.getOrderItems().isEmpty()) {
			for	(OrderItem item : order.getOrderItems()){
				orderRepository.deleteOrderItem(item.getId());
			}
		}
		
		orderRepository.deleteOrder(orderid);	
		
		return 0;
	}
	
	
}
