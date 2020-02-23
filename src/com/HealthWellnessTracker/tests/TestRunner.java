package com.HealthWellnessTracker.tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.HealthWellnessTracker.tests.DAOs.TestAllDAOs;
import com.HealthWellnessTracker.tests.DAOs.TestEventDAO;
import com.HealthWellnessTracker.tests.DAOs.TestLoginDAO;
import com.HealthWellnessTracker.tests.DAOs.TestRecordDAO;
import com.HealthWellnessTracker.tests.DAOs.TestUserProfileDAO;

class TestRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(TestAllDAOs.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		
		System.out.println("All DAOs Successful: " + result.wasSuccessful());
	
	}
}
