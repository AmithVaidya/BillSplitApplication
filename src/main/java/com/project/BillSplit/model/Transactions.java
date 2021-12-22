package com.project.BillSplit.model;

import java.util.ArrayList;

public class Transactions {
	
	private ArrayList<String> sender;
	private ArrayList<String> reciver;
	private ArrayList<Float> amount;
	
	public ArrayList<String> getSender() {
		return sender;
	}
	public void setSender(ArrayList<String> sender) {
		this.sender = sender;
	}
	public ArrayList<String> getReciver() {
		return reciver;
	}
	public void setReciver(ArrayList<String> reciver) {
		this.reciver = reciver;
	}
	public ArrayList<Float> getAmount() {
		return amount;
	}
	public void setAmount(ArrayList<Float> amount) {
		this.amount = amount;
	}
	
}
