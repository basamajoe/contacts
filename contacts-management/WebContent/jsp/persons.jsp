<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url var="url" value="/"></c:url>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>JavaLabs - Contacts Management</title>
        
    <meta name="description"
        content="A simple contacts managemen application">
    <meta name="keywords" content="Java, JSTL, DAO">
        
    <meta name="robots" content="index, follow">
    <link href="../css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
    <!-- init header part -->
    <header>
        <nav>
            <ul id="home" class="topnav">
                <li><a class="homebutton" title="Home"
                    href="http://www.javalabs.com/">JavaLabs.com</a></li>
                <li><a title="Tutorials"
                    href="http://www.javalabs.com/tutorials.html"> Tutorials </a></li>
                <li><a title="Services"
                    href="http://www.javalabs.com/services/index.html"> Services </a></li>
                <li><a title="Contact"
                    href="http://www.javalabs.com/social.html"> Contact </a></li>
            </ul>
        </nav>
    </header>
    <!-- end header part -->

    <!-- init container -->
    <div id="container">
        <!-- init leftcolumn -->
        <div id="leftcol">
            <!-- init sidebar-->
            <div class="sidebar">

                <h3>Main menu</h3>
                <a href="<c:out value="${url}" escapeXml="true" />"> Home</a> <br />
                <a href="<c:out value="${url}person/new" escapeXml="true" />"> New person </a> <br />
                <a href="<c:out value="${url}person/lst" escapeXml="true" />" rel="nofollow"> List of persons </a> <br />
            </div>
            <!-- end sidebar-->
        </div>
        <!-- end leftcolumn -->
        <!-- init article -->
        <div class="article">

            <!-- Example row of columns -->
            <div class="row">
                <h1>List of persons</h1>
		<div><c:out value="${msg}"></c:out></div>
		<div style="color:red"><c:out value="${err}"></c:out></div>
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
        </div>
        <!-- end article -->
        <!-- init right col -->
        <div id="rightcol">
            Here we can write something cool. See <a href="#">Cool things</a>
        </div>
        <!-- end right col -->
    </div>
    <!-- end container -->
    <!-- init footer -->
    <footer>
        <div id="bottomnav">
            <ul id="home" class="bottomnav">
                <li><a class="homebutton" title="Home" href="http://www.javalabs.com/">JavaLabs.com</a></li>
                <li><a title="J2EE" href="http://www.javalabs.com/tutorials.html"> J2EE </a></li>
            </ul>
        </div>
    </footer>
    <!-- end footer -->
</body>
</html>