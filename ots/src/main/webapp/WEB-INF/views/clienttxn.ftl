<#import "/spring.ftl" as spring />
<#assign springform=JspTaglibs["/WEB-INF/tlds/spring-form.tld"]>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="../js/jquery-1.11.1.min.js"></script>
<title>WELCOME TO OTS</title>

<script>
	function doPayCard() {
		if (!isAnyCBChecked()) {
			alert("Select an item to Pay!");
			return;
		} else if(!isMorePymnt()){ 
			alert("Please select only one transaction!");
			return;
		}
		else {
			document.getElementById("approvecancel").value="pay";
			document.form.submit();
		}
	}
	function doCancel(){
		if (!isAnyCBChecked()) {
			alert("Select an item to Pay!");
			return;
		} else{
		if(confirm('Are you sure to cancel the selected txns?')){
			document.getElementById("approvecancel").value="cancel";
			document.form.submit();
		}
		}
	}
	//Not used
	function doPayCredit() {
		if (!isAnyCBChecked()) {
			alert("Select any txn to cancel");
			return;
		} else if(!isMorePymnt()){ 
			alert("Please select only one transaction!");
			return;
		} else {
			document.getElementById("approvecancel").value="credit";
			document.form.submit();
		}
	}
	function isAnyCBChecked() {
		var isChecked = false;
		var cbs = document.form["checkbox[]"];
		if(cbs.length==undefined && cbs.checked) {
			return true;
		}			
		for ( var i = 0; i < cbs.length; i++) {
			if (cbs[i].checked == true) {
				isChecked = true;
				break;
			}
		}
		return isChecked;
	}
	function isMorePymnt(){
		var noChecked=0;
		
		var cbs = document.form["checkbox[]"];
		for ( var i = 0; i < cbs.length; i++) {
			if (cbs[i].checked == true) {
				noChecked=noChecked+1;
				if(noChecked>1){	
					return false;
				}
			}
		}
		return true;			
	}

</script>
</head>
<body>
<@springform.form modelAttribute="model" name="form" method="POST" action="txnapproval.html">
<#if model["txnlist"]??>
		<div style="BORDER:#E6e6e6 2px solid;width:676px;height:350px;overflow-x:hidden; overflow-y:auto; font-family: 'Lucida Grande'; font-size: 12pt; color: blue; margin-left: 30px; margin-top: 20px;">
			<table class="altrowstable" id="alternatecolor" border=1>
						<TR align="center" bgcolor="yellow" border=1 >
						<TH>Choose</TH>
						<TH>Transaction Type</TH>
						<TH align="center">Quantity</TH>
						<TH>Commission Type</TH>
						<TH>Txn Total Cost</TH>
						<TH>Txn Cost Paid</TH>
						</TR>
						<#assign i = 1>
						<#list model["txnlist"] as tlist>
						<#if i%2 == 0>
						<tr align="center" class="oddrowcolor">
						<td style="width:20px; padding:  0px;"><input name="checkbox[]"  type="checkbox" value="${tlist.getTxnId()}"></td>
						<td style="width:150px; padding: 0px;" class="commonText">${tlist.getType()}</td>
						<td style="width:150px; padding: 0px;" class="commonText">${tlist.getQuantity()}</td>
						<td style="width:150px; padding: 0px;" class="commonText">${tlist.getComsnType()}</td>
						<td style="width:150px; padding: 0px;" class="commonText">${tlist.getTxnCost()}</td>
						<td style="width:150px; padding: 0px;" class="commonText">${tlist.getTxnCostPaid()}</td>
						</tr>
						</#if>
						<#if i%2 != 0>
						<tr align="center" class="evenrowcolor">
						<td style="width:20px; padding:  0px;"><input name="checkbox[]"  type="checkbox" value="${tlist.getTxnId()}"></td>
						<td style="width:150px; padding: 0px;" class="commonText">${tlist.getType()}</td>
						<td style="width:150px; padding: 0px;" class="commonText">${tlist.getQuantity()}</td>
						<td style="width:150px; padding: 0px;" class="commonText">${tlist.getComsnType()}</td>
						<td style="width:150px; padding: 0px;" class="commonText">${tlist.getTxnCost()}</td>
						<td style="width:150px; padding: 0px;" class="commonText">${tlist.getTxnCostPaid()}</td>
						
						</tr>
						</#if>
						<#assign i = i+1>	
						</#list>
				
			</table>
			
			
				

		</div>
		<div align="left" style="margin-left: 30px;">
				<br/>
				<input type="hidden" id="approvecancel" name="approvecancel">
				<input class="submitButton" id="pay" name="pay" type="button"  value="Pay Now" onclick="doPayCard()">
				<input class="submitButton" id="cancel" name="cancel" type="button"  value="Cancel Txn" onclick="doCancel()">
			</div>
<#else>
<span id=Message style="color:red;"> All transactions are either Approved or cancelled !!!</span>
</#if>
</@springform.form> 
</body>
