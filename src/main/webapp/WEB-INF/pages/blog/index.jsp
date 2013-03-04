<?xml version="1.0" encoding="US-ASCII" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Example micro blog</title>
</head>
<body>
	<a href=<c:url value="/blog/create"/>>Create new entry</a>
	<table>
		<tr>
			<th>message</th>
			<th>user</th>
			<th>date</th>
		</tr>
		<c:forEach items="${entries}" var="entry">
			<tr>
				<td><a href=<c:url value="/blog/update/${entry.id}"/>>${entry.text}</a></td>
				<td>${entry.authorId}</td>
				<th>${entry.creationDate}</th>
			</tr>
		</c:forEach>
	</table>
</body>
</html>