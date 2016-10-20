package com.xingyunzh.orderpay.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name="T_ORDER_ITEM")
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 3678107792576131022L;
	private long id;
	private String name;
	private String externalId;
	private Order order;


	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="EXTERNAL_ID")
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	} 
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="orderid")
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
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
		if(this == obj){
			return true;
		}
			
		if(obj == null) {
			return false;
		}
		
		if(obj.getClass() != this.getClass()){
			return false;
		}
		
		OrderItem objOrderItem = (OrderItem)obj;
		if(objOrderItem.getId() == 0 || id == 0){
			return false;
		}
		else {
			return true;
		}
	}
}
