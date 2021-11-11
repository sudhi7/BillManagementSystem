package com.BillManagementSystem.Utilities;

import java.util.HashSet;
import java.util.UUID;

public class Account {
	private String AccountHolderName;
	private String AccountPassword;
	private String AccountHolderMobile;
	private long WalletBalance;
	private int ActiveBillsCount;
	private Connection TelevisionConnection;
	private Connection BroadbandConnection;
	private Connection WaterConnection;
	private Connection PowerConnection;
	
	public Account(String AccountHolderMobile, String password, String AccountHolderName, long amount) {
		this.AccountHolderMobile = AccountHolderMobile;
		this.AccountPassword = password;
		this.AccountHolderName = AccountHolderName;
		this.WalletBalance = amount;
		this.ActiveBillsCount = 0;
	}
	
	public void addAmountToWallet(int amount) {
		this.WalletBalance+=amount;
		System.out.println("Amount added successfully!");
		System.out.println("Wallet Balance is Rs." + this.WalletBalance);
		return;
	}
	
	public void addWaterConnection() throws Exception {
		if(WaterConnection!=null) {
			throw new Exception("Water Connection already exists");
		}
		else {
			int min, max, due;
			max = 10000;
			min = 100;
			due = (int) Math.random()*(max-min+1)+min;;
			WaterConnection = new Connection(UUID.randomUUID(), due);
			++this.ActiveBillsCount;
			System.out.println("Water Connection added successfully!");
		}
		return;
	}
	
	public void addTelevisionConnection() throws Exception {
		if(TelevisionConnection!=null) {
			throw new Exception("Television Connection already exists");
		}
		else {
			int min, max, due;
			max = 10000;
			min = 100;
			due = (int) Math.random()*(max-min+1)+min;;
			TelevisionConnection = new Connection(UUID.randomUUID(), due);
			++this.ActiveBillsCount;
			System.out.println("Television Connection added successfully!");
		}
		return;
	}
	
	public void addPowerConnection() throws Exception {
		if(PowerConnection!=null) {
			throw new Exception("Power Connection already exists");
		}
		else {
			int min, max, due;
			max = 10000;
			min = 100;
			due = (int) Math.random()*(max-min+1)+min;;
			PowerConnection = new Connection(UUID.randomUUID(), due);
			++this.ActiveBillsCount;
			System.out.println("Power Connection added successfully!");
		}
		return;
	}
	
	public void addBroadbandConnection() throws Exception {
		if(BroadbandConnection!=null) {
			throw new Exception("Broadband Connection already exists");
		}
		else {
			int min, max, due;
			max = 10000;
			min = 100;
			due = (int) Math.random()*(max-min+1)+min;;
			BroadbandConnection = new Connection(UUID.randomUUID(), due);
			++this.ActiveBillsCount;
			System.out.println("Broadband Connection added successfully!");
		}
		return;
	}
	
	public void removeTelevisionConnection() {
		this.TelevisionConnection = null;
		--this.ActiveBillsCount;
		System.out.println("Television Connection is removed!");
		return;
	}
	
	public void removeBroadbandConnection() {
		this.BroadbandConnection = null;
		--this.ActiveBillsCount;
		System.out.println("Broadband Connection is removed!");
		return;
	}
	
	public void removeWaterConnection() {
		this.WaterConnection = null;
		--this.ActiveBillsCount;
		System.out.println("Water Connection is removed!");
		return;
	}
	
	public void removePowerConnection() {
		this.PowerConnection = null;
		--this.ActiveBillsCount;
		System.out.println("Power Connection is removed!");
		return;
	}
	
	public void deductWalletBalance(int bill) {
		this.WalletBalance-=bill;
		System.out.println("Payment Successful!");
		System.out.println("Wallet Balance is Rs." + this.WalletBalance);
		return;
	}
	
	public long getWalletBalance() {
		return this.WalletBalance;
	}
	
	public Connection getTelevisionConnection() {
		return this.TelevisionConnection;
	}
	
	public Connection getBroadbandConnection() {
		return this.BroadbandConnection;
	}
	
	public Connection getWaterConnection() {
		return this.WaterConnection;
	}
	
	public Connection getPowerConnection() {
		return this.PowerConnection;
	}
	
	public String getAccountPassword() {
		return this.AccountPassword;
	}
	
	public int getActiveBillsCount() {
		return ActiveBillsCount;
	}

	public HashSet<Integer> getActiveBills() {
		System.out.println("Account Id : " + this.AccountHolderMobile);
		System.out.println("Account Holder Name : " + this.AccountHolderName);
		System.out.println("Wallet Balance : " + this.WalletBalance);
		HashSet<Integer> SetOfActiveConnections = new HashSet<>();
		if(TelevisionConnection!=null) {
			System.out.println("1. Television Bill (Connection Id : " + TelevisionConnection.getConnectionId() + "): Rs." + TelevisionConnection.getDue());
			SetOfActiveConnections.add(1);
		}
		if(BroadbandConnection!=null) {
			System.out.println("2. Broadband Bill (Connection Id : " + BroadbandConnection.getConnectionId() + "): Rs." + BroadbandConnection.getDue());
			SetOfActiveConnections.add(2);
		}
		if(WaterConnection!=null) {
			System.out.println("3. Water Bill (Connection Id : " + WaterConnection.getConnectionId() + "): Rs." + WaterConnection.getDue());
			SetOfActiveConnections.add(3);
		}
		if(PowerConnection!=null) {
			System.out.println("4. Power Bill (Connection Id : " + PowerConnection.getConnectionId() + "): Rs." + PowerConnection.getDue());
			SetOfActiveConnections.add(4);
		}
		return SetOfActiveConnections;
	}
}
