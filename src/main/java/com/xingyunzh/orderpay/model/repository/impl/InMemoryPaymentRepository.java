package com.xingyunzh.orderpay.model.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.xingyunzh.orderpay.model.Payment;
import com.xingyunzh.orderpay.model.repository.PaymentRepository;

@Repository
public class InMemoryPaymentRepository implements PaymentRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<Payment> getPaymentsByOrderId(long orderid) {
		String sql = "SELECT * FROM payment WHERE orderid = :orderId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderId", orderid);
		List<Payment> results = jdbcTemplate.query(sql, params, new PaymentRowMapper());
		return results;
	}

	@Override
	public List<Payment> getAllPayments() {
		String sql = "SELECT * FROM payment";
		List<Payment> results = jdbcTemplate.query(sql, new PaymentRowMapper());
		return results;
	}
	
	@Override
	public Payment getPaymentById(long paymentId) {
		String sql = "SELECT * FROM payment WHERE id = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", paymentId);
		List<Payment> results = jdbcTemplate.query(sql, params, new PaymentRowMapper());
		if(results == null || results.isEmpty()){
			return null;
		}
		else {
			return results.get(0);
		}
	}

	@Override
	public void createPaymentOnOrder(long orderid, Payment payment) {
		String sql = "insert into payment(amount, paytime, fromaccount,toaccount,referencetext,note,state,\"type\",orderid) values(:amount, :paytime, :fromaccount, :toaccount, :referencetext, :note, :state, :type, :orderid);";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderid", orderid);
		params.put("amount", payment.getAmount());
		params.put("paytime", payment.getPaytime());
		params.put("fromaccount", payment.getFromAccount());
		params.put("toaccount", payment.getToAccount());
		params.put("referencetext", payment.getReferenceText());
		params.put("note", payment.getNote());
		params.put("state", payment.getState().toString());
		params.put("type", payment.getType().toString());
		
		MapSqlParameterSource source = new MapSqlParameterSource(params);
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(sql, source, keyHolder);
		
		long id = keyHolder.getKey().longValue();
		payment.setId(id);
	}

	@Override
	public void updatePaymentContent(long paymentId, String key, Object value) {
		String sql = "update payment set " + key + "=:value where id=:paymentid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("value", value);
		params.put("paymentid", paymentId);
		
		jdbcTemplate.update(sql, params);
	}

	@Override
	public int deletePayment(long paymentId) {
		if(paymentId == 0){
			return -1;
		}
		String sql = "delete from payment where id = :id";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", paymentId);
		jdbcTemplate.update(sql, param);
		return 0;
	}

	
}
