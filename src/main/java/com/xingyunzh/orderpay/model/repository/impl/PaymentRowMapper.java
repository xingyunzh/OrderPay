package com.xingyunzh.orderpay.model.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.xingyunzh.orderpay.model.Payment;

public class PaymentRowMapper implements RowMapper<Payment> {

	@Override
	public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Payment pay = new Payment();
		pay.setId(rs.getLong("id"));
		pay.setAmount(rs.getBigDecimal("amount"));
		pay.setFromAccount(rs.getString("fromaccount"));
		pay.setToAccount(rs.getString("toaccount"));
		pay.setReferenceText(rs.getString("referencetext"));
		pay.setPaytime(rs.getTimestamp("paytime"));
		pay.setState(Payment.State.valueOf(rs.getString("state")));
		pay.setType(Payment.Type.valueOf(rs.getString("type")));
		pay.setNote(rs.getString("note"));
		
		return pay;
	}

}
