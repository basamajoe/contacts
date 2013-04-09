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
            <p>Application for the contacts management.</p>
            <ul>
                <li><a href="<c:out value="${url}person/new" escapeXml="true" />">New Person</a></li>
                <li><a href="<c:out value="${url}person/lst" escapeXml="true" />">List of persons</a></li>
            </ul>
        </nav>
    </header>
  
    <div>
        <div>
            <h1>Main page - Contact management</h1>
            <h2> <c:out value="${msg}"></c:out> </h2>
        </div>
    </div>
  
  <footer>
    <p>2012 JavaLabs</p>
  </footer>
</body>
</html>