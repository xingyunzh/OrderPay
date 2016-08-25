package com.xingyunzh.orderpay.model.repository;

import java.util.List;

import com.xingyunzh.orderpay.model.Payment;

public interface PaymentRepository {
	public List<Payment> getAllPayments();
	public List<Payment> getPaymentsByOrderId(long orderid);
	public Payment getPaymentById(long paymentId);
	public void createPaymentOnOrder(long orderid, Payment payment);
	public void updatePaymentContent(long paymentId, String key, Object value);
	public int deletePayment(long paymentId);
}
