<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <title>CCloud | Login</title>
</head>
<body>
<div class="main">
    <c:url var="signinup" value="/signinup"/>
    <c:url var="home" value="/home"/>
    <c:url var="welcome" value="/welcome"/>
    <c:url var="ourteam" value="#"/>
    <c:url var="about" value="#"/>
    <div class="nav navigator">
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
                    <ul class="pull-right">
                        <li><a href="${signinup}">Sign UP</a></li>
                        <li><a href="${signinup}">Sign IN</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="container body">
        <div class="col-md-6 text-left left-block">

            <div class="col-md-12">
                <h2> Sign In using existing account </h2>

                <h3> In Social Networks </h3>

                <div class="">
                    <form action="<c:url value="/signin/twitter" />" method="POST" class="submitForm">
                        <button type="submit"><i class="fa fa-twitter fa-3x" onclick=""></i></button>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                    <form action="#" method="POST" class="submitForm">
                        <button type="submit"><i class="fa fa-vk fa-3x"></i></button>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                    <form action="#" method="POST" class="submitForm">
                        <button type="submit"><i class="fa fa-facebook-official fa-3x"></i></button>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                    <form action="#" method="POST" class="submitForm">
                        <button type="submit"><i class="fa fa-instagram fa-3x"></i></button>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </div>
                <h3> In ChallengeCloud </h3>
            </div>
            <div class="col-md-8">
                <c:url value='/j_spring_security_check' var="check"/>
                <form:form class="form-horizontal" name="loginForm" action="${check}" method='POST'>
                    <div class="form-group">
                        <label for="inputEmailExisting" class="control-label col-md-2"></label>

                        <div>
                            <input type="text" class="form-control" name="username" id="inputEmailExisting"
                                   placeholder="Username" style="font-size: 20px;">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPasswordExisting" class="control-label col-md-2"></label>

                        <div>
                            <input type="password" class="form-control" name="password" id="inputPasswordExisting"
                                   placeholder="Password" style="font-size: 20px;">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="checkbox">
                            <label style="font-size: 16px;">
                                <input type="checkbox"> Remember Me
                            </label>
                        </div>
                    </div>
                    <div class="form-group text-left">
                        <div>
                            <button name="submit" type="submit" class="btn btn-default"> Sign In</button>
                        </div>
                    </div>
                </form:form>

                <c:if test="${not empty error}">
                    <div class="error">${error}</div>
                </c:if>
                <c:if test="${not empty msg}">
                    <div class="msg">${msg}</div>
                </c:if>
            </div>
        </div>

        <div class="col-md-6 text-left right-block">
            <div class="col-md-8">
                <h2> Create new account </h2>
                <c:if test="${not empty emailExists}">
                    <div class="msg">${emailExists}</div>
                </c:if>
                <c:url var="register" value="/signinup"/>
                <form:form commandName="user" class="form-horizontal" name="registerForm" action="${register}"
                           method='POST'>
                    <div class="form-group">
                        <label for="inputName" class="control-label col-md-2"></label>

                        <div>
                            <form:input path="name" type="login"
                                        class="form-control" id="inputName"
                                        placeholder="Name" style="font-size: 20px;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputLogin" class="control-label col-md-2"></label>

                        <div>
                            <form:input path="login" type="login"
                                        class="form-control" id="inputLogin"
                                        placeholder="Login" style="font-size: 20px;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="control-label col-md-2"></label>

                        <div>
                            <form:password path="password" class="form-control"
                                           id="inputPassword" placeholder="Password"
                                           style="font-size: 20px;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="control-label col-md-2"></label>

                        <div>
                            <form:password path="confirmPassword" class="form-control"
                                           id="inputPassword" placeholder="Confirm password"
                                           style="font-size: 20px;"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputEmail" class="control-label col-md-2"></label>

                        <div>
                            <form:input path="email" type="email"
                                        class="form-control" id="inputEmail"
                                        placeholder="Email" style="font-size: 20px;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div>
                            <button type="submit" class="btn btn-default"> Sign Up</button>
                        </div>
                    </div>
                </form:form>
                <c:if test="${not empty mismatch}">
                    <div>
                        <p>${mismatch}</p>
                    </div>
                </c:if>
            </div>
        </div>
    </div>

</div>
</body>
</html>