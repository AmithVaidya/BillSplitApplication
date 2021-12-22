package com.project.BillSplit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.BillSplit.model.Member;
import com.project.BillSplit.model.Transaction;
import com.project.BillSplit.model.Transactions;
import com.project.BillSplit.repository.MemberRepository;
import com.project.*;


@Service
public class TransactionService {
	
	public ArrayList<Transaction> compute() {
		
		//Get total
		MemberRepository.total=0;
		ArrayList<Member> members = MemberRepository.memberRepo1;
		ArrayList<Transaction> trans = new ArrayList<>();
		
		//Calculate the per person cost
		for(Member temp: members) {
			MemberRepository.total+=temp.getAmount();
		}
		float perMember = MemberRepository.total/members.size();
		
		System.out.println(MemberRepository.total);
		System.out.println(perMember);
		
		//Setting the balance field
		for(Member member: members) {
			member.setBalance(member.getAmount() - perMember);
			System.out.println("Balance: "+member.getBalance());
		}
		
		
		//Computing the transactions

		float absI, absJ, absDiff;
		for(int i=0; i<members.size(); i++) {
				for(int j=0; j<members.size(); j++) {	
					
					if(i!=j && members.get(i).getBalance()>0 &&members.get(j).getBalance()<0) {
						absI = Math.abs(members.get(i).getBalance());
						absJ = Math.abs(members.get(j).getBalance());
						absDiff = absI - absJ;
						if(absI >= absJ) {
							trans.add(new Transaction(members.get(j).getName(), members.get(i).getName(), absJ));
							members.get(i).setBalance(absDiff);
							members.get(j).setBalance(0);
						}
						else {
							trans.add(new Transaction(members.get(j).getName(), members.get(i).getName(), absI));
							members.get(j).setBalance(absDiff);
							members.get(i).setBalance(0);
							
						}
					}//if
					
				
				} 
		}
		
		return trans;
	}
	// to be modified
	public Member getMember(int id) {
		for(Member temp: MemberRepository.memberRepo1) {
			if(temp.getId() == id) return temp;
		}
		return null;
	}
	
	public void addMember(Member member) {
		//MemberRepository.memberRepo.put(member.getId(), member);
		//System.out.println(MemberRepository.memberRepo.get(member.getId()).getName());
		MemberRepository.memberRepo1.add(member);
	}
	
	public void deleteMember(int id) {
		MemberRepository.memberRepo1.remove(id);
	}
	
	public void deleteAllMembers() {
		MemberRepository.memberRepo1 = null;
		MemberRepository.memberRepo1 = new ArrayList<Member>();
		
	}
	
	public void updateMember(Member member) {
		//MemberRepository.memberRepo1.remove(member.getId());
		MemberRepository.memberRepo1.remove(member);
		MemberRepository.memberRepo1.add(member);
	}
	
	public void addMembers(Member membersObj[]) {
		ArrayList<Member> members = MemberRepository.memberRepo1;
		for(Member m: membersObj) 
			members.add(m);
	}
	
	public ArrayList<Member> getMembers() {
		return MemberRepository.memberRepo1;
	}
}
