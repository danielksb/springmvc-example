Java Spring MVC Example
=======================

This is a small example Java Spring MVC application, which is primarily used
to teach (and learn) more about the spring framework.

You can easily run the project by invoking Maven with the goal "tomcat:run".

The application is fairly simple and is not using many Spring features for the
sake of simplicity. The idea was rather to show how the controllers can be set
up to serve views for different requests and how to unit tests the controllers.

Overview
--------

The application provides a simple web site which is greeting you with your name.
In order for the application to know your name you need to create an account
on the web site and be logged in.

The signup, login and logout actions will be handled by the AccountController and the
greeting web page by the MainController.

The session and user data is stored in storage objects which are accessed by the
controllers. The implementation is not using any database, all data is stored in memory in simple
HashTables, so restarting the application will remove all user data. If you want to
implement a database backend you need to create your own implementation of the storage
interfaces and define the beans in the application-context.xml.
