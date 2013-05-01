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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
    <meta name="description"
        content="A simple contacts managemen application">
    <meta name="keywords" content="Java, JSTL, DAO">
    <meta name="robots" content="index, follow">
    <meta name="author" content="Joe Sanchez">
    
    <!-- CSS -->
    <link href="${url}css/bootstrap.css" rel="stylesheet"  type="text/css">
    <link href="${url}css/styleStickyFooter.css" rel="stylesheet" type="text/css">
    <link href="${url}css/bootstrap-responsive.css" rel="stylesheet" type="text/css">

    <!-- Fav -->
    <link rel="shortcut icon" href="${url}img/iconJabaLabs.png">
</head>

<body>

    <!-- Part 1: Wrap all page content here -->
    <div id="wrap">

      <!-- Fixed navbar -->
      <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="navbar-inner">
          <div class="container">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="brand" href="${url}">Java Labs</a>
            <div class="nav-collapse collapse">
              <ul class="nav">
                <li class="active"><a href="<c:out value="${url}" escapeXml="true" />">Home</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#contact">Contact</a></li>
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">More<b class="caret"></b></a>
                  <ul class="dropdown-menu">
                    <li><a href="#">One</a></li>
                    <li><a href="#">Two</a></li>
                    <li><a href="#">three</a></li>
                    <li class="divider">h</li>
                    <li class="nav-header">Extra</li>
                    <li><a href="#">One</a></li>
                    <li><a href="#">Two</a></li>
                  </ul>
                </li>
              </ul>
            </div><!--/.nav-collapse -->
          </div>
        </div>
      </div>
    
    <!-- Begin page content -->
    <div class="container">
        <div class="container-fluid">
		    <div class="row-fluid">
		        <div class="span3">
		          <div class="well sidebar-nav"> <!-- left side bar -->
		            <ul class="nav nav-list">
		              <li class="nav-header">Home</li>
		              <li class="active"><a href="#">Index</a></li>
		              <li class="nav-header">Contacts</li>
		              <li><a href="<c:out value="${url}person/new" escapeXml="true" />">New person</a></li>
		              <li><a href="<c:out value="${url}person/lst" escapeXml="true" />">List of persons</a></li>
		            </ul>
		          </div><!--/.well -->
		        </div><!--/span-->
		        <div class="span9">
					<form action="<c:out value="${url}${formAction}" escapeXml="true" />" method="post">
						<fieldset>
							<legend>
				            <c:if test="${not empty person.id}">
								Edit person
							</c:if>
							<c:if test="${empty person.id}">
								New person
							</c:if>
				          	${titleUser}
				          	</legend>
							<c:if test="${not empty person.id}">
							    <label for="idTxt">ID</label>
								<input id="idTxt" type="number" name="id" readonly="readonly" value="${person.id}">
							</c:if>
							<label for="txtFirstName">First Name</label>
							<input id="txtFirstName" name="firstName" type="text" value="${person.firstName}" placeholder="Enter the first name" required>
							<label for="txtLastName">Last Name</label>
							<input id="txtLastName" name="lastName" type="text" value="${person.lastName}" placeholder="Enter the last name">
							<label for="txtDate">Date of birth</label>
							<input id="txtDate" name="dob" type="date" value='<fmt:formatDate pattern=" dd/MM/yyyy" value="${person.dob}" />'><br/>
							<button class="btn btn-small btn-primary">Send</button> <button class="btn btn-small btn-warning" type="reset">Reset</button>
						</fieldset>
					</form>
		        </div><!--/span-->
		      </div><!--/row-->
          </div>
        <div id="push"></div>
    </div>
    </div>

    <footer>
      <div class="container">
        <p class="muted credit">© Java Lavs 2013</p>
      </div>
    </footer>


    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${url}js/jquery.js"></script>
    <script src="${url}js/bootstrap-transition.js"></script>
    <script src="${url}js/bootstrap-alert.js"></script>
    <script src="${url}js/bootstrap-modal.js"></script>
    <script src="${url}js/bootstrap-dropdown.js"></script>
    <script src="${url}js/bootstrap-scrollspy.js"></script>
    <script src="${url}js/bootstrap-tab.js"></script>
    <script src="${url}js/bootstrap-tooltip.js"></script>
    <script src="${url}js/bootstrap-popover.js"></script>
    <script src="${url}js/bootstrap-button.js"></script>
    <script src="${url}js/bootstrap-collapse.js"></script>
    <script src="${url}js/bootstrap-carousel.js"></script>
    <script src="${url}js/bootstrap-typeahead.js"></script>

</body>
</html>