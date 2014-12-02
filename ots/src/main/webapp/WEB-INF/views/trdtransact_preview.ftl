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
	//alert("${model.clientId}");
	$("#clientId").val("${model.clientId}");	
});

function backFunc(){
	location.href = "clienthome.html";
}

	
</script>
</head>
<body >
	<span align="left" style="padding-left: 40px;">Place Order Preview</span><br/>
   <div align="center" style="color: orange;">
	 	
	<form name="myform" method="POST" action="traderplaceorder.html" onsubmit="return logincheck()" >
	<table align=LEFT cellspacing=0 cellpadding=6 style="BORDER:#E6e6e6 10px solid; font-family:'Helvetica,Arial,sans-serif'; font-size: 13pt; margin-top: 18px; margin-left: 40px; color: black;width: 450px;">
		<TR bgcolor=orange >	
		<TD style="padding-left: 50px; padding-top: 10px; " >Order Type</TD>
		<TD colspan=2> Quantity </TD>						
		</TR>
		
		<TR bgcolor=orange>
		<TD style="color:green; padding-left: 50px; padding-top: 10px; ">
		<input type=text id="trntype" style="width:70px;" name=trntype readonly <#if model['trntype']??>value="${model.trntype}"</#if>  />
		</TD>
		<TD style="color:green; padding-top: 10px; ">
		<input type=text id="trnqty" style="width:70px;" name=trnqty <#if model['trnQty']??>value="${model.trnQty}"<#else>"0"</#if> readonly />
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
		<#if model['commsntype'] = "CASH">
		Commission Amount:
		<#elseif model['commsntype'] = "OIL">
		Commission in Oil:
		</#if>
		</TD>
		<TD colspan=2 style="padding-top: 10px; padding-bottom: 15px;">
		<input type=text id="commsnamt" style="width:70px;" name=commsnamt <#if model['commsnamt']??>value="${model.commsnamt}"</#if> readonly />		
		</TD>						
		</TR>
		<TR bgcolor="orange">
		<TD style="color:green; padding-left: 50px; padding-top: 10px; " >
		Transaction Cost:
		</TD>
		<TD colspan=2 style="padding-top: 10px; padding-bottom: 15px;">
		<input type=text id="txncost" style="width:70px;" name=txncost <#if model['txnCost']??>value="${model.txnCost}"</#if> readonly />		
		</TD>						
		</TR>
		<TR bgcolor="orange" >
		<TD align="center" style="padding-top: 10px; padding-bottom: 15px;" >
		<input type=submit class="submitButton" value="Place Trade" />
		</TD>
		<TD colspan=2 align="center" style="padding-top: 10px; padding-bottom: 15px;" >
		<input type="button"  class="submitButton" onClick="backFunc()" value="Cancel"  /></td>
		</TR>
	</table>
	
	<input type="hidden" name="clientId" id="clientId" <#if model[clientId]??>value="${model.clientId}"</#if> >
	</form>	
	<#if model['errorMsg']??>
		<div id="errordiv" style="color:red;">${model.errorMsg}</div>
	</#if>

</body>
