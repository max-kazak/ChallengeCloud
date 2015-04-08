<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!--Created by Yefim on 02.03.2015-->
<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/welcome.css" />">
    <title>CCloud|Subscriptions</title>

    <script type="text/javascript"
            src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <%--Script was created by Vova, rearrnaged by Yefim--%>
    <script type="text/javascript">
        var numToShow = 5;// how many subscriptions to request
        var numShown = 0;// Already received subscriptions
        var from;// Request "numToShow" (5) subscriptions starting from number "from" (1)
        max_num = ${totalAmount};
        function appendPosts() {
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
    <c:url var="home" value="/home" />
    <c:url var="welcome" value="/welcome" />
    <c:url var="about" value="#" />
    <c:url var="ourteam" value="#" />
    <c:url value="/j_spring_security_logout" var="logoutUrl" />
    <div class="nav">
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <a href="${welcome}">
                        <i class="fa fa-cloud"></i>ChallengeCloud
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
    <div class="container body">
        <div class="col-md-10 text-left left-block">
            <h2> Posts </h2>

        <div class="col-md-2 text-left right-block">
            <h2> Options </h2>

            <div class="col-md-10">
                <ul class="pull-left">
                    <li>
                        <button type="button" class="btn btn-default"> Cancel</button>
                    </li>

                    <li>
                        <button type="button" class="btn btn-default"> Dare</button>
                    </li>
                </ul>
            </div>
        </div>
    </div>


 <%--   <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div>
                <div id="subscription-send" class="row placeholders">
                </div>
            </div>

        </div>
        <div></div>
    </div>--%>
        <div class="row placeholders">
            <div id="subscription-send">

            </div>
        </div>

</div>
</body>
</html>