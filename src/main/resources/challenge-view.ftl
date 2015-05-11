<html>
<body>
<!-- first challenge -->
<div class="row challenge-view">
    <div class="challenge-img">
        <img src="./images/${imageId}.png"
             alt="Image For Challenge">
    </div>
    <!-- Challenge Info -->
    <div class="challenge-info">
        <div>
            <div style="float: left;">
                <h3> ${challengeName} </h3>
            </div>
            <div style="float: right;">
                <h3>Difficulty: ${challengeDifficulty}</h3>
            </div>
        </div>
        <br/> <br/>

        <div>
            <div>
            	<br/>
                <p> ${challengeDescription} </p>
            </div>
        </div>
        <div style="float: right;">
            <!-- <p class="pull-left"> </p> -->
        </div>
        <div style="float: right;">
            <p>
                <a href="#" class="btn btn-default" role="button"
                   onclick="showMore('${challengeName}','${challengeDescription}' , '${challengeDifficulty}')">More</a>
                <a href="#" class="btn btn-default" role="button" onclick="addChallenge('${challengeId}')">Add</a>
            </p>

        </div>
    </div>
    <!-- End of Challenge Info -->
</div>
<!-- end of 2 Challenge -->
</body>
</html>