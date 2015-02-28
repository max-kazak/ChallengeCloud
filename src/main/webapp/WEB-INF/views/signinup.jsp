<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="./../../resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="./../../resources/css/font-awesome.min.css">
	<link rel="stylesheet" href="./../../resources/css/main.css">
	<title>Document</title>
</head>
<body>

	<div class="container navigator">
		<div class="row">
			<div class="col-md-4">
				<a href="#" id="project">
					<div class="header">
						<i class="fa fa-cloud"></i> ChallengeCloud
					</div>
				</a>
			</div>
			<div class="col-md-8">
				<ul class="nav navbar-nav">
					<li class="list-item" >
						<a href="#" class="linker"> Home </a>
					</li>
					
					<li class="list-item" >
						<a href="#" class="linker"> About </a>
					</li>
				
					<li class="list-item last-list-item">
						<a href="#" class="linker"> Our Team </a>
					</li>
				</ul>
			</div>
		</div>
	</div>

	<div class="container body">
		<div class="col-md-6 text-left left-block">
			<h2> Sign In using existing account </h2>
			<div class="col-md-12">
				<h3 > In Social Networks </h3>
				<div class="row">
					<a href="#" class="soc-net"><i class="fa fa-vk fa-3x"></i></a>
					<a href="#" class="soc-net"><i class="fa fa-facebook-official fa-3x"></i></a>
					<a href="#" class="soc-net"><i class="fa fa-twitter fa-3x"></i></a>
					<a href="#" class="soc-net"><i class="fa fa-instagram fa-3x"></i></a>
				</div>
				<h3> In ChallengeCloud </h3>
			</div>
			<div class="col-md-10">
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
					<div class="form-group text-left">
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
		
		<div class="col-md-6 text-left right-block">
			<h2> Create new account </h2>
			<div class="col-md-10">
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