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
<#if model["txnlist"]??>
		<div style="BORDER:#E6e6e6 2px solid;width:676px;height:350px;overflow-x:hidden; overflow-y:auto; font-family: 'Lucida Grande'; font-size: 12pt; color: blue; margin-left: 30px; margin-top: 20px;">
			<table class="altrowstable" id="alternatecolor" border=1>

<TR align="center" bgcolor="yellow" border=1 >
<TH>Txn Type</TH>
<TH>Quantity</TH>
<TH>Txn Date</TH>
<TH>Commission Type</TH>
<TH>Txn Commission Cost</TH>
<TH>Txn Cost</TH>
<TH>Txn Status</TH>
<TH>Txn Approver Id</TH>
</TR>
<#assign i = 1>
<#list model["txnlist"] as tlist>
<#if i%2 == 0>
<tr  class="oddrowcolor">
<td style="width:150px; padding: 0px;" class="commonText">${tlist.getTxnType()}</td>
<td style="width:150px; padding: 0px;" class="commonText">${tlist.getQuantity()}</td>
<td style="width:150px; padding: 0px;" class="commonText">${tlist.getTxnDate()}</td>
<td style="width:150px; padding: 0px;" class="commonText">${tlist.getComsnType()}</td>
<td style="width:150px; padding: 0px;" class="commonText">${tlist.getComsnCost()}</td>
<td style="width:150px; padding: 0px;" class="commonText">${tlist.getTxnCost()}</td>
<td style="width:150px; padding: 0px;" class="commonText">${tlist.getLogStatus()}</td>
<td style="width:150px; padding: 0px;" class="commonText">${tlist.getTraderId()}</td>
</tr>
</#if>
<#if i%2 != 0>
<tr   class="evenrowcolor">
<td style="width:150px; padding: 0px;" class="commonText">${tlist.getTxnType()}</td>
<td style="width:150px; padding: 0px;" class="commonText">${tlist.getQuantity()}</td>
<td style="width:150px; padding: 0px;" class="commonText">${tlist.getTxnDate()}</td>
<td style="width:150px; padding: 0px;" class="commonText">${tlist.getComsnType()}</td>
<td style="width:150px; padding: 0px;" class="commonText">${tlist.getComsnCost()}</td>
<td style="width:150px; padding: 0px;" class="commonText">${tlist.getTxnCost()}</td>
<td style="width:150px; padding: 0px;" class="commonText">${tlist.getLogStatus()}</td>
<td style="width:150px; padding: 0px;" class="commonText">${tlist.getTraderId()}</td>
</tr>
</#if>
<#assign i = i+1>	
</#list>
</table>
</div>
<#else>
<span id=Message> All transactions are either Approved or cancelled</span>
</#if>
</@springform.form> 
</body>