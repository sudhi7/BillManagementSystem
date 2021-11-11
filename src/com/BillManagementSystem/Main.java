package com.BillManagementSystem;

import com.BillManagementSystem.Operations;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("***WELCOME***");
		Operations.AccountsList = new HashMap<>();
		int choice;
		while(true){
			System.out.println("How Can I help you?");
			System.out.println("1. I want to create an account.");
			System.out.println("2. I want to delete my account.");
			System.out.println("3. I want to pay a bill");
			System.out.println("4. I want to add a connection");
			System.out.println("5. I want to add money to wallet");
			System.out.println("5. I want to change my password");
			System.out.println("7. Exit");
			System.out.println("Choose an option...");
			choice = sc.nextInt();
			switch(choice) {
				case 1: Operations.createAccount();
						break;
				case 2: Operations.deleteAccount();
						break;
				case 3: Operations.payBill();
						break;
				case 4: Operations.addConnection();
						break;
				case 5: Operations.addMoney();
						break;
				case 6: Operations.changePassword();
						break;
				case 7: break;
				default: break;
			}
			if(choice<1 || choice>7) {
				throw new Exception("Invalid Choice");
			}
			else if(choice==7) {
				break;
			}
		}
		System.out.println("***THANK YOU***");
		sc.close();
		return;
	}
}
