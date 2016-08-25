package com.xingyunzh.orderpay.model.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.xingyunzh.orderpay.model.Order;
import com.xingyunzh.orderpay.model.OrderItem;
import com.xingyunzh.orderpay.model.repository.PaymentRepository;

public class OrderRowMapper implements RowMapper<Order> {

	private NamedParameterJdbcTemplate jdbcTemplate;
	private PaymentRepository paymentRepository;

	public OrderRowMapper(NamedParameterJdbcTemplate jdbcTemplate, PaymentRepository paymentRepository) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.paymentRepository = paymentRepository;
	}

	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order order = new Order();
		order.setId(rs.getLong("id"));
		order.setCustomerId(rs.getString("customerid"));
		order.setSellerId(rs.getString("sellerid"));
		order.setDescription(rs.getString("description"));
		order.setLiveDuration(rs.getInt("liveduration"));
		order.setTotalPrice(rs.getBigDecimal("totalprice"));
		order.setCreatetime(rs.getTimestamp("createtime"));
		order.setCallbackURL(rs.getString("callbackurl"));
		order.setState(Order.OrderState.valueOf(rs.getString("state")));
		
		order.setPayments(paymentRepository.getPaymentsByOrderId(order.getId()));
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", order.getId());
		List<OrderItem> items = jdbcTemplate.query("SELECT * FROM orderitem WHERE orderid = :orderId", param, new OrderItemRowMapper());
		order.setOrderItems(items);
		
		return order;
	}
	
}
