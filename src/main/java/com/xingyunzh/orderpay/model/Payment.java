package com.xingyunzh.orderpay.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="T_PAYMENT")
public class Payment implements Serializable {
	private static final long serialVersionUID = 3678107792576131025L;
	
	private long id;
	private BigDecimal amount;
	private Timestamp paytime;
	private String fromAccount;
	private String toAccount;
	private String referenceText;
	private String note;
	private State state;
	private Type type;
	private Order order;



	public enum State {
		PaymentStateSuccess,
		PaymentStateFailure,
		PaymentStateInProcessing,
		PaymentStateInterupted
	};
	
	public enum Type {
		PaymentTypeAlipay,
		PaymentTypeWeChat,
		PaymentTypeEBank,
		PaymentTypeOfflineCash
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name="AMOUNT")
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Column(name="PAY_TIME")
	public Timestamp getPaytime() {
		return paytime;
	}

	public void setPaytime(Timestamp paytime) {
		this.paytime = paytime;
	}

	@Column(name="FROM_ACCOUNT")
	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	@Column(name="TO_ACCOUNT")
	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	@Column(name="REFERENCE_TEXT")
	public String getReferenceText() {
		return referenceText;
	}

	public void setReferenceText(String referenceText) {
		this.referenceText = referenceText;
	}

	@Column(name="NOTE")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name="STATE")
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	@Column(name="TYPE")
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
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
		
		Payment objPayment = (Payment)obj;
		if(objPayment.getId() == 0 || id == 0){
			return false;
		}
		else {
			return true;
		}
	}
	
}
