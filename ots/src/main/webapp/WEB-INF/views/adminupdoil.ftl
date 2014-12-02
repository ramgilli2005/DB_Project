<#import "/spring.ftl" as spring />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="../js/jquery-1.11.1.min.js"></script>
<title>WELCOME TO OTS</title>

<script>

	
</script>
</head>
<body>

    <div align="left">
    <form name="myform" method="POST" action="adminupdoil.html" onsubmit="return logincheck()" >
		Current Oil Price: <input type=text id="oilprice" name=oilprice value=<#if model['oilPrice']??>"${model.oilPrice}"<#else>"0"</#if> readonly />
		Updated Oil Price: <input type=text id="oilprice_new" name=oilprice_new />
		<input type=submit class="submitButton" value="Update" />	 
	</form>
	</div>

</body>
</html>