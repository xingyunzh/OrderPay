package com.xingyunzh.orderpay.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Order implements Serializable {
	private static final long serialVersionUID = 3678107792576131021L;

	private long id = 0;
	private String customerId;
	private String sellerId;
	private String description;
	private BigDecimal totalPrice;
	private List<Payment> payments;
	private List<OrderItem> orderItems;
	private Timestamp createtime;
	private int liveDuration; // seconds, 0 - always alive
	private String callbackURL;
	private OrderState state;

	public enum OrderState {
		OrderStateInit, OrderStatePaid, OrderStateCancelled, OrderStateRefunded, OrderStatePartialRefunded, OrderStateClose
	};

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public int getLiveDuration() {
		return liveDuration;
	}

	public void setLiveDuration(int liveDuration) {
		this.liveDuration = liveDuration;
	}

	public String getCallbackURL() {
		return callbackURL;
	}

	public void setCallbackURL(String callbackURL) {
		this.callbackURL = callbackURL;
	}

	public OrderState getState() {
		return state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = (int) (prime * result + id);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (obj.getClass() != this.getClass()) {
			return false;
		}

		Order objOrder = (Order) obj;
		if (objOrder.id == 0 || id == 0) {
			return false;
		} else {
			return true;
		}
	}

}
