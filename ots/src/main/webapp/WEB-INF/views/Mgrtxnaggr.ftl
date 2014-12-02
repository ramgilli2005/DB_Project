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
    <form name="myform" method="POST" action="Mgrtxnaggr.html" >
		Start Date[YYYY/MM/DD]: <input type=text id="startdate" name=startdate />
		<br>
		End Date [YYYY/MM/DD]: <input type=text id="enddate" name=enddate />
		</br>	 
		<select id= "type" class="commonSelect" name = "type" >
							<option value="DAILY" selected>Daily</option>
							<option value="WEEKLY">Weekly</option>
							<option value="MONTHLY">Monthly</option>
		</select>
		<input type=submit class="submitButton" value="Get Aggregate" />
	</form>
	</div>

</body>
</html>