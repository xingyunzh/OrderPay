package com.xingyunzh.orderpay.service;

import java.util.List;

import com.xingyunzh.orderpay.model.Payment;

public interface PaymentService {
	public List<Payment> getPaymentsByOrderId(String orderId);
}
