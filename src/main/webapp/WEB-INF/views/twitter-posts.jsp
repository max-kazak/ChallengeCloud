<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<!--Created by Vladimir Zhdanov on 22.03.2015-->
<head>
<TITLE>Twitter Posts</TITLE>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/settings.css" />"> 
<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
	var id = 2;
	function getPosts() {
        $.ajax({
            url : 'twitter.html?id=' + id.toString() + '&get=posts',
            success : function(data) {
                $('#posts').html(data);
        	}
        });
	}
	function getFriends() {
        $.ajax({
            url : 'twitter.html?id=' + id.toString() + '&get=friends',
            success : function(data) {
                $('#friends').html(data);
        	}
        });
	}
</script>

<script type="text/javascript">
</script> 
</head>
 
<body>
<div class="main">
    <button onclick="getFriends()">getFriends</button>
    <h1>Friends</h1>
    <div id="friends"></div>
    </br>
    <button onclick="getPosts()">getPosts</button>
    <h1>Posts</h1>
    <div id="posts"></div>
</div>
</body>
</html>