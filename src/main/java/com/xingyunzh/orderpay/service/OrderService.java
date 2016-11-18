package com.xingyunzh.orderpay.service;

import java.util.List;
import com.xingyunzh.orderpay.model.Order;

public interface OrderService {
	public List<Order> getAllOrders();
	public List<Order> getByCustomer(String userid);
	public Order getById(long orderid);
	public long createOrder(Order order);
	public int deleteOrderById(long orderid);
}
