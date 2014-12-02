<#import "/spring.ftl" as spring />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="../js/jquery-1.11.1.min.js"></script>
<title>WELCOME TO OTS</title>

<script>
<style>
#searchbut {
    position:absolute;
	transition: .5s ease;
    left: 202px;
    top: 198px;

}
</style>
	
</script>
</head>
<body>

    <div align="left" style="margin-left: 40px;">
    	<form name="myform" method="POST" action="searchclnt.html" onsubmit="return logincheck()" >
		<table align=LEFT cellspacing=0 cellpadding=6 style="BORDER:#E6e6e6 10px solid; font-family:'Helvetica,Arial,sans-serif'; font-size: 13pt; margin-top: 18px; margin-left: 40px; color: black;width: 450px;">
			<TR bgcolor=orange>
		<TD style="color:green; padding-left: 50px; padding-top: 10px; padding-bottom: 10px;">
		<select id= "searchtype" class="commonSelect" name = "searchtype" >
							<option value="ID" selected>Id</option>
							<option value="NAME">Name</option>
							<option value="CITY">City</option>
		</select>
		</TD>
		<TD colspan=2 style="padding-top: 10px; padding-bottom: 10px;">
		<input type=text id="searchval" name=searchval style="height: 25px; width:100px;"/>  
		</TD>
		</table>
		<br/>
		<br/>
		<br/><br/><br/>
		<div style="margin-left:195px;">
		<input type=submit class="submitButton" id="searchbut" value="Search" />
		</div>
		</form>		
	</div>

</body>
