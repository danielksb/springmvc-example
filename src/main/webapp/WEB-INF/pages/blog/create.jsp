<?xml version="1.0" encoding="US-ASCII" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href=<c:url value="/css/blog.css"/> />
<title>Create blog entry</title>
</head>
<body>
	<form:form method="post" action="create" accept-charset="UTF-8"
		commandName="blogEntryFormData">
		<table>
			<tr>
				<td>Message</td>
				<td><form:input path="text" /></td>
				<td><form:errors path="text" cssClass="blogError"/></td>
			</tr>

			<tr>
				<td>Tags (separated by spaces)</td>
				<td><form:input path="tags" /></td>
				<td></td>
			</tr>
		</table>

		<input type="submit" />
	</form:form>
</body>
</html>