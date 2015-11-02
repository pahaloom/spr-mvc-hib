<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title><spring:message code="list.member.title"/></title>
<%@include file="fragment-bootstrap.jspf"%>
</head>
<body>
<%@include file="fragment-navbar.jspf"%>
<h1><spring:message code="list.member.title"/></h1>
<p><spring:message code="list.member.description"/></p>
<p>${message}</p>
<table border="1px" cellpadding="0" cellspacing="0" >
<thead>
<tr>
	<th width="10%"><spring:message code="list.member.header.id"/></th><th width="15%"><spring:message code="list.member.header.name"/></th><th width="15%"><spring:message code="list.member.header.teams"/></th><th width="10%"><spring:message code="list.member.header.actions"/></th>
</tr>
</thead>
<tbody>
<c:forEach var="member" items="${members}">
<tr>
	<td>${member.id}</td>
	<td class="name">${member.name}</td>
	<td><c:forEach var="team" items="${member.teams}">${team.name}<br/></c:forEach></td>
	<td>
	<a href="${pageContext.request.contextPath}/member/edit/${member.id}.html"><spring:message code="list.member.action.edit"/></a><br/>
	<a href="${pageContext.request.contextPath}/member/delete/${member.id}.html" class="deleteLink"><spring:message code="list.member.action.delete"/></a><br/>
	</td>
</tr>
</c:forEach>
</tbody>
</table>

	<p><a href="${pageContext.request.contextPath}/index.html"><spring:message code="list.member.link.home"/></a></p>

	<script type="text/javascript"><spring:message code="script.delete.confirmation" var="i18nConfirmation"/>
	$('.deleteLink').click(function(){
		return confirm("${i18nConfirmation} '" + $(this).closest("tr").find("td.name").text() + "'?");
	});
</script>
</body>
</html>