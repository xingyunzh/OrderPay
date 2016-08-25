package com.xingyunzh.orderpay.model.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.xingyunzh.orderpay.model.OrderItem;

public class OrderItemRowMapper implements RowMapper<OrderItem> {
	@Override
	public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrderItem item = new OrderItem();
		item.setId(rs.getLong("id"));
		item.setName(rs.getString("name"));
		item.setExternalId(rs.getString("externalid"));
		return item;
	}
}
