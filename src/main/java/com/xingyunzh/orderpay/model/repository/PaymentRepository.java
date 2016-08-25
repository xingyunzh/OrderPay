package com.xingyunzh.orderpay.model.repository;

import java.util.List;

import com.xingyunzh.orderpay.model.Payment;

public interface PaymentRepository {
	public List<Payment> getPaymentsByOrderId(long orderId);
}
