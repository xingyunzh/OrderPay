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
import com.xingyunzh.orderpay.model.Order;
import com.xingyunzh.orderpay.service.OrderService;

@RestController
@RequestMapping(value="/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseWrapper getall() {
		return new ResponseWrapper(orderService.getAllOrders());
	}
	
	@RequestMapping(value = "/id/{orderid}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseWrapper getById(@PathVariable long orderid) {
		return new ResponseWrapper(orderService.getById(orderid));
	}
	
	@RequestMapping(value = "/customer/{customerid}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseWrapper getByCustomer(@PathVariable(value="customerid") String userid) {
		return new ResponseWrapper(orderService.getByCustomer(userid));
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public ResponseWrapper createOrder(@RequestBody Order order) {
		orderService.createOrder(order);
		return new ResponseWrapper(order);
	}
	
	@RequestMapping(value = "/delete/{orderid}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseWrapper deleteOrder(@PathVariable long orderid) {
		int ret = orderService.deleteOrderById(orderid);
		if(ret == 0){
			return new ResponseWrapper("Order" + orderid + "Deleted");
		}
		else {
			return new ResponseWrapper(ret, "Fail to delete");
		}
	}
	
}
