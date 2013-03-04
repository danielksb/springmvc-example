Java Spring MVC Example
=======================

This is a small example Java Spring MVC application, which is primarily used
to teach (and learn more) about the Spring framework.

You can easily run the project by invoking Maven with the goal "tomcat:run".

The application is fairly simple and provides basic example implementations for
typical features of a Spring application. Currently the project is far from being
finished, I still play around with the code a lot and do some experimenting. 

Overview
--------

Currently the following features are implemented:
 * user registration and login via Spring Security
 * test cases for controllers, models and integration tests
 * usage of MongoDB (must be activated in the app-context.xml)
 
 In progress:
  * simple CRUD operations in form of a micro-blogging service
 
Future
-------
 * Selenium acceptance tests
 * Spring Web Flow example
 * Hibernate and transaction management
 * REST with Spring and Jersey
 