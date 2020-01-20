package com.HealthWellnessTracker.DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection implements DBConnectionI{
	
	@Override
	public Connection connect() {
		Connection dbConnect = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			dbConnect = DriverManager.getConnection("jdbc:mariadb://localhost:3306/hwt?user=hwt_admin&password=123admin123");
			System.out.println(dbConnect.isValid(0)?"Connected to Database":"ERROR: Cannot connect to database");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error connecting to the database, check the code.");
			e.printStackTrace();
		}
		return dbConnect;
	}
	
	@Override
	public void dbDisconnect() {
		
	}
	
}
