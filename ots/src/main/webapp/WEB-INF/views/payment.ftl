<#import "/spring.ftl" as spring />
<#assign springform=JspTaglibs["/WEB-INF/tlds/spring-form.tld"]>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="../js/jquery-1.11.1.min.js"></script>
<title>WELCOME TO OTS</title>

<script>

</script>
</head>
<body>
<@springform.form modelAttribute="model" name="form" method="POST" action="txnpayment.html">
<div style="width:676px;height:350px;overflow-x:hidden; overflow-y:auto; font-family: 'Lucida Grande'; font-size: 12pt; color: blue;">
			<table class="altrowstable" id="alternatecolor" >
						
						<tr  class="oddrowcolor">
						<td style="width:20px; padding:  0px;"></td>
						</tr>
						
				
			</table>
			
			<div align="left">
				<br/>
				<input class="submitButton" id="delete" name="delete" type="submit"  value="Pay By Card" >
				<input class="submitButton" id="delete" name="delete" type="button"  value="Pay By Credit" onclick="creditPay()">
				
			</div>
		</div>
</@springform.form> 
</body>
