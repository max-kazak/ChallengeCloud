<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<!--Created by Yefim on 02.03.2015-->
<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css" />">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/home.css" />">
<%--<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">--%>
    <title>CCloud|Subscriptions</title>
    <link rel="shortcut icon" href="<c:url value="/resources/img/cloud-icon.png" />" />

    <script type="text/javascript"
            src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <%--Script was created by Vova, rearrnaged by Yefim--%>
    <script type="text/javascript">
        var numToShow = 5;// how many subscriptions to request
        var numShown = 0;// Already received subscriptions
        var from;// Request "numToShow" (5) subscriptions starting from number "from" (1)
        max_num = ${totalAmount};
        function appendPosts() {
            if (max_num ==0) {
                $('#subscription-send').append('<h2>No posts yet</h2><a href="home" class="btn btn-primary" role="button">Home</a>');
            }
            if(numShown<max_num) {
                if(numShown+numToShow>max_num) {
                    numToShow = max_num-numShown;
                }

                $.ajax({
                    url: 'subscription-send.html?numToShow=' + numToShow.toString()+ '&numShown='+numShown.toString(),
                    success: function (data) {
                        $('#subscription-send').append(data);
                    }
                });
                numShown += numToShow;
            }
        }
    </script>

    <script type="text/javascript">
        var loading = false;
        $(window).scroll(function () {
            if ($(document).height() <= $(window).scrollTop() + 1920) {
                if (loading == false) {
                    loading = true;
                    appendPosts();
                    loading = false;
                }

            }
        });
        $(document).ready(function () { //Doesn't work
            //alert("ready")
            appendPosts();
            $(window).scrollTo(0);
        });
    </script>

</head>
<body>

<div class="main">
    <c:url var="welcome" value="/welcome"/>
    <c:url var="home" value="/home"/>
    <c:url var="settings" value="/settings"/>
    <c:url var="subscription" value="/subscription" />
    <c:url var="about" value="#"/>
    <c:url var="ourteam" value="#"/>
    <c:url var="challenges" value="/challenges"/>
    <c:url var="trend" value="#"/>
    <c:url var="trophyroom" value="/trophy"/>
    <c:url var="active" value="#"/>
    <c:url var="archive" value="#"/>
    <c:url var="history" value="#"/>
    <c:url var="upcoming" value="#"/>
    <c:url var="relevantPage" value="/"/>
    <c:url value="/j_spring_security_logout" var="logoutUrl"/>

    <div class="header">
        <div class="nav navigator">
            <div class="container">
                <div class="row">
                    <div class="col-md-3">
                        <a href="${relevantPage}"> <i class="fa fa-cloud"></i>ChallengeCloud
                        </a>
                    </div>

                    <div class="col-md-9">
                        <ul class="pull-right">
                            <li><a href="${home}">Home</a></li>
                            <li><a href="${about}">About</a></li>
                            <li><a href="${ourteam}">Our Team</a></li>
                            <li>
                                <form:form name="logoutForm" action="${logoutUrl}" method="POST" cssClass="logoutForm pull-right">
                                    <button class="btn btn-default btn-lg" type="submit"> Log out</button>
                                </form:form>
                            </li>
                        </ul>
                        <div class="pull-right">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class ="main">
        <div class="row">
            <div class="col-md-2 col-md-offset-2">
                <div class="menu">
                    <!-- <ul class="nav nav-pills nav-stacked"> -->
                    <ul class="nav nav-pills nav-stacked">
                        <li role="presentation" class="active"><a href="${challenges}">Challenges</a></li>
                        <li role="presentation"><a href="${trend}">Trend</a></li>
                        <li role="presentation"><a href="${trophyroom}">Trophy Room</a></li>
                        <li role="presentation"><a href="${history}">My history</a></li>
                        <li role="presentation"><a href="${settings}">Settings</a></li>
                    </ul>
                </div>
            </div>

            <div class="col-md-6">
                <!-- Central col -->
                <div class="row">
                    <div class="pull-right">
                        <ul class="nav nav-pills">
                            <li role="presentation" class="active"><a href="${active}">Active</a></li>
                            <li role="presentation"><a href="${archive}">Archive</a></li>
                            <li role="presentation"><a href="${upcoming}">Upcoming</a></li>
                        </ul>
                    </div>
                </div>

                <div>
                    <div id="subscription-send">
                        <!--CHALLENGES GO HERE -->
                    </div>
                </div>
            </div>
            <!-- End of Central col -->
        </div>
    </div>
</div>
</body>
</html>