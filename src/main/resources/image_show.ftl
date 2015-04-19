<!-- Image -->
<div class="row challenge">
  <div class="challenge-img">
  <!-- c:url doesn't work!!! -->
    <img src="./images/${imageId}.png"
	 alt="Image">
  </div>
  <!-- Image Info -->
  <div class="challenge-info">
	<div>
	  <div style="float: left;">
	    <p> ${imageName} </p>
	  </div>
	  <div style="float: right;">
	    <p> ${imageDate} </p>
	  </div>
	</div>
	<br /> <br />
	<div>
	  <div>
	    <p>  </p> <!-- Challenge Description -->
	  </div>
	</div>
	<div style="float: right;">
	  <!-- <p class="pull-left"> </p> -->
	</div>
	<div style="float: right;">
	  <p>
		<button id="${imageId}" onclick="chooseImage(this.id)">
		  	Choose this image
		</button>
	  </p>
	</div>
  </div>
  <!-- End of Image Info -->
</div>
<!-- end of Image -->
<br/>