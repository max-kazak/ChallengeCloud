<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <!--Created by Andrey on 01.03.20115-->
  <head>
    <title>Settings</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/settings.css" />">
  </head>

  <body>
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
              <li><a href="<c:url value="/home" />">Home</a></li>
              <li><a href="#">About</a></li>
              <li><a href="#">Our Team</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>

    <div class="heading">
      <h1>Settings</h1>
    </div>

    <div class="row">
      <div class="col-md-3">
        <div class="menu">
          <ul>
            <li role="presentation"><a href="#">User</a></li>
            <li role="presentation"><a href="#">General</a></li>
            <li role="presentation"><a href="#">Social Networks</a></li>
            <li role="presentation" class="active"><a href="#">Security</a></li>
          </ul>
        </div>
      </div>
      <div class="col-md-9">
        <div class="settings">
          <ul>
            <li>Change nickname</li>
            <li>Change password</li>
          </ul>
        </div>
      </div>
    <div>
  </body>
</html>