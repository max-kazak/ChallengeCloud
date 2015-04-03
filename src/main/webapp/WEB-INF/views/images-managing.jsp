<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<!--Created by Vladimir Zhdanov on 28.03.2015-->
<head>
<TITLE>Images</TITLE>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/settings.css" />"> 
    
<!-- On 01.04.2015 by Vladimir Zhdanov. I took it from http://sergey-oganesyan.ru/javascript-s-primerami/kak-sdelat-vsplyvayushee-okno.html
and http://sergey-oganesyan.ru/examples/window_na_fone.html -->
<style type="text/css">
	#transp{
		display: none;
		opacity: 0.8;
		position: fixed;
		left: 0;
		right: 0;
		top: 0;
		bottom: 0;
		padding: 16px;
		background-color: rgba(1, 1, 1, 0.725);
		z-index: 100;
		overflow: auto;
	}
	#window{
		margin: 50px auto;
		display: none;
		background: #fff;
		z-index: 200;
		position: fixed;
		left: 0;
		right: 0;
		top: 0;
		bottom: 0;
		padding: 16px;
	}
	.close{
		cursor: pointer;
	}
	
</style>
<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
    var count = 5;// how many images to request
    var num = 0;// Already recieved images
    var from;// Request "count" (5) images from number "from" (1)
	max_count = ${max_count_html};// Number of images in database comes from jsp
	function appendImages() {
		if (num < max_count) {
    		if (num + count > max_count) {
    			count = max_count - num;
    		}
    		from = num + 1;// for numeration from 1
    		num = num + count;
	        $.ajax({
	            url : 'images-managing-all.html?num=' + from.toString() + '&count=' + count.toString(),
	            success : function(data) {
	                $('#images-managing-all').append(data);
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
				appendImages();
			loading = false;
		}
		  
	}
	});
	$(document).ready(function() { //Works
    	//alert(max_count.toString());
		appendImages();
		$(window).scrollTo(0);
	});
</script> 
</head>
 
<body>
<!-- This div is hidden by default. Press Upload to show it!!! -->

<!-- On 01.04.2015 by Vladimir Zhdanov. I took it from http://sergey-oganesyan.ru/javascript-s-primerami/kak-sdelat-vsplyvayushee-okno.html
and http://sergey-oganesyan.ru/examples/window_na_fone.html -->
	<script type="text/javascript">
		function showUpload() {
			document.getElementById('window-upload').style.display='block';
			document.getElementById('window').style.display='block';
			document.getElementById('transp').style.display='block';
		}
		function hide() {
			document.getElementById('window-upload').style.display='none';
			document.getElementById('window').style.display='none';
			document.getElementById('transp').style.display='none';
		}
	</script>
	<!-- Transparant background-->
	<div id="transp" onclick="hide()"></div>
	<!-- Window -->
	<div id="window">
		<!-- Close -->
		<img class="pull-right close" onclick="hide()" src="./resources/img/close.png" alt="Close">
		<br/>
		<!-- Content -->
		<div id="window-upload">
			<div class="col-lg-12">
				<div class="input-group">
					<input type="text" class="form-control" style=" id="upload_path" placeholder="Path ...">
					<span class="input-group-btn">
						<button class="btn" type="button" onclick="getPosts()">Browse</button>
					</span>
				</div>
				<br />
				<div class="input-group">
					<input type="text" class="form-control" id="upload_id" placeholder="ImageId">
					<span class="input-group-btn">
						<button class="btn" type="button" onclick="getPosts()">Generate</button>
					</span>
				</div>
				<br />
				<div class="input-group">
					<input type="text" class="form-control" id="upload_name" placeholder="Name ...">
				</div>
				<br />
				<div class="input-group">
					<input type="text" class="form-control" id="upload_description" placeholder="Description ...">
				</div>
				<!-- /input-group -->
				<!-- <img style="margin: 20px 0 0 50px;" src="./images/123123.png" alt="Content">  -->	
				<li role="presentation" class="pull-right"><button class="btn btn-default" onclick="upload()">Upload</button></li>
			</div> <!-- col-lg-12 -->
		</div>
	</div>


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
								<form:form name="logoutForm" action="${logoutUrl}" method="POST" cssClass="logoutForm pull-right">
									<button class="btn btn-default btn-lg" type="submit"> Log out</button>
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
                    <li role="presentation" class="active"><a href="#">Challenges</a></li>
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
						<h1>Images</h1>
					</div>
					<div class="menu2">
						<ul class="nav nav-pills">
							<li role="presentation" class="active"><a href="#">All</a></li>
							<li role="presentation"><a href="#">Recent</a></li>
							<li role="presentation"><a href="#">Random</a></li>
							<li role="presentation" class="pull-right"><button class="btn btn-default" onclick="showUpload()">Upload</button></li>
						</ul>
					</div>
					<!-- Pills -->
				</div>
                <br/>
                
				<!-- Images -->
				<div>
        			<div id="images-managing-all">
        			</div>
				</div>
				<!-- End of Images -->
			</div>
			<!-- End of Central col -->
			<div></div>
		</div>
	</div>
</body>
</html>