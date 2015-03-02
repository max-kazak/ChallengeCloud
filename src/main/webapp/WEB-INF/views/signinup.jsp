<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="./../../resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="./../../resources/css/font-awesome.min.css">
	<link rel="stylesheet" href="./../../resources/css/main.css">
	<title>CCloud|Login</title>
</head>
<body>
	<div class="nav navigator">
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
                <li><a href="#">Sign UP</a></li>
                <li><a href="#">Sign IN</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>

	<div class="container body">
		<div class="col-md-6 text-left left-block">
			
			<div class="col-md-12">
				<h2> Sign In using existing account </h2>
				<h3 > In Social Networks </h3>
				<div class="">
					<a href="#" class="soc-net"><i class="fa fa-vk fa-3x"></i></a>
					<a href="#" class="soc-net"><i class="fa fa-facebook-official fa-3x"></i></a>
					<a href="#" class="soc-net"><i class="fa fa-twitter fa-3x"></i></a>
					<a href="#" class="soc-net"><i class="fa fa-instagram fa-3x"></i></a>
				</div>
				<h3> In ChallengeCloud </h3>
			</div>
			<div class="col-md-8">
				<form action="form-horizontal">
					<div class="form-group">
						<label for="inputEmail" class="control-label col-md-2"></label>
						<div>
							<input type="email" class="form-control" id="inputEmail" placeholder="Email" style="font-size: 20px;">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword" class="control-label col-md-2"></label>
						<div>
							<input type="password" class="form-control" id="inputPassword" placeholder="Password" style="font-size: 20px;">
						</div>
					</div>
					<div class="form-group">
						<div class="checkbox">
							<label  style="font-size: 16px;">
								<input type="checkbox"> Remember Me
							</label>
						</div>
					</div>
					<div class="form-group text-left">
						<div>
							<button type="submit" class="btn btn-default"> Sign In </button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- 
		<div class="vertical-divider"></div> -->

		<div class="col-md-6 text-left right-block">
			<div class="col-md-8">
				<h2> Create new account </h2>
				<form action="form-horizontal" class>
					<div class="form-group">
						<label for="inputLogin" class="control-label col-md-2"></label>
						<div>
							<input type="login" class="form-control" id="inputLogin" placeholder="Login" style="font-size: 20px;">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword" class="control-label col-md-2"></label>
						<div>
							<input type="password" class="form-control" id="inputPassword" placeholder="Password" style="font-size: 20px;">
						</div>
					</div>
					<div class="form-group">
						<label for="repeatPassword" class="control-label col-md-2"></label>
						<div>
							<input type="password" class="form-control" id="repeatPassword" placeholder="Repeat Password" style="font-size: 20px;">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail" class="control-label col-md-2"></label>
						<div>
							<input type="email" class="form-control" id="inputEmail" placeholder="Email" style="font-size: 20px;">
						</div>
					</div>
					<div class="form-group">
						<div>
							<button type="submit" class="btn btn-default"> Sign Up </button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>