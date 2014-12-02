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
		<H1>User Created!!!</h1>
		<#if model['userId']??>
		<H2>Used Id: ${model.userId}</H2>
		</#if>
		<a href="login.html">Login</a>	 
	</div>

</body>
</html>