package com.meritamerica.assignment6.model;

import java.util.ArrayList;
import java.util.List;

public class FraudQueue {
	private static List<Transaction> fraudQList = new ArrayList<Transaction>();
	
	FraudQueue(){
	}
	public static void addTransaction(Transaction transaction) {
		fraudQList.add(transaction);
	}
	public Transaction getTransaction() {
		return null;
	}
}
