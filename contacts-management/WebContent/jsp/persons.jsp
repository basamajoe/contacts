<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <c:url var="url" value="/"></c:url>
    
<!DOCTYPE HTML>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Contacts manager</title>
  </head>

<body>
    <header>
        <nav>
            <ul>
                <li><a href="<c:out value="${url}person/new" escapeXml="true" />">New Person</a></li>
                <li><a href="<c:out value="${url}person/lst" escapeXml="true" />">List of persons</a></li>
            </ul>
    </nav>
    </header>
  
    <div>
        <div>
            <h1>Contact management</h1>
            <h2> <c:out value="${msg}"></c:out> </h2>
        </div>
        <div>
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
                            <td><a href='<c:out value="${url}person/del/${person.id}" escapeXml="true" />'>delete</a> | <a href='<c:out value="${url}person/edt/${person.id}" escapeXml="true" />'>edit</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
  
  <footer>
    <p>2012 JavaLabs</p>
  </footer>
</body>
</html>