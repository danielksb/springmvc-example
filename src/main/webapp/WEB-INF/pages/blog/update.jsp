<?xml version="1.0" encoding="US-ASCII" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Blog entry</title>
</head>
<body>
	<table>
		<tr>
			<td>Message:</td>
			<td>${entry.text}</td>
		</tr>

		<tr>
			<td>Tags:</td>
			<td>${entry.tags}</td>
		</tr>
		
		<tr>
			<td>Author:</td>
			<td>${entry.authorId}</td>
		</tr>
	</table>
	<a href=<c:url value="/blog"/>> Back </a>
</body>
</html>