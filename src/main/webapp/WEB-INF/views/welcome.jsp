<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Challenger - Welcome</title>
<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
</script>
<html>
<body>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<!-- csrt for log out-->
	<form action="${logoutUrl}" method="post" id="logoutForm">
	  <input type="hidden" 
		name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	</form>
	
	<a href="javascript:formSubmit()"> Logout</a>

	<h1>Hello, ${message} !</h1>	
</body>
</html>