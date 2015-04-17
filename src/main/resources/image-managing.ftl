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
	    <button class="btn btn-default" onclick="showEdit('${imageId}', '${imageName}')">Edit</button>
	    <button class="btn btn-default" onclick="removeImage('${imageId}')">Remove</button> <!-- btn-rimary -->
	  </p>
	</div>
  </div>
  <!-- End of Image Info -->
</div>
<!-- end of Image -->
<br/>