package com.cg.paymentwallet.repo;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cg.paymentwallet.beans.Customer;
import com.cg.paymentwallet.exception.IPaymentWalletException;
import com.cg.paymentwallet.exception.PaymentWalletException;



public class WalletRepoImpl implements WalletRepo {
	private static Map<String, Customer> data=null;
	private static List<String> myList=null;
	
	

	static {
		data=new HashMap<String ,Customer>();
		myList=new ArrayList<String>();
	}

	
public Customer save(Customer customer) {
		
		return data.put(customer.getMobileNo(), customer);
	}


	public Customer findOne(String moblieNo) {
		
		Customer findMobile = null;
		if(data.containsKey(moblieNo)) {
			findMobile = data.get(moblieNo);
			
		}
		return findMobile;
	}


	public Map<String, Customer> getDetails() {
		
		return data;
	}


	public void addTransaction(String transactions) {
	
		myList.add(transactions);
	}


	public List<String> viewTransaction() {
		
		return myList;
	}

	public boolean checkMobile(String mobile) throws PaymentWalletException {
		boolean result=false;
		Customer customer=findOne(mobile);
		if(customer!=null) {
			result=true;
		}
		else {
			throw new PaymentWalletException(IPaymentWalletException.Message4);
			
		}
		
		return result;
		
	}
	

}
