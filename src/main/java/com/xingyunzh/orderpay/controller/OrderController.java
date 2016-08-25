package com.xingyunzh.orderpay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xingyunzh.orderpay.model.Order;
import com.xingyunzh.orderpay.service.OrderService;

@RestController
@RequestMapping(value="/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Order> getall() {
		return orderService.getAllOrders();
	}
	
}
