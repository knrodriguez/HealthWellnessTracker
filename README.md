# Health & Wellness Tracker

Health & Wellness Tracker is a web application dedicated to giving users the ability to record and monitor any habits or ailments they desire. 

## Getting Started
### Overview

The Health & Wellness Tracker web app will display in a calendar format all user records inputted and saved. After creating an account, a user can create Events that describe their habits, illnesses, or symptoms. After an Event is created, the user can then track the Event in a calendar format as a record. Each record will contain the date the Event occurred, with the option to provide the start and end times and/or a name for each record. Once submitted, the record will display on the user's calendar, as well as the record's details when the record is clicked.  

### Requirements

* [OpenJDK](https://openjdk.java.net/install/index.html) - Java Development Kit (JDK)
* [MariaDB](https://downloads.mariadb.org/mariadb/10.4.12/) - SQL Database Server
* [Apache TomCat](https://tomcat.apache.org/download-90.cgi) - Java Web Server

### Installation

1. Download this repo.

2. In the command prompt, enter the following , changing the words with square brackets to their corresponding values:

   ```mysql
   mysql -u [username] -p -e "CREATE DATABASE IF NOT EXISTS `hwt`"
   ```

   Alternatively, if you have it downloaded, you can open your SQL client application and enter the following query:

	```SQL
	CREATE DATABASE IF NOT EXISTS `hwt` /*!40100 DEFAULT CHARACTER SET utf8*/;
	USE `hwt`;
	```

2. Run the Health & Wellness Tracker web app. The database will populate with tables and their corresponding columns.
3. If you would like to populate the tables with sample data, download and run [hwt_inserts.sql](https://github.com/KirstieRodriguez/HealthWellnessTracker/SQL/hwt_inserts.sql) query in your SQL client application. There you will find a sample accounts, each with profile, event, and record information. 

## Deployment

* Once you have installed Health & Wellness Tracker, create an account.
* Login with your new account, and create some Events. The categories currently offered are Habit, Symptom, and Illness, but more will coming in the future! 
* After you have created some Events, go to your calendar. There you can submit new records by click on a date. 

## Built With

| Tool                       | Software / Application / API |
| -------------------------- | ---------------------------- |
| SQL RDBMS                  | MariaDB                      |
| SQL Client Application     | HeidiSQL                     |
| Java Development Kit (JDK) | OpenJDK                      |
| Server                     | Apache TomCat                |
| Java Persistence API       | EclipseLink                  |
| Unit Testing Framework     | JUnit 5                      |
| Web MVC Framework          | Spring Web MVC               |
| Dependency Management      | Apache Maven                 |
| Java EE IDE                | Eclipse                      |

## Acknowledgments
Health & Wellness Tracker uses several open-source elements in its design: 

* [FullCalendar](https://fullcalendar.io/) JavaScript Calendar API
* [Bootstrap-select](https://developer.snapappointments.com/bootstrap-select/) jQuery select plugin
* [Bootstrap](https://getbootstrap.com/) Toolkit
* [Font Awesome](https://fontawesome.com/) Icons

## Authors

* **Kirstie Rodriguez** - *Initial Work*
