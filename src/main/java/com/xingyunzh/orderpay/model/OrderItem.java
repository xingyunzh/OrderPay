package com.xingyunzh.orderpay.model;

import java.io.Serializable;

public class OrderItem implements Serializable {
	private static final long serialVersionUID = 3678107792576131022L;
	private long id;
	private String name;
	private String externalId;
	

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
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
