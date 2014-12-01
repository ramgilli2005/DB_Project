<#import "/spring.ftl" as spring />
<#assign springform=JspTaglibs["/WEB-INF/tlds/spring-form.tld"]>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="../js/jquery-1.11.1.min.js"></script>
<title>WELCOME TO OTS</title>

<script>
function doPayCard() {
		if(confirm('Are you sure you want to pay by Card?')){
			var a=document.form.txncost.value;
			var b=document.form.txncostpd.value;
			var x=document.form.pymntamt.value;
			if((b+x) > a){
				alert("Overpaying is not allowed right now!");
				return;
			}		
			document.getElementById("creditcard").value="card";
			document.form.submit();
		}
	}
	function doPayCredit() {
		if(confirm('Are you sure you want to pay from Credit?')){
			var a=document.form.txncost.value;
			var b=document.form.txncostpd.value;
			var x=document.form.pymntamt.value;
			var y=document.form.creditAmt.value;
			if(x > y){
				alert("Payment cannot be higher than credit!!!");
				return;
			} else {
				if((b+y) > a){
					alert("Overpaying is not allowed right now!");
					return;
				}
				document.getElementById("creditcard").value="credit";
				document.form.submit();
			}
			
			
		}
		
	}
function transactRedirect(){
	location.href="clienthome.html";
}
</script>
</head>
<body>
<@springform.form modelAttribute="model" name="form" method="POST" action="txnpayment.html">
<div style="width:676px;height:350px;overflow-x:hidden; overflow-y:auto; font-family: 'Lucida Grande'; font-size: 12pt; color: blue;">
	<table align=LEFT cellspacing=0 cellpadding=6 style="BORDER:#E6e6e6 10px solid; font-family:'Helvetica,Arial,sans-serif'; font-size: 13pt; margin-top: 18px; margin-left: 40px; color: black;width: 450px;">
						
		<TR bgcolor="orange">
		<TD style="color:green; padding-left: 50px; padding-top: 10px; " >
		Transaction Cost:
		</TD>
		<TD colspan=2 style="padding-top: 10px; padding-bottom: 15px;">
		<input type=text id="txncost" style="width:70px;" name=txncost <#if model['txnCost']??>value="${model.txnCost}"</#if> readonly />		
		</TD>						
		</TR>
		<TR bgcolor="orange">
		<TD style="color:green; padding-left: 50px; padding-top: 10px; " >
		Transaction Cost Paid Till Now:
		</TD>
		<TD colspan=2 style="padding-top: 10px; padding-bottom: 15px;">
		<input type=text id="txncostpd" style="width:70px;" name=txncostpd <#if model['costPaid']??>value="${model.costPaid}"</#if> readonly />		
		</TD>						
		</TR>
		<TR bgcolor="orange">
		<TD style="color:green; padding-left: 50px; padding-top: 10px; " >
		Your Current Credit:
		</TD>
		<TD colspan=2 style="padding-top: 10px; padding-bottom: 15px;">
		<input type=text id="creditAmt" style="width:70px;" name=creditAmt <#if model['txnCost']??>value="${model.creditAmt}"</#if> readonly />		
		</TD>						
		</TR>
		<TR bgcolor="orange">
		<TD style="color:green; padding-left: 50px; padding-top: 10px; " >
		Payment Amount:
		</TD>
		<TD colspan=2 style="padding-top: 10px; padding-bottom: 15px;">
		<input type=text id="pymntamt" style="width:70px;" name=pymntamt />		
		</TD>						
		</TR>
		
		<TR bgcolor="orange" >
		<TD colspan=2 align="center" style="padding-left: 50px; padding-top: 10px; padding-bottom: 15px;" >
		<input class="submitButton" id="delete" name="delete" type="button"  value="Pay By Card" onClick="doPayCard()" >
		<input class="submitButton" id="delete" name="delete" type="button"  value="Pay By Credit" onClick="doPayCredit()" >
		
		</TD>
		<TD colspan=2 align="center" style="padding-top: 10px; padding-bottom: 15px;" >
		<input type="button" class="submitButton" value="Pay Later" onClick="transactRedirect()"/>
		</TD>
		</TR>					
				
	</table>
	<input type="hidden" id="creditcard" name="creditcard">
	<input type="hidden" id="transactionId" name="transactionId" <#if model['txnId']??> value="${model.txnId}" </#if> >			
		</div>
</@springform.form> 
</body>
