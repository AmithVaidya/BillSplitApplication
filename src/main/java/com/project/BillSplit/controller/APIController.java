package com.project.BillSplit.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.BillSplit.model.Member;
import com.project.BillSplit.model.Transaction;
import com.project.BillSplit.model.Transactions;
import com.project.BillSplit.service.TransactionService;

@RestController
public class APIController {

	@Autowired
	private TransactionService service;
	
	@RequestMapping(value="/" , method=RequestMethod.GET)
	public ResponseEntity<Object> display(){
		Member m = new Member(10, "AmithBond", 100, 1000);
		return new ResponseEntity<>(m.toString(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getTransactions" , method=RequestMethod.GET)
	public ResponseEntity<ArrayList<Transaction>> getTransactions() {
		return new ResponseEntity<>(service.compute(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getMember" , method=RequestMethod.GET)
	public ResponseEntity<Member> getMembers(@RequestParam String id) {
		return new ResponseEntity<>(service.getMember(Integer.parseInt(id)), HttpStatus.OK);
	}
	
	@RequestMapping(value="/addMember", method=RequestMethod.POST)
	public ResponseEntity<String> addMember(@RequestBody Member member) {
		service.addMember(member);
		return new ResponseEntity<>("Created member.", HttpStatus.OK);
	}
	
	@RequestMapping(value="/addMembers", method=RequestMethod.POST)
	public ResponseEntity<String> addMembers(@RequestBody Member members[]) {
		service.addMembers(members);
		return new ResponseEntity<>("Created member.", HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/updateMember", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateMember(@RequestBody Member member){
		service.updateMember(member);
		return new ResponseEntity<>("Updated member.", HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteMember/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteMember(@PathVariable String id) {
		String msg="Deleted memeber with id: "+id;
		service.deleteMember(Integer.parseInt(id));
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteAllMembers", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteMember() {
		String msg="Deleted all memebers";
		service.deleteAllMembers();
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/getMembers" , method=RequestMethod.GET)
	public ResponseEntity<ArrayList<Member>> getMembers() {
		return new ResponseEntity<>(service.getMembers(), HttpStatus.OK);
	}
	
	
}
