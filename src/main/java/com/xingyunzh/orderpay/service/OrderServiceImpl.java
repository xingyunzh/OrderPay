package com.xingyunzh.orderpay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingyunzh.orderpay.model.Order;
import com.xingyunzh.orderpay.model.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public List<Order> getAllOrders() {
		return orderRepository.getAllOrders();
	}

}
