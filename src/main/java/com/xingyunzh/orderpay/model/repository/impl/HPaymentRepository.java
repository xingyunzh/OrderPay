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

import com.xingyunzh.orderpay.model.Payment;
import com.xingyunzh.orderpay.model.repository.OrderRepository;
import com.xingyunzh.orderpay.model.repository.PaymentRepository;

@Repository("paymentRepo")
public class HPaymentRepository implements PaymentRepository {
	private SessionFactory sessionFactory;
	private OrderRepository orderRepository;
	


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public OrderRepository getOrderRepository() {
		return orderRepository;
	}
	@Autowired
	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public List<Payment> getPaymentsByOrderId(long orderid) {
		String hql = "FROM Payment WHERE orderid = ?";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setLong(0, orderid);
		return query.list();
	}

	@Override
	public List<Payment> getAllPayments() {
		String hql = "FROM Payment";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@Override
	public Payment getPaymentById(long paymentId) {
		String hql = "FROM Payment WHERE id = ?";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setLong(0, paymentId);		
		List<Payment> results = query.list();
		if(results == null || results.isEmpty()){
			return null;
		}
		else {
			return results.get(0);
		}
	}

	@Override
	public void createPaymentOnOrder(long orderid, Payment payment) {		
		payment.setOrder(this.orderRepository.getById(orderid));
		this.sessionFactory.getCurrentSession().save(payment);
	}

	@Override
	public void updatePaymentContent(long paymentId, String key, Object value) {	
		String hql = "UPDATE Payment SET " + key + "=? where id=:paymentid";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, value);
		query.setParameter("paymentid", paymentId);
		query.executeUpdate();
	}

	@Override
	public int deletePayment(long paymentId) {		

		Payment payment = getPaymentById(paymentId);
		if(null == payment){
			return -1;
		}
		this.sessionFactory.getCurrentSession().delete(payment);
		return 0;
	}

	
}
