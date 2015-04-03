<#--By Yefim Krokhin-->
<html>
<body>
<!-- first challenge -->
<div class="row challenge">
    <div class="challenge-img">
        <img src=${postImage}
                     alt="Image">
    </div>
    <!-- Challenge Info -->
    <div class="challenge-info">
        <div>
            <div style="float: left;">
                <p>${postDate}</p>
            </div>
            <div style="float: right;">
                <p> ${postText} </p>
                <div style="float: right;">
                    <a href="${postOriginUrl}" class="btn btn-primary pull-right" role="button">Open original posts</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>