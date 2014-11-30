<#import "/spring.ftl" as spring />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="../js/jquery-1.11.1.min.js"></script>
<title>WELCOME TO OTS</title>

<script>

</script>
</head>
<body>
<@springform.form modelAttribute="model"   name="form" method="POST" action="payment.html">
<div style="width:676px;height:350px;overflow-x:hidden; overflow-y:auto; font-family: 'Lucida Grande'; font-size: 12pt; color: blue;">
			<table class="altrowstable" id="alternatecolor" >
						<#assign i = 1>
						<#list model["trnpymntlist"] as tplist>
						<#if i%2 == 0>
						<tr  class="oddrowcolor">
						<td style="width:20px; padding:  0px;"><input name="checkbox[]"  type="checkbox" value="${tplist}"></td>
						</tr>
						</#if>
						<#if i%2 != 0>
						<tr   class="evenrowcolor">
						<td style="width:20px; padding:  0px;"><input name="checkbox[]"  type="checkbox" value="${tplist}"></td>
						</tr>
						</#if>
						<#assign i = i+1>	
						</#list>
				
			</table>
			
			<div align="left">
				<br/>
				<input class="submitButton" id="delete" name="delete" type="button"  value="Delete" onclick="doDelete()">
				
			</div>
		</div>
</@springform.form> 
</body>
