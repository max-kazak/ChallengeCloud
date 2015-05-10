<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<!--Created by Vladimir Zhdanov on 22.03.2015-->
<head>
<TITLE>Challenge Creation</TITLE>
	<link rel="shortcut icon" href="<c:url value="/resources/img/cloud-icon.png" />" />
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />"> 

	<script type="text/javascript"
	    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

	<script type="text/javascript">
		var chosenImageId = "ErrorId";

        var numToShow = 5;// how many subscriptions to request
        var numShown = 0;// Already received subscriptions
        max_num = ${totalAmount};

        function appendImages() {
            if(numShown<max_num) {
                if(numShown+numToShow>max_num) {
                    numToShow = max_num-numShown;
                }

                $.ajax({
                    url: 'challenge_creation_images.html?numToShow=' + numToShow.toString()+ '&numShown='+numShown.toString(),
                    success: function (data) {
                        $('#images').append(data);
                    }
                });
                numShown += numToShow;
            }   
        }

		function createChallenge() {
	    	var title = document.getElementById("title").value;
	    	var description = document.getElementById("description").value;
	    	var diff = document.getElementById("diff").value;
	    	var hash = document.getElementById("hash").value;
	    	//'challenge_creation.html?title=' + title + '&description=' + description + '&diff=' + diff + '&hash=' + hash
	    	$('#msg').append("<p>Processing your request</p>");
	    	$.ajax({
	            url : 'create_challenge.html?title=' + title + '&description=' + description + '&diff=' + diff + '&hash=' + hash + '&imageId=' + chosenImageId,
	            success : function(data) {
	            	$('#msg').empty();
               		$('#msg').append(data);
        		}
        	});
		}

		function chooseImage(imageId) {
			chosenImageId = imageId
			$('#chosen_image').append(chosenImageId);
		}

		function showImages() {
			document.getElementById('shown_images').style.display='block';
			appendImages();
		}
	</script> 
</head>
 
<body>
	<!--Page content-->
	<c:url var="welcome" value="/welcome"/>
    <c:url var="home" value="/home"/>
    <c:url var="settings" value="/settings"/>
    <c:url var="subscription" value="/subscription" />
    <c:url var="images_managing" value="/images-managing"/>
    <c:url var="about" value="#"/>
    <c:url var="ourteam" value="#"/>
    <c:url var="challenges" value="#"/>
    <c:url var="trend" value="#"/>
	<c:url var="trophyroom" value="/trophy"/>
    <c:url var="active" value="#"/>
    <c:url var="archive" value="#"/>
    <c:url var="upcoming" value="#"/>
	<c:url var="relevantPage" value="/"/>
    <c:url value="/j_spring_security_logout" var="logoutUrl"/>


	<div class="nav navigator">
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <a href="${relevantPage}"> <i class="fa fa-cloud"></i>ChallengeCloud
                    </a>
                </div>

                <div class="col-md-9">
                    <ul class="pull-left">
                        <li><a href="${home}">Home</a></li>
                        <li><a href="${about}">About</a></li>
                        <li><a href="${ourteam}">Our Team</a></li>
                    </ul>
                    <form:form name="logoutForm" action="${logoutUrl}" method="POST" cssClass="logoutForm pull-right">
                        <button class="btn btn-default btn-lg" type="submit"> Log out</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>


	<div class="main">
		<div class="row">
			<div class="col-lg-3">
				<p>${message}</p>
				<div id="debug"></div>
			</div>

			<div class="col-lg-6">
				<button type="button" onclick="showImages()">Choose Image</button>

				<div id="chosen_image">
				</div>

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

			<div class="col-lg-3">
				<div id="shown_images" style="display: none;">
					<div><a href="${images_managing}">Or upload a new image</a></div>
					<div id="images"></div>
					<button type="button" onclick="appendImages()">More</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>