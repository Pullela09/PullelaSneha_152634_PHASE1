package com.cg.paymentwallet.beans;

import java.math.BigDecimal;

public class Customer {

	
	private String name;
	private String mobileNo;
	private Wallet wallet;
	public Customer() {
		wallet =  new Wallet();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public BigDecimal getWallet() {
		return wallet.getBalance();
	}
	
	public void setWallet(BigDecimal amount) {
		wallet.setBalance(amount);
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", mobileNo=" + mobileNo + ", wallet=" + wallet + "]";
	}
	
	
	
	
}
