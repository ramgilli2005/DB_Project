<#import "/spring.ftl" as spring />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="../js/jquery-1.11.1.min.js"></script>
<title>WELCOME TO OTS</title>

<script>

function logincheck(){
	var x=document.myform.qty.value;
	if(x==''){
		alert("Please enter quantity!!!");
	 	return false;	
	}
	var y = document.myform.cur_qty.value;
	if(parseFloat(x) - parseFloat(y) < 0){
		alert("You have insufficient OIL for SELL!!!");
	 	return false;
	}
}
$(document).ready(function(){

});

	
</script>
</head>
<body background-color="blue;">
	<span align="left" style="padding-left: 40px;">Place Trade</span><br/>
   <div align="center" style="color: orange;">
 	
	<form name="myform" method="POST" action="preview.html" onsubmit="return logincheck()" >
	<table align=LEFT cellspacing=0 cellpadding=6 style="BORDER:#E6e6e6 10px solid; font-family:'Helvetica,Arial,sans-serif'; font-size: 13pt; margin-top: 18px; margin-left: 40px; color: black;width: 450px;">
		<TR bgcolor=#add8e6 >
		<TH style="color:white;padding-left: 50px; padding-top: 10px; " >Current Oil Reserves</TH>
		<TH colspan=2><input type=text id="cur_qty" name=cur_qty value=<#if model['curQty']??>"${model.curQty}"<#else>"0"</#if> readonly /></TH>
		</TR>
		<TR bgcolor=orange >	
		<TD style="padding-left: 50px; padding-top: 10px; " >Order Type</TD>
		<TD colspan=2> Quantity </TD>						
		</TR>
		<TR bgcolor=orange>
		<TD style="color:green; padding-left: 50px; padding-top: 10px; ">
		<select id= "ordertype" class="commonSelect" name = "ordertype" >
							<option value="BUY" selected>BUY</option>
							<option value="SELL">SELL</option>
		</select>
		</TD>
		<TD colspan=2 style="padding-top: 10px;">
		<input type=text id="qty" name=qty style="height: 25px; width:100px;"/>  
		</TD>						
		</TR>
		<TR bgcolor="orange" >
		<TD style="color:green; padding-left: 50px; padding-top: 10px; " >
		Commission Type:
		</TD>
		<TD colspan=2 style="padding-top: 10px; padding-bottom: 15px;">
		<select id= "commsntype" class="commonSelect" name = "commsntype" >
							<option value="CASH" selected>CASH</option>
							<option value="OIL">OIL</option>
		</select>
		
		</TD>						
		</TR>
		<TR bgcolor="orange" >
		<TD colspan=3 align="center" style="padding-top: 10px; padding-bottom: 15px;" >
		<input type=submit class="submitButton" value="Preview" />
		</TD>
		</TR>
	</table>
	</form>	
<#if model['errorMsg']??>
		<div id="errordiv" style="color:red;">${model.errorMsg}</div>
</#if>
</body>
