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
		<#if model['errorMsg']??>
			<div id="errordiv" style="color:red;">${model.errorMsg}</div>
		<#else>
			<H1> Payment Succesfull!!! </H1>			
		</#if>	
		
		<a href="clienthome.html">Client Home</a>	 
	</div>

</body>
</html>