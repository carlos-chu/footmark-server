<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>超级账户平台</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<c:if test="${not empty url }">
	<meta http-equiv="refresh" content="2;url=${url }">
</c:if>
</head>
<body>
	<c:choose>
		<c:when test="${empty msg}">
			<p>loading...</p>

		</c:when>
		<c:otherwise>
			<p>${msg }</p>
		</c:otherwise>
	</c:choose>
</body>
</html>