package com.cg.paymentwallet.repo;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import com.cg.paymentwallet.beans.Customer;
import com.cg.paymentwallet.exception.PaymentWalletException;


public interface WalletRepo {
	public Customer save(Customer customer);
	public Customer findOne(String moblieNo);
	public Map<String, Customer> getDetails();
	public void addTransaction(String transactions);
	public List<String> viewTransaction();
	public boolean checkMobile(String mobile) throws PaymentWalletException;
	
}
