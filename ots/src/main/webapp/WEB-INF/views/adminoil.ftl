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
	<#if model['Msg']??>
		<div id="errordiv" style="color:red;">${model.Msg}</div>
	</#if>
    <div align="left">
		Current Oil Price: <input type=text id="oilprice" name=oilprice value=<#if model['oilPrice']??>"${model.oilPrice}"<#else>"0"</#if> readonly />	 
	</div>

</body>
</html>