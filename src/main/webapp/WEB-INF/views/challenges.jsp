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
    function crunchifyAjax() {
    	num = num + 1;
        $.ajax({
            url : 'challengestest.html',
            success : function(data) {
                $('#result').html(data);
            }
        });
        $.ajax({
            url : 'challenges-all.html?num=' + num.toString(),
            success : function(data) {
                $('#challenges-all').html(data);
        	}
        });
    }
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
    <div align="center">
        <br> <br> ${message} <br> <br>
        <div id="result"></div>
        <div id="challenges-all"></div>
        <br>
        <p>
            by <a href="http://crunchify.com">Crunchify.com</a>
        </p>
    </div>
</body>
</html>