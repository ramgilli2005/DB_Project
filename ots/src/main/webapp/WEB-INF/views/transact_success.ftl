<#import "/spring.ftl" as spring />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="../js/jquery-1.11.1.min.js"></script>
<title>WELCOME TO OTS</title>

<script>

function logincheck(){
	var x=document.myform.qty.value;
	if(x==''){
		alert("Enter Both User Name and Password");
	 	return false;	
	}
}	
$(document).ready(function(){

});

function transactRedirect(){
	location.href="clienthome.html";
}
</script>

</head>
<body >
	<span align="left" style="padding-left: 40px;">Place Order Preview</span><br/>
   <div align="center" style="color: orange;">
 	<form name="myform" method="POST" action="payment.html"  >	
	<table align=LEFT cellspacing=0 cellpadding=6 style="BORDER:#E6e6e6 10px solid; font-family:'Helvetica,Arial,sans-serif'; font-size: 13pt; margin-top: 18px; margin-left: 40px; color: black;width: 450px;">
		<TR bgcolor=orange >	
		<TD style="padding-left: 50px; padding-top: 10px; " >Order Type</TD>
		<TD colspan=2> Quantity </TD>						
		</TR>
		
		<TR bgcolor=orange>
		<TD style="color:green; padding-left: 50px; padding-top: 10px; ">
		<input type=text id="trntype" style="width:70px;" name=trntype readonly <#if model['trntype']??>value="${model.trntype}"</#if>  />
		</TD>
		<TD colspan=2 style="color:green; padding-top: 10px; ">
		<input type=text id="trnqty" style="width:70px;" name=trnqty readonly <#if model['trnQty']??>value="${model.trnQty}"</#if> />
		</TD>
		
		<TR bgcolor="orange">
		<TD style="color:green; padding-left: 50px; padding-top: 10px; " >
		Commission Type:
		</TD>
		<TD colspan=2 style="padding-top: 10px; padding-bottom: 15px;">
		<input type=text id="commsntype" style="width:70px;" name=commsntype <#if model['commsntype']??>value="${model.commsntype}"</#if> readonly />		
		</TD>						
		</TR>
		<TR bgcolor="orange">
		<TD style="color:green; padding-left: 50px; padding-top: 10px; " >
		Commission Amount:
		</TD>
		<TD colspan=2 style="padding-top: 10px; padding-bottom: 15px;">
		<input type=text id="commsnamt" style="width:70px;" name=commsnamt <#if model['commsnamt']??>value="${model.commsnamt}"</#if> readonly />		
		</TD>						
		</TR>
		<TR bgcolor="orange" >
		<TD colspan=2 align="center" style="padding-left: 50px; padding-top: 10px; padding-bottom: 15px;" >
		<input type=submit class="submitButton" value="Pay Now" />
		</TD>
		<TD colspan=2 align="center" style="padding-top: 10px; padding-bottom: 15px;" >
		<input type="button" class="submitButton" value="Pay Later" onClick="transactRedirect()"/>
		</TD>
		</TR>
	</table>
<input type=type id="transactionId" name=transactionId style="display:none;" <#if model['transactionId']??>value="${model.transactionId}</#if> "/>
</body>
