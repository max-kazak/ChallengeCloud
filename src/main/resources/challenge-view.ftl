<html>
<body>
<!-- first challenge -->
<div class="row challenge">
  <div class="challenge-img">
    <img src="./images/${imageId}.png"
         alt="Image For Challenge">
      </div>
      <!-- Challenge Info -->
      <div class="challenge-info">
	<div>
	  <div style="float: left;">
	    <p> ${challengeName} </p>
	  </div>
	  <div style="float: right;">
	    <p>Difficulty: ${challengeDifficulty}</p>
	  </div>
	</div>
	<br /> <br />
	<div>
	  <div>
	    <p> ${challengeDescription} </p>
	  </div>
	</div>
	<div style="float: right;">
	  <!-- <p class="pull-left"> </p> -->
	</div>
	<div style="float: right;">
	  <p>
	    <a href="#" class="btn btn-default" role="button" onclick="showMore('${challengeId}')">More</a>
	    <a href="#" class="btn btn-default" role="button" onclick="addChallenge('${challengeId}')">Add</a>
	  </p>

	</div>
      </div>
      <!-- End of Challenge Info -->
    </div>
    <!-- end of 2 Challenge -->
  </body>
</html>