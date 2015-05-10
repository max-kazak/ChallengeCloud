<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<!--Created by Vladimir Zhdanov on 05.03.2015-->
<head>
    <title>CCloud | Trophies</title>
    <link rel="shortcut icon" href="<c:url value="/resources/img/cloud-icon.png" />"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css" />">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/home.css" />">
    <link rel="image/png" href="&#xf0c2;"/>
    <script type="text/javascript"
            src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

    <script>
        $(document).ready(function () {
            $.ajax({
                url: 'gettrophies.html',
                success: function (response) {
                    $('#achievements').append(response);
                }
            });
        });
    </script>


<body>
<c:url var="welcome" value="/welcome"/>
<c:url var="home" value="/home"/>
<c:url var="settings" value="/settings"/>
<c:url var="subscription" value="/subscription"/>
<c:url var="about" value="#"/>
<c:url var="ourteam" value="#"/>
<c:url var="challenges" value="/challenges"/>
<c:url var="trend" value="#"/>
<c:url var="trophyroom" value="/trophy"/>
<c:url var="active" value="#"/>
<c:url var="archive" value="#"/>
<c:url var="upcoming" value="#"/>
<c:url value="/j_spring_security_logout" var="logoutUrl"/>

<div class="header">
    <div class="nav navigator">
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <a href="${welcome}"> <i class="fa fa-cloud"></i>ChallengeCloud
                    </a>
                </div>

                <div class="col-md-9">
                    <ul class="pull-right">
                        <li><a href="${home}">Home</a></li>
                        <li><a href="${about}">About</a></li>
                        <li><a href="${ourteam}">Our Team</a></li>
                        <li>
                            <form:form name="logoutForm" action="${logoutUrl}" method="POST"
                                       cssClass="logoutForm pull-right">
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

<!-- For Horizontal and Vertical lines not contact -->
<div class="main">
    <div class="row">
        <div class="col-md-2 col-md-offset-2">
            <div class="menu">
                <!-- <ul class="nav nav-pills nav-stacked"> -->
                <ul class="nav nav-pills nav-stacked">
                    <li role="presentation" class="active"><a href="${challenges}">Challenges</a></li>
                    <li role="presentation"><a href="${trend}">Trend</a></li>
                    <li role="presentation"><a href="${trophyroom}">Trophy Room</a></li>
                    <li role="presentation"><a href="${settings}">Settings</a></li>
                </ul>
            </div>
        </div>

        <div class="col-md-6">
            <div>
                <div id="achievements">
                    <!--Achievements are here -->
                </div>
            </div>
        </div>
        <!-- End of Central col -->
    </div>
</div>
</body>
</html>
