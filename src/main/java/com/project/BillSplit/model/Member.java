package com.project.BillSplit.model;

public class Member {
	
	@Override
	public String toString() {
		return "{\"id\" :" + id + ", \"name\": \" " + name + " \" , \"amount\":" + amount + ", \"balance\":" + balance + "}";
	}
	private int id;
	private String name;
	private float amount;
	private float balance;
	
	

	public Member(int id, String name, float amount, float balance) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.balance = balance;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
}
