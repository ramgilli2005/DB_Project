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
<#if model["aggrlist"]??>
		<div style="BORDER:#E6e6e6 2px solid;width:676px;height:350px;overflow-x:hidden; overflow-y:auto; font-family: 'Lucida Grande'; font-size: 12pt; color: blue; margin-left: 30px; margin-top: 20px;">
			<table class="altrowstable" id="alternatecolor" border=1>
						<TR align="center" bgcolor="yellow" border=1 >
						<TH>Start Date</TH>
						<TH align="center">Aggregate Txn Cost</TH>
						<TH>Aggregate Txn Count</TH>
						</TR>
						<#assign i = 1>
						<#list model["aggrlist"] as tlist>
						<#if i%2 == 0>
						<tr align="center" class="oddrowcolor">
						
						<td style="width:150px; padding: 0px;" class="commonText">${tlist.getStartDate()}</td>
						<td style="width:150px; padding: 0px;" class="commonText">${tlist.getAggregateValue()}</td>
						<td style="width:150px; padding: 0px;" class="commonText">${tlist.getCount()}</td>
						</tr>
						</#if>
						<#if i%2 != 0>
						<tr align="center" class="evenrowcolor">
						<td style="width:150px; padding: 0px;" class="commonText">${tlist.getStartDate()}</td>
						<td style="width:150px; padding: 0px;" class="commonText">${tlist.getAggregateValue()}</td>
						<td style="width:150px; padding: 0px;" class="commonText">${tlist.getCount()}</td>
						
						</tr>
						</#if>
						<#assign i = i+1>	
						</#list>
				
			</table>
		</div>
<#else>
<span id=Message style="color:red;"> No Transactions available !!!</span>
</#if>
</@springform.form> 
</body>
