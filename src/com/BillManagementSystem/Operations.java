package com.BillManagementSystem;

import com.BillManagementSystem.Utilities.Account;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Operations {
	
	static HashMap<String,Account> AccountsList;
	
	public static void createAccount() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the following details");
		System.out.println("1. Please Enter your Mobile Number");
		final String AccountHolderMobile = sc.next();
		final String verificationStatus = getMobileVerificationStatus(AccountHolderMobile);
		if(verificationStatus.equals("Successful")) {
			System.out.println("2. Please provide a password");
			System.out.println("Remember your password should contain a minimum of 8 characters with atleast 1 alphabet, atleast 1 number and 1 special character");
			final String password = sc.next();
			boolean isValidPassword = validatePassword(password);
			if(isValidPassword==true) {
				System.out.println("3. Please enter the password again");
				final String passwordAgain = sc.next();
				if(passwordAgain.equals(password)) {
					System.out.println("Passwords Match!");
					System.out.println("4. Please Enter your Name");
					String AccountHolderName = sc.next();
					if(AccountHolderName.length()==0) {
						throw new Exception("Account Holder Name Cannot be Empty");
					}
					else {
						System.out.println("5.Add wallet amount. (Please add a minimum of 1000 rupees into the wallet)");
						long amount = sc.nextInt();
						if(amount<1000) {
							throw new Exception("You need to add a minimum of 1000 Rupees into your wallet");
						}
						else {
							Account personalAccount = new Account(AccountHolderMobile, password, AccountHolderName, amount);
							AccountsList.put(AccountHolderMobile, personalAccount);
							System.out.println("Your account is created successfully!");
						}
					}
				}
				else {
					throw new Exception("The passwords you entered do not match");
				}
			}
			else {
				throw new Exception("Invalid password");
			}
		}
		else {
			throw new Exception(verificationStatus);
		}
	}
	
	private static void deletePersonalAccount(String mobileNumber) {
		AccountsList.remove(mobileNumber);
		return;
	}
	
	public static void deleteAccount() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please provide your registered mobile number");
		String mobileNumber = sc.next();
		boolean isValidMobile = validateMobile(mobileNumber);
		if(isValidMobile) {
			if(isExistingMobileNumber(mobileNumber)) {
				System.out.println("Please provide your password");
				final String givenPassword = sc.next();
				final String actualPassword = AccountsList.get(mobileNumber).getAccountPassword();
				if(givenPassword.equals(actualPassword)) {
					System.out.println("Are you sure you want to delete your account?");
					System.out.println("1. Yes");
					System.out.println("2. No");
					String choice = sc.next();
					if(choice.equals("1")) {
						deletePersonalAccount(mobileNumber);
						System.out.println("Your account is deleted..Lets Meet Soon :) ");
					}
					else if(choice.equals("2")) {
						System.out.println("We appreciate your decision... Your account is not deleted !!");
					}
					else {
						throw new Exception("Invalid Choice");
					}
				}
				else {
					throw new Exception("You have entered wrong password !!");
				}
			}
			else {
				throw new Exception("No account exists with the mobile number you entered");
			}
		}
		else {
			throw new Exception("The mobile number you entered is Invalid");
		}
		return;
	}
	
	public static void payBill() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please provide your registered mobile number");
		String mobileNumber = sc.next();
		boolean isValidMobile = validateMobile(mobileNumber);
		if(isValidMobile) {
			if(isExistingMobileNumber(mobileNumber)) {
				Account myAccount = AccountsList.get(mobileNumber);
				System.out.println("Please provide your password");
				final String givenPassword = sc.next();
				final String actualPassword = myAccount.getAccountPassword();
				if(givenPassword.equals(actualPassword)) {
					if(myAccount.getActiveBillsCount()==0) {
						System.out.println("You have no active bills to pay");
					}
					else {
						System.out.println("You have " + myAccount.getActiveBillsCount() + "active bills to pay");
						System.out.println("Choose which Bill do you want to pay first");
						HashSet<Integer> SetOfActiveConnections = myAccount.getActiveBills();
						int choice = sc.nextInt();
						if(SetOfActiveConnections.contains(choice)) {
							if(choice==1) {
								if(myAccount.getTelevisionConnection().getDue()>myAccount.getWalletBalance()) {
									throw new Exception("Wallet Balance not sufficient");
								}
								else {
									myAccount.deductWalletBalance(myAccount.getTelevisionConnection().getDue());
									myAccount.removeTelevisionConnection();
								}
							}
							else if(choice==2) {
								if(myAccount.getBroadbandConnection().getDue()>myAccount.getWalletBalance()) {
									throw new Exception("Wallet Balance not sufficient");
								}
								else {
									myAccount.deductWalletBalance(myAccount.getBroadbandConnection().getDue());
									myAccount.removeBroadbandConnection();
								}
							}
							else if(choice==3) {
								if(myAccount.getWaterConnection().getDue()>myAccount.getWalletBalance()) {
									throw new Exception("Wallet Balance not sufficient");
								}
								else {
									myAccount.deductWalletBalance(myAccount.getWaterConnection().getDue());
									myAccount.removeWaterConnection();
								}
							}
							else if(choice==4) {
								if(myAccount.getPowerConnection().getDue()>myAccount.getWalletBalance()) {
									throw new Exception("Wallet Balance not sufficient");
								}
								else {
									myAccount.deductWalletBalance(myAccount.getPowerConnection().getDue());
									myAccount.removePowerConnection();
								}
							}
						}
						else {
							throw new Exception("Invalid Choice");
						}
					}
				}
				else {
					throw new Exception("You have entered wrong password !!");
				}
			}
			else {
				throw new Exception("No account exists with the mobile number you entered");
			}
		}
		else {
			throw new Exception("The mobile number you entered is Invalid");
		}
		return;
	}
	
	private static boolean validateMobile(String MobileNumber) {
		if(MobileNumber.length()!=10) {
			return false;
		}
		else {
			int f=1;
			for(int i=0;i<10;i++) {
				if(MobileNumber.charAt(i)>=48 && MobileNumber.charAt(i)<=57) {
					continue;
				}
				else {
					f=0;
					break;
				}
			}
			if(f==0)
				return false; 
		}
		return true;
	}
	
	private static boolean isExistingMobileNumber(String MobileNumber) {
		return AccountsList.containsKey(MobileNumber);
	}
	
	private static String getMobileVerificationStatus(String MobileNumber) {
		if(validateMobile(MobileNumber)==false)
			return "The mobile number you entered is Invalid";
		if(isExistingMobileNumber(MobileNumber)) {
			return "An Account With This Mobile Number Already Exists";
		}
		return "Successful";
	}
	
	private static boolean validatePassword(String password) {
		if(password.length()<8)
			return false;
		int spCharCount = 0, digitCount = 0, alphaCount = 0;
		password.toLowerCase();
		for(int i=0; i<password.length(); i++) {
			if(password.charAt(i)>=48 && password.charAt(i)<=57) {
				++digitCount;
			}
			else if(password.charAt(i)>=97 && password.charAt(i)<=122) {
				++alphaCount;
			}
			else {
				++spCharCount;
			}
		}
		if(digitCount>=1 && alphaCount>=1 && spCharCount>=1) {
			return true;
		}
		return false;
	}
	
	public static void addMoney() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please provide your registered mobile number");
		String mobileNumber = sc.next();
		boolean isValidMobile = validateMobile(mobileNumber);
		if(isValidMobile) {
			if(isExistingMobileNumber(mobileNumber)) {
				Account myAccount = AccountsList.get(mobileNumber);
				System.out.println("Please provide your password");
				final String givenPassword = sc.next();
				final String actualPassword = myAccount.getAccountPassword();
				if(givenPassword.equals(actualPassword)) {
					System.out.println("Enter amount to be added");
					System.out.println("Remeber you cannot add amount less than 100");
					int amount = sc.nextInt();
					if(amount<100) {
						throw new Exception("Cannot add amount less than 100");
					}
					else {
						myAccount.addAmountToWallet(amount);
					}
				}
				else {
					throw new Exception("You have entered wrong password !!");
				}
			}
			else {
				throw new Exception("No account exists with the mobile number you entered");
			}
		}
		else {
			throw new Exception("The mobile number you entered is Invalid");
		}
		return;
	}
	
	public static void changePassword() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please provide your registered mobile number");
		String mobileNumber = sc.next();
		boolean isValidMobile = validateMobile(mobileNumber);
		if(isValidMobile) {
			if(isExistingMobileNumber(mobileNumber)) {
				Account myAccount = AccountsList.get(mobileNumber);
				System.out.println("Please provide your password");
				final String givenPassword = sc.next();
				final String actualPassword = myAccount.getAccountPassword();
				if(givenPassword.equals(actualPassword)) {
					System.out.println("Please provide a new password");
					System.out.println("Remember your password should contain a minimum of 8 characters with atleast 1 alphabet, atleast 1 number and 1 special character");
					final String password = sc.next();
					boolean isValidPassword = validatePassword(password);
					if(isValidPassword==true) {
						System.out.println("Please enter the new password again");
						final String passwordAgain = sc.next();
						if(passwordAgain.equals(password)) {
							System.out.println("Passwords Reset successful!");
						}
						else {
							throw new Exception("The passwords you entered do not match");
						}
					}
					else {
						throw new Exception("Invalid password");
					}
				}
				else {
					throw new Exception("You have entered wrong password !!");
				}
			}
			else {
				throw new Exception("No account exists with the mobile number you entered");
			}
		}
		else {
			throw new Exception("The mobile number you entered is Invalid");
		}
		return;
	}
	
	public static void addConnection() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please provide your registered mobile number");
		String mobileNumber = sc.next();
		boolean isValidMobile = validateMobile(mobileNumber);
		if(isValidMobile) {
			if(isExistingMobileNumber(mobileNumber)) {
				Account myAccount = AccountsList.get(mobileNumber);
				System.out.println("Please provide your password");
				final String givenPassword = sc.next();
				final String actualPassword = myAccount.getAccountPassword();
				if(givenPassword.equals(actualPassword)) {
					System.out.println("Please choose which connection do you want to add");
					System.out.println("1. Television Connection");
					System.out.println("2. Broadband Connection");
					System.out.println("3. Water Connection");
					System.out.println("4. Power Connection");
					int choice = sc.nextInt();
					if(choice==1) {
						myAccount.addTelevisionConnection();
					}
					else if(choice==2) {
						myAccount.addBroadbandConnection();
					}
					else if(choice==3) {
						myAccount.addWaterConnection();
					}
					else if(choice==4) {
						myAccount.addPowerConnection();
					}
					else {
						throw new Exception("Invalid Choice");
					}
				}
				else {
					throw new Exception("You have entered wrong password !!");
				}
			}
			else {
				throw new Exception("No account exists with the mobile number you entered");
			}
		}
		else {
			throw new Exception("The mobile number you entered is Invalid");
		}
		return;
	}
}
