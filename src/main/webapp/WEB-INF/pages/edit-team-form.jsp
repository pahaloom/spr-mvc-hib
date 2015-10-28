<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title><spring:message code="edit.team.title"/></title>
<%@include file="fragment-bootstrap.jspf"%>
</head>
<body>
<%@include file="fragment-navbar.jspf"%>
<h1><spring:message code="edit.team.title"/></h1>
<p><spring:message code="edit.team.description"/></p>
<p>${message}</p>
<form:form method="POST" commandName="team" action="${pageContext.request.contextPath}/team/edit/${team.id}.html">
<table>
<tbody>
	<tr>
		<td><spring:message code="edit.team.label.name"/></td>
		<td><form:input path="name" /></td>
	</tr>
	<tr>
		<td><spring:message code="edit.team.label.rating"/></td>
		<td><form:input path="rating" /></td>
	</tr>
	<tr>
		<td><spring:message code="edit.team.label.organization"/></td>
		<td><spring:message code="edit.team.empty.organization" var="i18nEmptyOrganization"/>
			<form:select path="organization">
				<form:option value="${null}" label="${i18nEmptyOrganization}" />
				<form:options items="${organizations}" itemLabel="name" itemValue="id" />
			</form:select>
		</td>
	</tr>
	<tr><spring:message code="edit.team.button.edit" var="i18nEdit"/>
		<td><input type="submit" value="${i18nEdit}" /></td>
		<td></td>
	</tr>
</tbody>
</table>
</form:form>

<p><a href="${pageContext.request.contextPath}/index.html"><spring:message code="edit.team.link.home"/></a></p>
</body>
</html>