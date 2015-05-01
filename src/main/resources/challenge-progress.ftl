<html>
    <body>
        <div class="challenge-progress">
            <div class="challenge">
                <div class="col-md-3">
                    <div class="challenge-img">
                        <img src="./images/${imageId}.png"
                             alt="Image For Challenge">
                    </div>
                </div>
                <!-- Challenge Info -->
                <div class="col-md-9">
                    <div class="challenge-info">
                        <div class="challenge-head">
                            <div class="pull-left">
                                <p>${subscriptionName}</p>
                            </div>
                            <div class="pull-right">
                                <p>${date}</p>
                            </div>
                        </div>
                        <div class="progress">
                        <!--added Progress by Yefim Krokhin on 15.04.2015-->
                            <div
                                    class="progress-bar progress-bar-success progress-bar-striped"
                                    role="progressbar" aria-valuenow="${progress}" aria-valuemin="0"
                                    aria-valuemax="100" style="width: ${progress}%">
                            ${progress}% Complete
                            </div>
                        </div>
                        <div class="bottom-details">
                            <div>
                                <p class="pull-left">${postsAdded}/${totalAmountOfPosts} posts</p>
                            </div>
                            <!--By Yefim Krokhin on 02.04.2015-->
                            <div>
                                <a href="subscription?subscriptionId=${subscriptionId}" class="btn btn-primary pull-right" role="button">Details</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>
                <!-- End of Challenge Info -->
            </div>
        </div>
    </body>
</html>