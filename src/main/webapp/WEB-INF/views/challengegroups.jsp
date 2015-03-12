<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--Created by Yefim on 02.03.2015-->
<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/welcome.css" />">
    <title>CCloud|Groups</title>
    <script type="text/javascript"
            src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <script type="text/javascript">
        //TODO: finish with ajax later @Nipel-Crumple
        $(document).ready(function() {
            loadImg();
            loadImg();
            $(window).scroll(loadImg);
            function loadImg() {
                if ($(window).scrollTop() + $(window).height() >= $(document).height()) {
                    console.log("SCROLLING SHMOLING");
                    for (var i = 0; i < 3; i++) {
                        $.ajax({
                            url: 'ajaxscroll.html',
                            success: function (img) {
                                $('#image-holder').append("<div class='col-md-4'>" +
                                "                               <div class='thumbnail'>" + img + "</div>" +
                                "                          </div>");
                            }
                        });
                    }
                }
            }
        });

    </script>

</head>
<body>

    <div class="main">
        <div class="nav">
            <div class="container">
                <div class="row">
                    <div class="col-md-3">
                        <a href="#">
                            <i class="fa fa-cloud"></i>ChallengeCloud
                        </a>
                    </div>
                    <div class="col-md-9">
                        <ul class="pull-left">
                            <li><a href="#">Home</a></li>
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

        <div class="neighborhood-guides">
            <div class="container" id="image-holder">
                <h2>
                Choose challenge category
                </h2>
            </div>
        </div>
    </div>

</body>
</html>