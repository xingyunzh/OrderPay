package com.xingyunzh.orderpay.model.repository;

import java.util.List;

import com.xingyunzh.orderpay.model.Order;

public interface OrderRepository {
	List<Order> getAllOrders();
}
