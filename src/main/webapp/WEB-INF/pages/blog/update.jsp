<?xml version="1.0" encoding="US-ASCII" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Blog entry</title>
</head>
<body>
	<form:form method="post" accept-charset="UTF-8" commandName="entry">
		<table>
			<tr>
				<td>Message:</td>
				<td><form:input path="text" readonly="true"/></td>
			</tr>

			<tr>
				<td>Tags:</td>
				<td><form:input path="tags"/></td>
			</tr>

			<tr>
				<td>Author:</td>
				<td><form:input path="authorId" readonly="true"/></td>
			</tr>
			<tr>
				<td>Creation date:</td>
				<td><form:input path="creationDate" readonly="true"/></td>
			</tr>
		</table>
		<input type="submit"/>
	</form:form>
	<a href=<c:url value="/blog"/>> Back </a>
</body>
</html>