<html>
    <body>
        <div class="challenge-progress">
            <div class="challenge">
                    <div class="challenge-img">
                        <img src="./images/${imageId}.png"
                             alt="Image For Challenge">
                    </div>
                <!-- Challenge Info -->
                    <div class="challenge-info">
                        <div class="challenge-head">
                            <div class="pull-left">
                                <h3>${subscriptionName}</h3>
                            </div>
                            <div class="pull-right">
                                <h3>${date}</h3>
                            </div>
                        </div>
                        <br/>
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
                                <h3 class="pull-left" style="margin-top: 5px">${postsAdded}/${totalAmountOfPosts} posts</h3>
                                <a href="subscription?subscriptionId=${subscriptionId}" class="btn btn-primary pull-right" role="button">Details</a>
                            </div>
                            <!--By Yefim Krokhin on 02.04.2015-->
                            <div>
                           </div>
                        </div>
                    </div>
                <div style="clear:both;"></div>
                <!-- End of Challenge Info -->
            </div>
        </div>
    </body>
</html>