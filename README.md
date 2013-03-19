Java Spring MVC Example
=======================

This is a small example Java Spring MVC application, which is primarily used
for experimenting with some Spring features and discussing about it with friends.

You can easily run the project by invoking Maven with the goal "tomcat:run".

Overview
--------

Currently the following features are implemented:
 * user registration and login via Spring Security and a very simple custom authentication provider
 * test cases for controllers, models and integration tests
 * usage of MongoDB via Spring Data (must be activated)
 * Hibernate and transaction management (must be activated)
 
 In progress:
  * simple CRUD operations in form of a blog-like message service
 
Future
-------
 * Selenium acceptance tests
 * Spring Web Flow example
 * REST with Spring and Jersey
 * maybe a more modern template engine
