package com.HealthWellnessTracker.tests;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SelectPackages({"com.HealthWellnessTracker.DAOs"})
@SuiteClasses({TestLoginDAO.class, TestEventDAO.class, TestRecordDAO.class, TestUserProfileDAO.class})
public class AllTests {
	
}
