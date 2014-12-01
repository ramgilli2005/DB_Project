<#import "/spring.ftl" as spring />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="../js/jquery-1.11.1.min.js"></script>
<title>WELCOME TO OTS</title>

<script>

function logincheck(){
	var x=document.myform.qty.value;
	if(x==''){
		alert("Please enter quantity!!!");
	 	return false;	
	}
}
$(document).ready(function(){

});

	
</script>
</head>
<body background-color="blue;">
	<span align="left" style="padding-left: 40px;">Place Trade for client</span><br/>
   <div align="center" style="color: orange;">
 	
	<form name="myform" method="POST" action="preview.html" onsubmit="return logincheck()" >
	<table align=LEFT cellspacing=0 cellpadding=6 style="BORDER:#E6e6e6 10px solid; font-family:'Helvetica,Arial,sans-serif'; font-size: 13pt; margin-top: 18px; margin-left: 40px; color: black;width: 450px;">
		<TR bgcolor=#add8e6 >
		<TH style="color:white;padding-left: 50px; padding-top: 10px; " >Client Id:</TH>
		<TH colspan=2>
		<select class="select"  name="clientid" id="username"  style="font-size:11px; font-family : Arial, Helvetica,sans-serif;">
     						<option style="max-width: 100px" value="">Select user</option>
								<#list model["clientIdList"] as user>
								{ 
								<option value="${user.clientId}">${user.fname},${user.lname}</option>									
			                    }<#if user_has_next>,</#if>
							   </#list>	
						</select>
		</TH>
		</TR>
		
	</table>
	</form>	

</body>
