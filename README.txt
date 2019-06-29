This README file explains how to use Notification system to send emaol/slack notification. This file also specifies
minimum system requirements needed to run the program.

System Requirements:
---------------------
	- System must have java-8 or higher version.
	- System must comply minimum requirements specified for jvm.

Libraries added in the application
-----------------------------------
  - used all the spring boot related jars

Running in an IDE
---------------------
If you want to run the application in an IDE, such as IntelliJ IDEA, you should
be able to import the entire project into a project in the IDE. Alternatively
you can copy entire source files into existing java gradle project. After this build the project in IDE to load the
dependent jars. Once the project is successfully built in IDE, you can run NotificationApp.java as a Java application.
This will start the spring boot application and inbuilt tomcat server would get started.

Run using executable JAR file
------------------------------
If you want to run the application through command line, you can execute the below command in your cmd.

java -jar {$full-path-of-copied-folde-path}\build\libs\NotificationSystem-1.0-SNAPSHOT.jar

If you have copied the source code/project in C:\NotificationSystem then the command should be

java -jar C:\NotificationSystem\build\libs\NotificationSystem-1.0-SNAPSHOT.jar

This will start the spring boot application and inbuilt tomcat server would get started.

Execution instructions:
--------------------------
Once the tomcat server started successfully, you can send the email notification service request to the system. Any REST
API client can be used for the testing. I have used the POST-MAN to test the notifications.

If you want to send the email notification then you need to send the POST request with the below url and json input in
the request body

uri: http://localhost:8080/notification/email
json : {
        "from":"master@sample.com",
        "to":"student@sample.com",
        "cc":"principal@sample.com",
        "subject":"Welcome to class",
        "message":"Assignment details"
}

Here in the input, "from" and "to" should have valid email address to successfully send the email notification. More than
one email addresses can be separated with ";".

If you want to send the slack message notification then you need to send the POST request with the below url and json input in
the request body

uri: http://localhost:8080/notification/slack
json : {
        "from":"master@sample.com",
        "to":"student@sample.com",
        "cc":"principal@sample.com",
        "subject":"Welcome to class",
        "message":"Assignment details"
}

Here in the input, "from" and "to" should have valid email address to successfully send the email notification. More than
one email addresses can be separated with ";".


Extensions:-
--------------
    -Application can be extended to support other notifications as well in future.
	- Separate factory and notification object can be created by extending the NotificationFactory.
	- Separate notification implementation can be done by implementing the INotification.




