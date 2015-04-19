<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<!--Created by Vladimir Zhdanov on 22.03.2015-->
<head>
<TITLE>Challenge Creation</TITLE>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
	<script type="text/javascript"
	    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	<script type="text/javascript">
		function createChallenge() {
	    	title = document.getElementById("title").value;
	    	description = document.getElementById("description").value;
	    	diff = document.getElementById("diff").value;
	    	hash = document.getElementById("hash").value;
	    	//'challenge_creation.html?title=' + title + '&description=' + description + '&diff=' + diff + '&hash=' + hash
	    	$('#msg').append("<p>Processing your request</p>");
	    	$.ajax({
	            url : 'create_challenge_test.html?title=' + title + '&description=' + description + '&diff=' + diff + '&hash=' + hash,
	            success : function(data) {
	            	$('#msg').empty();
               		$('#msg').append(data);
        		}
        	});
		}

	</script> 
</head>
 
<body>

	<div class="main">
		<div class="col-md-3">
		</div>

		<div class="col-md-6">
			<div class="input-group">
				<input type="text" class="form-control" id="title" placeholder="Challenge title">
			</div>
			<div class="input-group">
				<input type="text" class="form-control" id="description" placeholder="Description">
			</div>
			<div class="input-group">
				<input type="text" class="form-control" id="diff" placeholder="Difficulty">
				<span class="input-group-addon">/10</span>
			</div>
			<div class="input-group">
				<span class="input-group-addon">#</span>
				<input type="text" class="form-control" id="hash" placeholder="Hashtag">
			</div>
			<div>
				<button type="button" onclick="createChallenge()">Create</button>
			</div>
			<div id="msg"></div>
		</div>

		<div class="col-md-3">
		</div>

	</div>
</body>
</html>
