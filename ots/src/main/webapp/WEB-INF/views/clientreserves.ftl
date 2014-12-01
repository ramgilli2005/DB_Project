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
<span align="left" style="padding-left: 40px;">Oil Reserves & Credit</span><br/>
   <div align="center" style="color: orange;">
 	
	<form name="myform" method="POST" action="" >
	<table align=LEFT cellspacing=0 cellpadding=6 style="BORDER:#E6e6e6 10px solid; font-family:'Helvetica,Arial,sans-serif'; font-size: 13pt; margin-top: 18px; margin-left: 40px; color: black;width: 450px;">
		<TR bgcolor=#add8e6 >
	 	<TH style="color:white;padding-left: 50px; padding-top: 10px; " >Current Oil Reserves</TH>
	 	<TH style="color:white;padding-left: 50px; padding-top: 10px; " >Credit</TH>
		</TR>
		
		<TR bgcolor=orange >
		<TD colspan=2><input type=text id="cur_qty" name=cur_qty value=<#if model['curQty']??>"${model.curQty}"<#else>"0"</#if> readonly /></TD>
		<TD colspan=2><input type=text id="credit" name=credit value=<#if model['credit']??>"${model.credit}"<#else>"0"</#if> readonly /></TD>	
		</TR>
	 	
	 </table>
	 </form>
	 
	</div>

</body>
</html>