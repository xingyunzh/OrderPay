package com.xingyunzh.orderpay.model.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.xingyunzh.orderpay.model.Order;
import com.xingyunzh.orderpay.model.OrderItem;
import com.xingyunzh.orderpay.model.repository.OrderRepository;
import com.xingyunzh.orderpay.model.repository.PaymentRepository;

@Repository("orderRepo")
public class HOrderRepository implements OrderRepository {
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Order> getAllOrders() {
		String hql = "FROM Order";
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		return q.list();
	}

	@Override
	public List<Order> getByCustomer(String userid) {
		String hql = "FROM Order o WHERE o.customerId = ?";
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		q.setString(0, userid);
		return q.list();
	}

	@Override
	public Order getById(long id) {
		return (Order) this.sessionFactory.getCurrentSession().get(Order.class, id);
	}

	@Override
	public long createOrder(Order order) {
		return (long) this.sessionFactory.getCurrentSession().save(order);
	}

	@Override
	public void createOrderItemsForOrder(long orderid, List<OrderItem> items) {
		Order order = getById(orderid);
		for (OrderItem item : items){
			item.setOrder(order);
			this.sessionFactory.getCurrentSession().save(item);
		}
	}

	@Override
	public void deleteOrder(long id) {
		this.sessionFactory.getCurrentSession().delete(getById(id));
		
	}

	@Override
	public void deleteOrderItem(long id) {
		OrderItem item = (OrderItem) this.sessionFactory.getCurrentSession().get(OrderItem.class, id);
		this.sessionFactory.getCurrentSession().delete(item);
	}

}


