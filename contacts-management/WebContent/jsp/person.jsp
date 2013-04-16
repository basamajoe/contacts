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
    <link href='<c:out value="${url}css/style.css" />' rel="stylesheet" type="text/css">
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
	        <h1>${title}</h1>
	        <div><c:out value="${msg}"></c:out></div>
                <div style="color:red"><c:out value="${err}"></c:out></div>
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