<#import "/spring.ftl" as spring />
<#assign springform=JspTaglibs["/WEB-INF/tlds/spring-form.tld"]>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="../js/jquery-1.11.1.min.js"></script>
<title>WELCOME TO OTS</title>

<script>
	function doApprove() {
		if (!isAnyCBChecked()) {
			alert("Select any row to delete");
			return;
		} else {
			document.getElementById("approvecancel").value="approve";
			document.form.submit();
		}
	}
	function doCancel() {
		if (!isAnyCBChecked()) {
			alert("Select any row to delete");
			return;
		} else {
			document.getElementById("approvecancel").value="cancel";
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



</script>
</head>
<body>
<@springform.form modelAttribute="model" name="form" method="POST" action="trdpymntapproval.html">
<#if model["pymntlist"]??>
		<div style="BORDER:#E6e6e6 2px solid;width:676px;height:350px;overflow-x:hidden; overflow-y:auto; font-family: 'Lucida Grande'; font-size: 12pt; color: blue; margin-left: 30px; margin-top: 20px;">
			<table class="altrowstable" id="alternatecolor" border=1>
						<TR align="center" bgcolor="yellow" border=1 >
						<TH>Choose</TH>
						<TH>Payment Amt</TH>
						<TH align="center">Client Id</TH>
						<TH>Payment Date</TH>
						</TR>
						<#assign i = 1>
						<#list model["pymntlist"] as plist>
						<#if i%2 == 0>
						<tr  class="oddrowcolor">
						<td style="width:20px; padding:  0px;"><input name="checkbox[]"  type="checkbox" value="${plist.getPaymentId()}"></td>
						<td style="width:150px; padding: 0px;" class="commonText">${plist.getPaymentAmount()} </td>
						<td style="width:150px; padding: 0px;" class="commonText">${plist.getClientId()} </td>
						<td style="width:150px; padding: 0px;" class="commonText">${plist.getPaymentDate()} </td>
						</tr>
						</#if>
						<#if i%2 != 0>
						<tr   class="evenrowcolor">
						<td style="width:20px; padding:  0px;"><input name="checkbox[]"  type="checkbox" value="${plist.getPaymentId()}"></td>
						<td style="width:150px; padding: 0px;" class="commonText">${plist.getPaymentAmount()} </td>
						<td style="width:150px; padding: 0px;" class="commonText">${plist.getClientId()} </td>
						<td style="width:150px; padding: 0px;" class="commonText">${plist.getPaymentDate()} </td>
						</tr>
						</#if>
						<#assign i = i+1>	
						</#list>
				
			</table>
			
			
		</div>
		<div align="left" style="margin-left:40px;">
				<br/>
				<input type="hidden" id="approvecancel" name="approvecancel">
				<input class="submitButton" id="approve" name="approve" type="button"  value="Approve" onclick="doApprove()">
				
			</div>
<#else>
<span id=Message> All transactions are either Approved or cancelled</span>
</#if>
</@springform.form> 
</body>
