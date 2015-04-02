<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<!--Created by Vladimir Zhdanov on 05.03.2015-->
<head>
    <title>TestPage</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/settings.css" />">

    <script type="text/javascript"
            src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
</head>


<body>
<div class="main">
    <c:url var="welcome" value="/welcome"/>
    <c:url var="home" value="/home"/>
    <c:url var="settings" value="/settings"/>
    <c:url var="about" value="#"/>
    <c:url var="ourteam" value="#"/>
    <c:url var="challenges" value="#"/>
    <c:url var="trend" value="#"/>
    <c:url var="trophyroom" value="#"/>
    <c:url var="active" value="#"/>
    <c:url var="archive" value="#"/>
    <c:url var="upcoming" value="#"/>
    <c:url value="/j_spring_security_logout" var="logoutUrl"/>
    <div>
        <a href="testpage_postcomment">Press me to post a comment</a>
    </div>
        <div>
        <a href="testpage_getcomment">Press me to get a tweet</a>
    </div>
</div>
</body>
</html>