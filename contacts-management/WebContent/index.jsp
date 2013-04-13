<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <c:url var="url" value="/"></c:url>
<!DOCTYPE HTML>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>JavaLabs - Contacts Management</title>
        
    <meta name="description"
        content="A simple contacts managemen application">
    <meta name="keywords" content="Java, JSTL, DAO">
        
    <meta name="robots" content="index, follow">
    <meta name="author" content="Joe Sanchez">
    <link href="css/style.css" rel="stylesheet" type="text/css">
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
                <div>
                    <img alt="sth" src="imgs/bottleLabs.png" width="64"
                            height="64">
                    <h2>
                        <a href="#">Main page <c:out value="${msg}"></c:out></a>
                    </h2>
		    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. In
                       tortor eros, porttitor in pulvinar sit amet, consectetur in quam.
			Aenean egestas cursus molestie. Aenean ultrices, felis auctor
			scelerisque vestibulum, nulla arcu varius dolor, vitae interdum
			libero est eu erat. Suspendisse vitae laoreet massa. Integer vitae
			dui at tortor adipiscing accumsan id nec velit. Donec id eleifend
			erat. Maecenas tincidunt commodo enim nec lacinia. Nunc turpis
			ipsum, molestie vel dapibus ut, mollis non ante. Integer nulla
			metus, tincidunt a imperdiet in, ultrices eu velit. Nullam aliquet
			consequat risus in consequat. Phasellus massa tortor, rutrum non
			tincidunt eu, rutrum sed arcu. Nulla sagittis ante ac sem pharetra
			pharetra tincidunt magna tristique. Etiam tempus sollicitudin
			libero et porttitor. Maecenas euismod vestibulum mauris sed
			pellentesque. Donec bibendum pharetra purus, sit amet scelerisque
			enim consectetur congue. Proin ut odio ipsum.</p>
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