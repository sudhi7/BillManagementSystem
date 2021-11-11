package com.BillManagementSystem.Utilities;

import java.util.UUID;

public class Connection {
	private UUID ConnectionId;
	private int Due;
	
	Connection(UUID ConnectionId, int Due) {
		this.ConnectionId = ConnectionId;
		this.Due = Due;
	}
	
	public int getDue() {
		return Due;
	}
	
	public UUID getConnectionId() {
		return ConnectionId;
	}
}
