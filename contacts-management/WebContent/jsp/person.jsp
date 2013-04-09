<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url var="url" value="/"></c:url>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>${title}</title>

  </head>
<body>
    <header>
        <nav>
            <ul>
                <li>
                    <a href="<c:out value="${url}person/lst" escapeXml="true" />">List of persons</a>
                </li>
            </ul>
        </nav>
    </header>
  
    <div>
        <h2>${title}</h2>
        <div>
            <h3>${titleUser}</h3>
            <form action="<c:out value="${url}${formAction}" escapeXml="true" />" method="post">
                <ul>
                    <c:if test="${not empty person.id}">
                        <li>
                            <label for="idTxt">ID</label>
                            <input id="idTxt" type="number" name="id" readonly="readonly" value="${person.id}">
                        </li>
                    </c:if>
                    <li>
                        <label for="txtFirstName">First Name</label>
                        <input id="txtFirstName" name="firstName" type="text" value="${person.firstName}" placeholder="Enter the first name" required>
                    </li>
                    <li>
                        <label for="txtLastName">Last Name</label>
                        <input id="txtLastName" name="lastName" type="text" value="${person.lastName}" placeholder="Enter the last name">
                    </li>
                    <li>
                        <label for="txtDate">Date of birth</label>
                        <input id="txtDate" name="dob" type="date" value='<fmt:formatDate pattern=" dd/MM/yyyy" value="${person.dob}" />'>
                    </li>
                </ul>
                <button>Send</button><button type="reset">Reset</button>
            </form>
        </div>
    </div>
    
    <footer>
        <p>2012 JavaLabs</p>
    </footer>
</body>
</html>