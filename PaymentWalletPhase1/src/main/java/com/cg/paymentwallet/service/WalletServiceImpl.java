package com.cg.paymentwallet.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;


import com.cg.paymentwallet.beans.Customer;
import com.cg.paymentwallet.exception.IPaymentWalletException;
import com.cg.paymentwallet.exception.PaymentWalletException;
import com.cg.paymentwallet.repo.WalletRepo;
import com.cg.paymentwallet.repo.WalletRepoImpl;

public class WalletServiceImpl implements WalletService {
	WalletRepo repo= null;
	public WalletServiceImpl() {
		
		repo=new WalletRepoImpl();
	}
	public Customer createAccount(Customer customer) {
		
		return repo.save(customer);
	}
	public Customer showBalance(String moblineNo) {
		
		return repo.getDetails().get(moblineNo);
	}
	public List<String> printTransactions(){
		return repo.viewTransaction();
		
	}
	
	public Customer fundTranfer(String sourceMobile, String targetMobile, BigDecimal amount) {
		
		Customer senderFund= repo.findOne(sourceMobile);
		BigDecimal senderBalance = senderFund.getWallet().subtract(amount);
		senderFund.setWallet(senderBalance);
		Customer receiverFund= repo.findOne(targetMobile);
		BigDecimal receiverBalance = receiverFund.getWallet().add(amount);
		receiverFund.setWallet(receiverBalance);
	repo.addTransaction("from  :"+sourceMobile+"to:" +targetMobile+"amount :" +amount+"at" +LocalDateTime.now());
		return repo.getDetails().get(targetMobile);
		
		
		
	}
	public Customer deposit(String mobileNo, BigDecimal amount) {
		Customer customer = new Customer();
		Map<String, Customer> details = repo.getDetails();
		Set<Entry<String, Customer>> entrySet = details.entrySet();
		Iterator<Entry<String, Customer>> it = entrySet.iterator();
		while (it.hasNext()) {
			Map.Entry<String, Customer> entry = it.next();
			if (entry.getKey().equals(mobileNo)) {
				customer = entry.getValue();
			}

		}
		BigDecimal depositAmt = customer.getWallet().add(amount);
		customer.setWallet(depositAmt);
		repo.addTransaction("amountdeposited :"+amount+"balance:" +depositAmt+"at" +LocalDateTime.now());
		
		return repo.getDetails().get(mobileNo);
	
	}

		
		
		
		
		

	public Customer withdraw(String mobileNo, BigDecimal amount) {
		Customer customer = new Customer();
		Map<String, Customer> details = repo.getDetails();
		Set<Entry<String, Customer>> entrySet = details.entrySet();
		Iterator<Entry<String, Customer>> it = entrySet.iterator();
		while (it.hasNext()) {
			Map.Entry<String, Customer> entry = it.next();
			if (entry.getKey().equals(mobileNo)) {
				customer = entry.getValue();
			}

		}
		BigDecimal withdrawAmt = customer.getWallet().subtract(amount);
		customer.setWallet(withdrawAmt);
		repo.addTransaction("amountdeposited :"+amount+"balance:" +withdrawAmt+"at" +LocalDateTime.now());
		return repo.getDetails().get(mobileNo);

		
	
	}
	public boolean validate(String name, String moblieNo, BigDecimal amount) throws PaymentWalletException {
		boolean result = false;
		if ((name.trim().matches("[a-zA-z]*"))) {
			if ((moblieNo.matches("\\d{10}"))) {
				result = true;

			} else {
				throw new PaymentWalletException(IPaymentWalletException.Message2);
			}

		} else {
			throw new PaymentWalletException(IPaymentWalletException.Message1);
		}
		return result;

	}
	public boolean validateBalance(BigDecimal balance ,String mobile) throws PaymentWalletException{
		boolean result=false;
		BigDecimal amt=repo.findOne(mobile).getWallet();
		BigDecimal big=balance.max(amt);
		if(big.equals(amt)) {
			result=true;
		}else {
			throw new PaymentWalletException(IPaymentWalletException.Message3);
		}
		return result;
		
	}
	public boolean checkMobile(String mobile) throws PaymentWalletException {
	
		return repo.checkMobile(mobile);
	}
	
	
	
}
	
	
	

	