<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title><spring:message code="home.title"/></title>
<%@include file="fragment-bootstrap.jspf"%>
</head>
<body>
<%@include file="fragment-navbar.jspf"%>
<h1><spring:message code="home.title"/></h1>
<p>
${message}<br/>
<a href="${pageContext.request.contextPath}/team/add.html"><spring:message code="home.add.team"/></a><br/>
<a href="${pageContext.request.contextPath}/team/list.html"><spring:message code="home.team.list"/></a><br/>
<a href="${pageContext.request.contextPath}/organization/add.html"><spring:message code="home.add.organization"/></a><br/>
<a href="${pageContext.request.contextPath}/organization/list.html"><spring:message code="home.organization.list"/></a><br/>
<a href="${pageContext.request.contextPath}/member/add.html"><spring:message code="home.add.member"/></a><br/>
<a href="${pageContext.request.contextPath}/member/list.html"><spring:message code="home.member.list"/></a><br/>
</p>
</body>
</html>