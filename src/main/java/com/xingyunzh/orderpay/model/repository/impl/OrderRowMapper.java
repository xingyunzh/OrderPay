package com.xingyunzh.orderpay.model.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.xingyunzh.orderpay.model.Order;

public class OrderRowMapper implements RowMapper<Order> {

	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order order = new Order();
		order.setId(rs.getLong("id"));
		order.setCustomerId(rs.getString("customerid"));
		order.setSellerId(rs.getString("sellerid"));
		order.setDescription(rs.getString("description"));
		order.setLiveDuration(rs.getInt("liveduration"));
		order.setTotalPrice(rs.getBigDecimal("totalprice"));
		order.setState(Order.OrderState.valueOf(rs.getString("state")));
		
		return order;
	}
	
}
