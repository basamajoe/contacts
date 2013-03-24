<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url var="url" value="/"></c:url>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>${title}</title>
    <link rel="stylesheet" href='<c:out value="${url}css/style.css"/>'>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
    <script type="text/javascript" src='<c:out value="${url}js/mascara.js" />'></script>
    <script type="text/javascript" src='<c:out value="${url}js/general.js" />'></script>

    <script type="text/javascript">
    jQuery(function($){
	    $("#txtDate").mask("99/99/9999");
    });
    </script>

  </head>
<body>
  <header>
    <nav class="link-person">
      <ul>
        <li><a href="<c:out value="${url}person" escapeXml="true" />">List of persons</a></li>
      </ul>
    </nav>
  </header>
  
	<div id="pers" class="container">
		<h2>${title}</h2>
		<div>
			<fieldset>
				<legend>${titleUser}</legend>
				<form action="<c:out value="${url}${urls}" escapeXml="true" />" method="post">
					<ul>
						<c:if test="${not empty person.id}">
						<li><label for="idTxt">ID</label><input id="idTxt" type="text" name="id" readonly="readonly" value="${person.id}"></li>
						</c:if>
						<li><label for="txtFirstName">First Name</label><input id="txtFirstName" name="firstName" type="text" value="${person.firstName}"></li>
						<li><label for="txtLastName">Last Name</label><input id="txtLastName" name="lastName" type="text" value="${person.lastName}"></li>
						<li><label for="txtDate">Date of birth</label><input id="txtDate" name="dob" type="text" value='<fmt:formatDate pattern=" dd/MM/yyyy" value="${person.dob}" />'></li>
					</ul>
					<div class="divButton">
						<button>Send</button><button type="reset">Reset</button>
					</div>
				</form>
			</fieldset>
		</div>
	
	</div>
	
  <footer>
    <p>2012 JavaLabs</p>
  </footer>
</body>
</html>