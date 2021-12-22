package com.project.BillSplit.model;

public class Transaction {
	
	private String senderName;
	private String recieverName;
	private float amount;
	
	public Transaction(String senderName, String recieverName, float amount) {
		this.amount=amount;
		this.senderName=senderName;
		this.recieverName=recieverName;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getRecieverName() {
		return recieverName;
	}
	public void setRecieverName(String recieverName) {
		this.recieverName = recieverName;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	
}
