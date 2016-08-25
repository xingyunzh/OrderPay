package com.xingyunzh.orderpay.model.repository;

import java.util.List;

import com.xingyunzh.orderpay.model.Order;
import com.xingyunzh.orderpay.model.OrderItem;

public interface OrderRepository {
	public List<Order> getAllOrders();
	public List<Order> getByCustomer(String userid);
	public Order getById(long id);
	public long createOrder(final Order order);
	public void createOrderItemsForOrder(long orderid, List<OrderItem> items);
	public void deleteOrder(long id);
	public void deleteOrderItem(long id);
}
