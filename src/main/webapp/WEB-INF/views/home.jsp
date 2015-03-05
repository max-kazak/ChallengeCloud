<!DOCTYPE html>
<html>
  <!--Created by Vladimir Zhdanov on 05.03.2015-->
  <head>
    <title>Settings</title>
    <link rel="stylesheet" href="./../../resources/css/bootstrap.css">
    <link rel="stylesheet" href="./../../resources/css/font-awesome.min.css">
    <link rel="stylesheet" href="./../../resources/css/main.css">
    <link rel="stylesheet" href="./../../resources/css/settings.css">
  </head>

  <body>
  <div class="main">
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
              <li><a href="./home.jsp">Home</a></li>
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
    <!-- Travel into second col?? -->
    <div class="heading">
      <h1>Challenges</h1>
    </div>

    <div class="row">
      <div class="col-md-3">
        <div class="menu">
          <!-- <ul class="nav nav-pills nav-stacked"> -->
          <ul class="menu">
            <li role="presentation" class="active"><a href="#">Challenges</a></li>
            <li role="presentation"><a href="#">Trend</a></li>
            <li role="presentation"><a href="#">Trophy Room</a></li>
            <li role="presentation"><a href="./settings.jsp">Settings</a></li>
          </ul>
        </div>
      </div>
      <div class="col-md-9">
        <div class="row">
          <div class="challenges">
            <ul class="nav nav-pills">
                <li role="presentation" class="active"><a href="#">Active</a></li>
                <li role="presentation"><a href="#">Archive</a></li>
                <li role="presentation"><a href="#">Upcoming</a></li>
            </ul>
          </div>
        </div>
        <div class="row"><!-- and some challenges inside -->
            <div class="row"><!-- first challenge -->
              <div class="col-sm-4 col-md-9">
                <div class="challenge">
                    <div class="row">
                      <div class="col-sm-4 col-md-3 picture">
                        <img src="./../../resources/img/ch1.PNG" alt="Image For Challenge">
                      </div>
                      <div class="col-sm-4 col-md-8">
                        <div class="row">
                            <ul class="pull-left">
                              <h3>Challenge Name</h3>
                            </ul>
                            <ul class="pull-right">
                              <h3>1 jan, 2015</h3>
                            </ul>
                        </div>
                        <div class="row">
                            <div class="progress">
                              <div class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                <span class="sr-only">40% Complete (success)</span>
                              </div>
                            </div>
                        </div>
                        <div class="row">
                            <h3 class="pull-left">5/20 posts</h3>
                        </div>
                        <div class="row">
                            <p><a href="#" class="btn btn-primary pull-right" role="button">Details</a>
                        </div>
                      </div>
                    </div>
                </div>
              </div>
            </div> <!-- end of 1 Challenge -->
            <div class="row"><!-- first challenge -->
              <div class="col-sm-4 col-md-9">
                <div class="challenge">
                    <div class="row">
                      <div class="col-sm-4 col-md-3 picture">
                        <img src="./../../resources/img/ch2.PNG" alt="Image For Challenge">
                      </div>
                      <div class="col-sm-4 col-md-8">
                        <div class="row">
                            <ul class="pull-left">
                              <h3>Challenge Name</h3>
                            </ul>
                            <ul class="pull-right">
                              <h3>1 jan, 2015</h3>
                            </ul>
                        </div>
                        <div class="row">
                            <div class="progress">
                              <div class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                <span class="sr-only">40% Complete (success)</span>
                              </div>
                            </div>
                        </div>
                        <div class="row">
                            <h3 class="pull-left">5/20 posts</h3>
                        </div>
                        <div class="row">
                            <p><a href="#" class="btn btn-primary pull-right" role="button">Details</a>
                        </div>
                      </div>
                    </div>
                </div>
              </div>
            </div> <!-- end of 1 Challenge -->
        </div>
      </div>
    <div>
  </div>
  </body>
</html>