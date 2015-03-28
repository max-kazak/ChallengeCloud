<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<!--Created by Vladimir Zhdanov on 05.03.2015-->
<head>
<title>Home</title>
	<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/settings.css" />">

    <script type="text/javascript"
            src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <%--Script was created by Vova, rearrnaged by Yefim--%>
    <script type="text/javascript">
        var num = -4;// for numeration from 1
        function appendChallenges() {
            num = num + 5;
            $.ajax({
                url: 'home-subscriptions.html?subscriptionId=' + num.toString(),
                success: function (data) {
                    $('#home-subscriptions').append(data);
                }
            });
        }
    </script>

    <script type="text/javascript">
        var loading = false;
        $(window).scroll(function () {
            if ($(document).height() <= $(window).scrollTop() + 1920) {
                if (loading == false) {
                    loading = true;
                    appendChallenges();
                    loading = false;
                }
            }
        });
        $(document).ready(function () { //Doesn't work
            //alert("ready")
            appendChallenges();
            $(window).scrollTo(0);
        });
    </script>
</head>


<body>
	<div class="main">
		<div class="nav navigator">
			<div class="container">
				<div class="row">
					<div class="col-md-3">
						<a href="#"> <i class="fa fa-cloud"></i>ChallengeCloud
						</a>
					</div>

					<div class="col-md-9">
						<ul class="pull-left">
							<li><a href="<c:url value="/home" />">Home</a></li>
							<li><a href="#">About</a></li>
							<li><a href="#">Our Team</a></li>
						</ul>
						<ul class="pull-right">
							<li><a href="#">Log out</a></li>
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
						<li role="presentation" class="active"><a href="<c:url value="/challenges" />">Challenges</a></li>
						<li role="presentation"><a href="#">Trend</a></li>
						<li role="presentation"><a href="#">Trophy Room</a></li>
						<li role="presentation"><a href="<c:url value="/settings" />">Settings</a></li>
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
							<li role="presentation" class="active"><a href="#">Active</a></li>
							<li role="presentation"><a href="#">Archive</a></li>
							<li role="presentation"><a href="#">Upcoming</a></li>
						</ul>
					</div>
					<!-- Pills -->
				</div>
                <br/>
				<!-- Challenges -->
				<div id="home-subscriptions">
				<!--HERE -->
				</div>
				<!-- End of Challenges -->

			</div>
			<!-- End of Central col -->
			<div></div>
		</div>
	</div>
</body>
</html>