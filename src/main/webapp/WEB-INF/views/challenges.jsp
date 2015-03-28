<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<!--Created by Vladimir Zhdanov on 15.03.2015-->
<head>
<TITLE>Challenges</TITLE>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/settings.css" />"> 
<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
    var count = 5;// how many challenges to request
    var num = 0;// Already recieved challenges
    var from;// Request "count" (5) challenges from number "from" (1)
	max_count = ${max_count_html};// Number of challenges in database comes from jsp
	function appendChallenges() {
		if (num < max_count) {
    		if (num + count > max_count) {
    			count = max_count - num;
    		}
    		from = num + 1;// for numeration from 1
    		num = num + count;
	        $.ajax({
	            url : 'challenges-all.html?num=' + from.toString() + '&count=' + count.toString(),
	            success : function(data) {
	                $('#challenges-all').append(data);
	        	}
	        });
		}
	}
</script>

<script type="text/javascript">
	var loading = false;
	$(window).scroll(function(){
	//if((($(window).scrollTop()+$(window).height())+2)>=$(document).height()){ //Doesn't work
	if($(document).height() <= $(window).scrollTop() + 1920){
		if(loading == false){
			loading = true;
				appendChallenges();
			loading = false;
		}
		  
	}
	});
	$(document).ready(function() { //Works
    	//alert(max_count.toString());
		appendChallenges();
		$(window).scrollTo(0);
	});
</script> 
</head>
 
<body>
<div class="main">
	<c:url var="home" value="/home" />
	<c:url var="settings" value="/settings" />
	<c:url var="welcome" value="/welcome" />
	<c:url var="about" value="#" />
	<c:url var="ourteam" value="#" />
	<c:url var="challenges" value="#" />
	<c:url var="trend" value="#" />
	<c:url var="trophyroom" value="#" />
	<c:url var="all" value="#" />
	<c:url var="recommendations" value="#" />
	<c:url var="random" value="#" />
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<div class="nav navigator">
			<div class="container">
				<div class="row">
					<div class="col-md-3">
						<a href="${welcome}"> <i class="fa fa-cloud"></i>ChallengeCloud
						</a>
					</div>

					<div class="col-md-9">
						<ul class="pull-left">
							<li><a href="${home}">Home</a></li>
							<li><a href="${about}">About</a></li>
							<li><a href="${ourteam}">Our Team</a></li>
						</ul>
						<ul class="pull-right">
							<li>
								<form:form action="${logoutUrl}">
									<button class="btn btn-default" type="submit" > Log out </button>
								</form:form>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<br />
		<!-- For Horizontal and Vertical lines not contact -->

    <div class="row">
        <div class="col-md-3">
            <div class="menu">
                <!-- <ul class="nav nav-pills nav-stacked"> -->
                <ul class="menu">
                    <li role="presentation" class="active"><a href="${challenges}">Challenges</a></li>
                    <li role="presentation"><a href="${trend}">Trend</a></li>
                    <li role="presentation"><a href="${trophyroom}">Trophy Room</a></li>
                    <li role="presentation"><a href="${settings}">Settings</a></li>
                </ul>
            </div>
        </div>

			<div class="col-md-6">
				<!-- Central col -->
				<div class="row">
					<div class="heading">
						<h1>Challenges</h1>
					</div>
					<div class="menu2">
						<ul class="nav nav-pills">
							<li role="presentation" class="active"><a href="#">All</a></li>
							<li role="presentation"><a href="${recommendations}">Recommendations</a></li>
							<li role="presentation"><a href="${random}">Random</a></li>
						</ul>
					</div>
					<!-- Pills -->
				</div>
                <br/>
                
				<!-- Challenges -->
				<div>
        			<div id="challenges-all"> <!-- Ajax requested. Created by Vova on 15.03.2015 -->
        			</div>
				</div>
				<!-- End of Challenges -->
			</div>
			<!-- End of Central col -->
			<div></div>
		</div>
	</div>
</body>
</html>