<?xml version="1.0" encoding="US-ASCII" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href=<c:url value="/css/registration.css"/> />
<title>Example Registration</title>
</head>
<body>
	<h2>Registration</h2>
	<form:form method="post" action="signup" accept-charset="UTF-8" commandName="userRegistrationData">
		<form:errors path="" cssClass="registrationError"/>
		<table>
			<tr>
				<td>Username</td>
				<td><form:input path="id" /></td>
				<td><form:errors path="id" cssClass="registrationError"/></td>
			</tr>

			<tr>
				<td>Password</td>
				<td><form:password path="password"/></td>
				<td><form:errors path="password" cssClass="registrationError"/></td>
			</tr>

			<tr>
				<td>Verify Password</td>
				<td><form:password path="confirmedPassword"/></td>
				<td><form:errors path="confirmedPassword" cssClass="registrationError"/></td>
			</tr>
		</table>

		<input type="submit" />
	</form:form>
</body>
</html>