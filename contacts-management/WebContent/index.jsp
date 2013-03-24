<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <c:url var="url" value="/"></c:url>
<!DOCTYPE HTML>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
  <header>
    <nav class="link-person">
      <ul>
        <li><a href="<c:out value="${url}person/new" escapeXml="true" />">New Person</a></li>
        <li><a href="<c:out value="${url}person" escapeXml="true" />">List of persons</a></li>
      </ul>
    </nav>
  </header>
  
	<div id="container" class="container">
		<div>
      <h1>Contact management</h1>
			<h2> <c:out value="${message}"></c:out> </h2>
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
						<th>Actions</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="person" items="${persons}">
						<tr>
							<td>${person.firstName}</td>
							<td>${person.lastName}</td>
							<td><fmt:formatDate pattern=" dd/MM/yyyy" value="${person.dob}" /></td>
							<td><a href='<c:out value="${url}person/delete/${person.id}" escapeXml="true" />'>delete</a> | <a url='<c:out value="${url}person/update/${person.id}" escapeXml="true" />' href="#">edit</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
  
  <section>
  
    <article>
      <header>
        <h2>Contacts news</h2>
        <p>Posted on <time datetime="2012-03-22T16:31:24+02:00">March 22nd 2012</time> by <a href="#">Writer</a> - <a href="#comments">0 comments</a></p>
      </header>
      <p>My first attempt to design the front page</p>
    </article>
    
  </section>
  
  <footer>
    <p>2012 JavaLabs</p>
  </footer>
</body>
</html>