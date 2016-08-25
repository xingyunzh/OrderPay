package com.xingyunzh.orderpay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingyunzh.orderpay.model.Order;
import com.xingyunzh.orderpay.model.Payment;
import com.xingyunzh.orderpay.model.Payment.State;
import com.xingyunzh.orderpay.model.repository.OrderRepository;
import com.xingyunzh.orderpay.model.repository.PaymentRepository;
import com.xingyunzh.orderpay.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public List<Payment> getAllPayments() {
		return paymentRepository.getAllPayments();
	}

	@Override
	public List<Payment> getPaymentsByOrderId(long orderid) {
		return paymentRepository.getPaymentsByOrderId(orderid);
	}

	@Override
	public Payment getPaymentById(long paymentId) {
		return paymentRepository.getPaymentById(paymentId);
	}

	@Override
	public int createPaymentOnOrder(long orderid, Payment payment) {
		Order order = orderRepository.getById(orderid);
		if(order == null){
			return -1;
		}
		
		paymentRepository.createPaymentOnOrder(orderid, payment);
		return 0;
	}

	@Override
	public int updatePaymentState(long paymentId, State state) {
		Payment payment = paymentRepository.getPaymentById(paymentId);
		if(payment == null){
			return -1;
		}
		paymentRepository.updatePaymentContent(paymentId, "state", state.toString());
		return 0;
	}

	@Override
	public int updatePaymentNote(long paymentId, String note) {
		Payment payment = paymentRepository.getPaymentById(paymentId);
		if(payment == null){
			return -1;
		}
		paymentRepository.updatePaymentContent(paymentId, "note", note);
		return 0;
	}

	@Override
	public int deletePayment(long paymentId) {
		Payment payment = paymentRepository.getPaymentById(paymentId);
		if(payment == null){
			return -1;
		}
		
		return paymentRepository.deletePayment(paymentId);
	}
}
