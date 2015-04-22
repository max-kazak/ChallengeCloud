<#--By Yefim Krokhin-->
<html>
<body>
<!-- first challenge -->
<div class="row challenge">
    <#--<div class="challenge-img">
        <img src=${postImage}
                     alt="Image" class="img-thumbnail">
    </div>-->
    <!-- Tweet Info -->
<#--    <div class="challenge-info">
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
    </div>-->
<#--TODO reformat shape of post-->

    <div class="col-xs-6 col-sm-3 placeholder">
        <img src=${postImage} class="center-block img-responsive img-rounded" alt="Image">
        <h2>${postText}</h2>
        <span class="text-muted">${postDate}</span>
        <a href="${postOriginUrl}" class="btn btn-primary pull-right" role="button">Open original posts</a>
    </div>

</body>
</html>