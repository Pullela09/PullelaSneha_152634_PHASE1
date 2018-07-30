package com.cg.paymentwallet.pl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cg.paymentwallet.beans.Customer;
import com.cg.paymentwallet.exception.PaymentWalletException;
import com.cg.paymentwallet.service.WalletService;
import com.cg.paymentwallet.service.WalletServiceImpl;

public class Client {

	public static void main(String[] args) {
		int choice;
		int choice1;
		boolean result = false;
		Scanner scanner = new Scanner(System.in);
		do {

			System.out.println("ENTER CHOICE");
			System.out.println("1.CREATE ACCOUNT");
			System.out.println("2.LOGIN");
			System.out.println("3.EXIT");
			choice = scanner.nextInt();
			Customer customer = new Customer();
			WalletService service = new WalletServiceImpl();
			switch (choice) {
			case 1:
				try

				{
					System.out.println("***********create account***********");
					System.out.println("Enter name");

					String name = scanner.next();
					System.out.println("Enter mobile no");
					String moblieNo = scanner.next();
					System.out.println("Enter amount");
					BigDecimal amount = scanner.nextBigDecimal();

					result = service.validate(name, moblieNo, amount);
					if (result) {
						customer.setName(name);
						customer.setMobileNo(moblieNo);
						customer.setWallet(amount);
						service.createAccount(customer);
						System.out.println("Name:" +customer.getName());
						System.out.println("Mobile Number" +customer.getMobileNo());
						System.out.println("Balance" +customer.getWallet());
						System.out.println("Account created successfully");
					}
				} catch (PaymentWalletException e) {
					System.out.println(e.getMessage());
				}

				break;

			case 2:
				System.out.println("**********login***********");
				System.out.println("Enter your Mobile Number");
				String mobile = scanner.next();
				try {
					if(service.checkMobile(mobile)) {
				
				do {
					System.out.println("1.Show Balance");
					System.out.println("2.Fund Tranfer");
					System.out.println("3.Deposit Amount");
					System.out.println("4.Withdraw Amount");
					System.out.println("5.Print Transactions");
					System.out.println("6.EXIT");
					System.out.println("Enter Choice");
					choice1 = scanner.nextInt();
					switch (choice1) {
					case 1:
						System.out.println("******Check your balance******");
						Customer balanceShow = service.showBalance(mobile);
						System.out.println(balanceShow);
						break;

					case 2:
						System.out.println("******Fund Tranfer******");
						System.out.println("Enter the Sender's Mobile number");
						String mobileNo3 = scanner.next();
						System.out.println("Enter the Receiver's Mobile number");
						String mobileNo4 = scanner.next();
						System.out.println("Enter the amount to be transferred");
						BigDecimal amount34 = scanner.nextBigDecimal();
						Customer transfer = service.fundTranfer(mobileNo3, mobileNo4, amount34);
						System.out.println(transfer);

						break;
					case 3:
						System.out.println("******Deposit amount******");
						//System.out.println("Enter your Mobile number");
						//String mobileNo1 = scanner.next();
						System.out.println("Enter the amount to be deposited");
						BigDecimal amount1 = scanner.nextBigDecimal();
						Customer deposit = service.deposit(mobile, amount1);
						System.out.println(deposit);
						break;

					case 4:
						System.out.println("******Withdraw amount******");
						//System.out.println("Enter your mobile number");
						//String mobileNo2 = scanner.next();
						System.out.println("Enter the amount to be withdrawn");
						BigDecimal amount2 = scanner.nextBigDecimal();
						try {
							service.validateBalance(amount2, mobile);
						} catch (PaymentWalletException e) {
							
							System.out.println(e.getMessage());
						}
						Customer withdraw = service.withdraw(mobile, amount2);
						System.out.println(withdraw);
						break;
					case 5:
						System.out.println("******Print Transactions******");
						List<String> details=new ArrayList<String>();
						details=service.printTransactions();
						System.out.println(details);
						break;
						
						
					case 6:
						System.exit(0);
						break;
					}

				} while (choice1 != 6);
					}
				}catch (PaymentWalletException e) {
					System.out.println(e.getMessage());
					break;
				}

			case 3:
				System.exit(0);
				break;

			}

		} while (choice != 3);

	}

}
