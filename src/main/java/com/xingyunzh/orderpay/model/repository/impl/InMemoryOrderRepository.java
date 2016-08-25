package com.xingyunzh.orderpay.model.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.xingyunzh.orderpay.model.Order;
import com.xingyunzh.orderpay.model.repository.OrderRepository;

@Repository
public class InMemoryOrderRepository implements OrderRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<Order> getAllOrders() {
		List<Order> results = jdbcTemplate.query("select * from \"order\"", new OrderRowMapper());
		return results;
	}

}
