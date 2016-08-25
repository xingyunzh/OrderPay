package com.xingyunzh.orderpay.service;

import java.util.List;

import com.xingyunzh.orderpay.model.Payment;

public interface PaymentService {
	public List<Payment> getAllPayments();
	public List<Payment> getPaymentsByOrderId(long orderid);
	public Payment getPaymentById(long paymentId);
	public int createPaymentOnOrder(long orderid, Payment payment);
	public int updatePaymentState(long paymentId, Payment.State state);
	public int updatePaymentNote(long paymentId, String note);
	public int deletePayment(long paymentId);
}
