package com.xingyunzh.orderpay.model.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.xingyunzh.orderpay.model.Payment;
import com.xingyunzh.orderpay.model.repository.PaymentRepository;

@Repository
public class InMemoryPaymentRepository implements PaymentRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<Payment> getPaymentsByOrderId(long orderId) {
		String sql = "SELECT * FROM payment WHERE orderid = :orderId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderId", orderId);
		List<Payment> results = jdbcTemplate.query(sql, params, new PaymentRowMapper());
		return results;
	}

}
