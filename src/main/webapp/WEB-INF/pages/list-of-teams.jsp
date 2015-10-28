<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title><spring:message code="list.team.title"/></title>
<%@include file="fragment-bootstrap.jspf"%>
</head>
<body>
<%@include file="fragment-navbar.jspf"%>
<h1><spring:message code="list.team.title"/></h1>
<p><spring:message code="list.team.description"/></p>
<table border="1px" cellpadding="0" cellspacing="0" >
<thead>
<tr>
	<th width="10%"><spring:message code="list.team.header.id"/></th><th width="15%"><spring:message code="list.team.header.name"/></th><th width="10%"><spring:message code="list.team.header.rating"/></th><th width="10%"><spring:message code="list.team.header.organization"/></th><th width="10%"><spring:message code="list.team.header.actions"/></th>
</tr>
</thead>
<tbody>
<c:forEach var="team" items="${teams}">
<tr>
	<td>${team.id}</td>
	<td>${team.name}</td>
	<td>${team.rating}</td>
	<td>${team.organization.name}</td>
	<td>
		<a href="${pageContext.request.contextPath}/team/edit/${team.id}.html"><spring:message code="list.team.action.edit"/></a><br/>
	<a href="${pageContext.request.contextPath}/team/delete/${team.id}.html"><spring:message code="list.team.action.delete"/></a><br/>
	</td>
</tr>
</c:forEach>
</tbody>
</table>

<p><a href="${pageContext.request.contextPath}/index.html"><spring:message code="list.team.link.home"/></a></p>

</body>
</html>