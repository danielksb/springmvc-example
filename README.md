Java Spring MVC Example
=======================

This is a small example Java Spring MVC application, which is primarily used
to teach (and learn) more about the spring framework.

You can easily run the project by invoking Maven with the goal "tomcat:run".

The application is fairly simple and demonstrates how a simple user/password login
can be achieved using Spring Security.

Overview
--------

The application provides a simple web site which is greeting you with your name.
In order for the application to know your name you need to create an account
on the web site and be logged in.

The signup, login and logout actions will be handled by the AccountController and the
greeting web page by the MainController. There is also a page called "accountSettings"
which displays some minimal account information. The page can only be accessed if the
user is logged in. The security configuration can be found in the spring-security.xml.

The user data is stored by the UserStorage which is accessed by the
controllers. The implementation is not using any database, all data is stored in memory in a
simple HashTable, so restarting the application will remove all user data. There is also
an implementation for MongoDB which can be activated by uncommenting the beans
in the MongoDB section of the application-context.xml. Of course you can create your
own UserStorage using another database by simple implementing the UserStorage interface and
create a bean called "userStorage" which then gets automatically injected into the controllers. 
