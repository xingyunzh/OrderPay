package com.xingyunzh.orderpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xingyunzh.orderpay.dto.ResponseWrapper;
import com.xingyunzh.orderpay.model.Payment;
import com.xingyunzh.orderpay.service.PaymentService;

@RestController
@RequestMapping("/pay")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping(value="/getall", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseWrapper getall() {
		return new ResponseWrapper(paymentService.getAllPayments());
	}
	
	@RequestMapping(value="/id/{paymentid}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseWrapper getPaymentById(@PathVariable long paymentid) {
		return new ResponseWrapper(paymentService.getPaymentById(paymentid));
	}
	
	@RequestMapping(value="/order/{orderid}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseWrapper getPaymentByOrderId(@PathVariable long orderid) {
		return new ResponseWrapper(paymentService.getPaymentsByOrderId(orderid));
	}
	
	@RequestMapping(value="/create/{orderid}", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public ResponseWrapper createPaymentOnOrder(@PathVariable long orderid, @RequestBody Payment payment) {
		int ret = paymentService.createPaymentOnOrder(orderid, payment);
		if(ret < 0){
			return new ResponseWrapper(ret, "fail to create payment.");
		}
		else {
			return new ResponseWrapper(ret, "success to create payment.");
		}
	}

	@RequestMapping(value="/id/{paymentid}/state/{state}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseWrapper updatePaymentState(@PathVariable long paymentid, @PathVariable String state) {
		int ret = paymentService.updatePaymentState(paymentid, Payment.State.valueOf(state));
		if(ret < 0){
			return new ResponseWrapper(ret, "fail to update payment state.");
		}
		else {
			return new ResponseWrapper(ret, "success to update payment state.");
		}
	}
	
	@RequestMapping(value="/id/{paymentid}/note/{note}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseWrapper updatePaymentNote(@PathVariable long paymentid, @PathVariable String note) {
		int ret = paymentService.updatePaymentNote(paymentid, note);
		if(ret < 0){
			return new ResponseWrapper(ret, "fail to update payment note.");
		}
		else {
			return new ResponseWrapper(ret, "success to update payment note.");
		}
	}
	
	@RequestMapping(value="/delete/{paymentid}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseWrapper deletePayment(@PathVariable long paymentid) {
		int ret = paymentService.deletePayment(paymentid);
		if(ret < 0){
			return new ResponseWrapper(ret, "fail to delete payment.");
		}
		else {
			return new ResponseWrapper(ret, "success to delete payment.");
		}
	}
	
}
