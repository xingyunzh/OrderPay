package com.xingyunzh.orderpay.model.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.xingyunzh.orderpay.model.Order;
import com.xingyunzh.orderpay.model.OrderItem;
import com.xingyunzh.orderpay.model.repository.OrderRepository;
import com.xingyunzh.orderpay.model.repository.PaymentRepository;

public class InMemoryOrderRepository implements OrderRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public List<Order> getAllOrders() {
		List<Order> results = jdbcTemplate.query("select * from \"order\"",
				new OrderRowMapper(jdbcTemplate, paymentRepository));
		return results;
	}

	@Override
	public List<Order> getByCustomer(String userid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userid", userid);
		List<Order> results = jdbcTemplate.query("select * from \"order\" where customerid = :userid", params,
				new OrderRowMapper(jdbcTemplate, paymentRepository));
		return results;
	}

	@Override
	public Order getById(long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<Order> results = jdbcTemplate.query("select * from \"order\" where id = :id", params,
				new OrderRowMapper(jdbcTemplate, paymentRepository));
		if (!results.isEmpty()) {
			return results.get(0);
		} else {
			return null;
		}
	}

	@Override
	public long createOrder(final Order order) {
		String sql = "insert into \"order\"(customerid, sellerid, description,totalprice,createtime, liveduration, callbackurl,state) values(:customerid, :sellerid, :description, :totalprice, :createtime, :liveduration, :callbackurl, :state);";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customerid", order.getCustomerId());
		params.put("sellerid", order.getSellerId());
		params.put("description", order.getDescription());
		params.put("totalprice", order.getTotalPrice());
		params.put("createtime", order.getCreatetime());
		params.put("liveduration", order.getLiveDuration());
		params.put("callbackurl", order.getCallbackURL());
		params.put("state", order.getState().toString());
		
		MapSqlParameterSource source = new MapSqlParameterSource(params);
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(sql, source, keyHolder);
		
		return keyHolder.getKey().longValue();
	}

	@Override
	public void createOrderItemsForOrder(long orderid, List<OrderItem> items) {
		String sql = "insert into orderitem(name, externalid, orderid) values(:name, :externalid, :orderid);";
		
		for(OrderItem item : items){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name", item.getName());
			params.put("externalid", item.getExternalId());
			params.put("orderid", orderid);
			
			MapSqlParameterSource source = new MapSqlParameterSource(params);
			GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(sql, source, keyHolder);
			
			long itemid = keyHolder.getKey().longValue();
			item.setId(itemid);			
		}
	}

	
	
	@Override
	public void deleteOrderItem(long id) {
		String sql = "delete from orderitem where id = :id";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		jdbcTemplate.update(sql, param);
	}

	@Override
	public void deleteOrder(long id) {
		String sql = "delete from \"order\" where id = :id";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		jdbcTemplate.update(sql, param);
	}
}


