package com.cg.paymentwallet.beans;
import java.math.BigDecimal;

public class Wallet {
	
	private BigDecimal balance;

	public Wallet() {
		
	}
	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Wallet [balance=" + balance + "]";
	}
	
	
	

}
