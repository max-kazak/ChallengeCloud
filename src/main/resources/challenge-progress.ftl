<html>
<body>
<div class="row challenge">
    <div class="challenge-img">
        <img src="./../../resources/img/ch1.PNG"
             alt="Image For Challenge">
    </div>
    <!-- Challenge Info -->
    <div class="challenge-info">
        <div>
            <div style="float: left;">
                <p>${subscriptionName}</p>
            </div>
            <div style="float: right;">
                <p>${date}</p>
            </div>
        </div>
        <br/> <br/>

        <div class="progress">
        <#--added Progress by Yefim Krokhin on 15.04.2015-->
            <div
                    class="progress-bar progress-bar-success progress-bar-striped"
                    role="progressbar" aria-valuenow="${progress}" aria-valuemin="0"
                    aria-valuemax="100" style="width: ${progress}%">
            ${progress}% Complete
            </div>
        </div>
        <div style="float: left;">
            <p class="pull-left">${postsAdded}/${totalAmountOfPosts} posts</p>
        </div>
        <#--By Yefim Krokhin on 02.04.2015-->
        <div style="float: right;">
            <a href="subscription?subscriptionId=${subscriptionId}" class="btn btn-primary pull-right" role="button">Details</a>
        </div>
    </div>
    <!-- End of Challenge Info -->
</body>
</html>