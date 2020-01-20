package com.HealthWellnessTracker.DAOs;

import java.sql.Connection;

public interface DBConnectionI {

	public Connection connect();
	public void dbDisconnect();
	
}
