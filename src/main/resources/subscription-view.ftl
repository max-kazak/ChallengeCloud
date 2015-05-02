<#--By Yefim Krokhin-->
<html>
<body>
<!-- first challenge -->
<div class="row challenge">
<#--TODO reformat shape of post-->

<#-- <div class="col-xs-6 col-sm-3 placeholder">
     <img src=${postImage} class="center-block img-responsive img-rounded" alt="Image">
     <h2>${postText}</h2>
     <span class="text-muted">${postDate}</span>
     <a href="${postOriginUrl}" class="btn btn-primary pull-right" role="button">Open original posts</a>
 </div>-->

    <div class="col-md-3">
        <div class="challenge-img">
            <img src="${postImage}" class="center-block img-responsive img-rounded"
                 alt="Image">
        </div>
    </div>

    <div class="col-md-9">
        <div class="challenge-info">
            <div class="challenge-head">
            <#--<div class="pull-left">
                <p>${subscriptionName}</p>
            </div>-->
                <div class="pull-right">
                    <p>${postDate}</p>
                </div>
            </div>

            <h2>${postText}</h2>

            <div class="bottom-details">
                <div>
                    <a href="${postOriginUrl}" class="btn btn-primary pull-right" role="button">Open original post</a>
                </div>
            </div>
        </div>
    </div>

</body>
</html>