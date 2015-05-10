<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<!--Created by Vladimir Zhdanov on 22.03.2015-->
<head>
<TITLE>Twitter Posts</TITLE>
	<link rel="shortcut icon" href="<c:url value="/resources/img/cloud-icon.png" />" />
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
	var id = 2;
	var hash = 'CCloud';
	function getPosts() {
    	t_id = document.getElementById("t_id").value;
    	hash = document.getElementById("t_hash").value;
        $.ajax({
            url : 'twitter.html?id=' + t_id + '&hash=' + hash,
            success : function(data) {
                $('#posts').html(data);
        	}
        });
	}
	function addPosts() {
    	user_id = document.getElementById("user_id").value;
    	hash = document.getElementById("t_hash").value;
        $.ajax({
            url : 'twitter.html?id=' + user_id + '&hash=' + hash + '&add=1',
            success : function(data) {
                $('#posts').html(data);
        	}
        });
	}
</script>

<script type="text/javascript">
</script> 
</head>
 
<body>
<div class="main">
    <br/>
    <!--  <button onclick="getPosts()">getPosts</button>
    <h1>Posts</h1> -->
    <div>
    	<p> Twitter CCloudTest  556989981  </p>
    	<p> Twitter CCloudTest2 3105816243 </p>
    	<p> ChallengeCloud CCloudTest2 19eda4a69b254c61 </p>
    	
    </div>
		<div class="col-lg-6">
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">#</span>
				<input type="text"
					class="form-control" id="t_hash" placeholder="HashTag">
			</div>
			<br />
			<div class="input-group">
				<input type="text" class="form-control" id="t_id"
					placeholder="Twitter ID ..."> <span
					class="input-group-btn">
					<button class="btn btn-default" type="button" onclick="getPosts()">Search Tweets</button>
				</span>
			</div>
			<br />
			<!--  <div class="input-group pull-right">
					<button class="btn btn-default pull-rght" type="button" onclick="addPosts()">Add Tweets</button>
			</div>  -->
			<div class="input-group">
				<input type="text" class="form-control" id="user_id"
					placeholder="User ID ..."> <span
					class="input-group-btn">
					<button class="btn btn-default" type="button" onclick="addPosts()">Add Tweets</button>
				</span>
			</div>
			<!-- /input-group -->
			<br/>
		</div>
	<div id="posts"></div>
	</div></body>
</html>