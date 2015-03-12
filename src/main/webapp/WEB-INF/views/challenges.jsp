<html>
<head>
<TITLE>Crunchify - Spring MVC Example with AJAX call</TITLE>
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
    <link rel="stylesheet" href="./resources/css/font-awesome.min.css">
    <link rel="stylesheet" href="./resources/css/main.css">
    <link rel="stylesheet" href="./resources/css/settings.css"> 
 
<style type="text/css">
body {
    background-image:
        url('http://cdn3.crunchify.com/wp-content/uploads/2013/03/Crunchify.bg_.300.png');
}
</style>
<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
	var num = 0;
	function appendChallenge() {
    	num = num + 1;
        $.ajax({
            url : 'challenges-all.html?num=' + num.toString(),
            success : function(data) {
                $('#challenges-all').append(data);
        	}
        });
	}
    function crunchifyAjax() {
    	//num = num + 1;
        $.ajax({
            url : 'challengestest.html',
            success : function(data) {
                $('#result').html(data);
            }
        });
        appendChallenge();
    }
</script>

<script type="text/javascript">
	var loading = false;
	$(window).scroll(function(){
	//if((($(window).scrollTop()+$(window).height())+2)>=$(document).height()){ //Doesn't work
	if($(document).height() <= $(window).scrollTop() + 1920){
		if(loading == false){
			loading = true;
	    	num = num + 1;
			//$('#loadingbar').css("display","block");
			//$.get("load.php?start="+$('#loaded_max').val(), function(loaded){
			//	$('body').append(loaded);
			//	$('#loaded_max').val(parseInt($('#loaded_max').val())+50);
			//	$('#loadingbar').css("display","none");
			//	loading = false;
			//});
			appendChallenge();
			loading = false;
		}
		  
	}
	});
	$(document).ready(function() {
		//$('#loaded_max').val(50);
		for (i =0; i< 20; i++) {
			appendChallenge();
			$(window).scrollTo(0);
		}
	});
</script> 
 
<script type="text/javascript">
    var intervalId = 0;
    intervalId = setInterval(crunchifyAjax, 3000);
</script>

<!--  <script type="text/javascript">
	var loadedRows = 0;

	$(window).bind('scrollstop', function() {
		scroll_top = $(document).scrollTop();
		var page_height = $(document).height();
		wind_height = $(window).height();
		if ((page_height - scroll_top) < wind_height * 2) {
			var iLoad = 0;
			while (iLoad++ < 5)
				if (loadedRows <= tableData.length)
					addRow(tableData[loadedRows++]);
		}
	});
	
	function addRow(row){
		        var newRow = $("#dataTable").find('tbody').append($('<tr>'));     
		        for(var td in row){
		            $(newRow).append($('<td>').append($('<a>', {
		                href: "/images/" + row[td].f + ".jpg"
		            }).click(function(){viewgallery(row[td].i );return false;})
		            .append($('<img>',{src: "/images/small/" + row[td].f + ".jpg",alt: "..."}))));
		        }
		     }
</script>    -->

</head>
 
<body>
<ifame>
    <div align="center">
        <br> <br> ${message} <br> <br>
        <div id="result"></div>
        <div id="challenges-all"></div>
        <br>
        <p>
            by <a href="http://crunchify.com">Crunchify.com</a>
        </p>
    </div>
</ifame>
</body>
</html>