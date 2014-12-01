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
<@springform.form modelAttribute="model" name="form" method="POST" action="">
<#if model["trnlist"]??>
		<div style="width:676px;height:350px;overflow-x:hidden; overflow-y:auto; font-family: 'Lucida Grande'; font-size: 12pt; color: blue;">
			<table class="altrowstable" id="alternatecolor" >
						<#assign i = 1>
						<#list model["trnlist"] as tlist>
						<#if i%2 == 0>
						<tr  class="oddrowcolor">
						<td style="width:20px; padding:  0px;"><input name="checkbox[]"  type="checkbox" value="${tlist.getTxnIdx()}::${slist.getTxnCommsnType()}"></td>
						<td style="width:150px; padding: 0px;" class="commonText">${tlist.getTxnType()}</td>
						<td style="width:150px; padding: 0px;" class="commonText">${tlist.getTxnQty()}</td>
						<td style="width:150px; padding: 0px;" class="commonText">${tlist.getTxnCommsnType()}</td>
						<td style="width:150px; padding: 0px;" class="commonText">${tlist.getTxnCost()}</td>
						<td style="width:150px; padding: 0px;" class="commonText">${tlist.getTxnStatus()}</td>
						</tr>
						</#if>
						<#if i%2 != 0>
						<tr   class="evenrowcolor">
						<td style="width:20px; padding:  0px;"><input name="checkbox[]"  type="checkbox" value="${tlist.getTxnIdx()}::${slist.getTxnCommsnType()}"></td>
						<td style="width:150px; padding: 0px;" class="commonText">${tlist.getTxnType()}</td>
						<td style="width:150px; padding: 0px;" class="commonText">${tlist.getTxnQty()}</td>
						<td style="width:150px; padding: 0px;" class="commonText">${tlist.getTxnCommsnType()}</td>
						<td style="width:150px; padding: 0px;" class="commonText">${tlist.getTxnCost()}</td>
						<td style="width:150px; padding: 0px;" class="commonText">${tlist.getTxnStatus()}</td>
						</tr>
						</#if>
						<#assign i = i+1>	
						</#list>
				
			</table>
			
			<div align="left">
				<br/>
				<input type="hidden" id="approvecancel" name="approvecancel">
				<input class="submitButton" id="pay" name="pay" type="button"  value="Pay By Card" onclick="doPayCard()">
				<input class="submitButton" id="cancel" name="cancel" type="button"  value="Pay By Credit" onclick="doPayCredit()">
			</div>
				
>
		</div>
<#else>
<span id=Message> All transactions are either Approved or cancelled</span>
</#if>
</@springform.form> 
</body>