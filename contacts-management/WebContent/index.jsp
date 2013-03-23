<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <c:url var="url" value="/"></c:url>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contact manager</title>
<link rel="stylesheet" href='<c:out value="${url}css/style.css"/>'>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src='<c:out value="${url}js/general.js" />'></script>

<script type="text/javascript">

$(document).ready(function(){
    $('table tbody tr td a').click(function (event){
        var url = $(this).attr("url");   
        window.open(url, '_self');
        event.preventDefault();

    });
});
	
</script>
</head>

<body>
	<div id="container" class="container">
		<div id="header">
			<h2>Contact management</h2>
		
		</div>
		<div>
			<h2> <c:out value="${message}"></c:out> </h2>
			<div class="link-person">
				<a href="<c:out value="${url}person/new" escapeXml="true" />">Add new person</a>
			</div>
		</div>
		<div id="form">
		</div>
		<div id="table">
			<table>
				<thead>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Date of birth</th>
						<th>Acciones</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="person" items="${persons}">
						<tr>
								
							<td>${person.firstName}</td>
							<td>${person.lastName}</td>
							<td><fmt:formatDate pattern=" dd/MM/yyyy" value="${person.dob}" /></td>
							<td><a href="#" url='<c:out value="${url}person/delete/${person.id}" escapeXml="true" />'>Eliminar</a> | <a url='<c:out value="${url}person/update/${person.id}" escapeXml="true" />' href="#">edit</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>