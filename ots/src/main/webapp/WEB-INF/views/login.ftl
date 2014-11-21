<#assign springform=JspTaglibs["http://www.springframework.org/tags/form"]>
<#import "/spring.ftl" as spring />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="../js/jquery-1.11.1.min.js"></script>
<title>WELCOME TO OTS</title>

<script>

function logincheck(){
	var x=document.myform.username.value;
	var y=document.myform.password.value;
	if(x=='' && y=='')
	  {
	alert("Enter User Name and Password");
	 return false;

	  }
	  else if(x=='' || y =='')
	  {
	alert("Enter Both User Name and Password");
	 return false;
	  }
	 
	}
	 
function myFunction()
{
var x=document.getElementById("Message").innerHTML;
<#if model['errorMsg']??>
var str='${model["errorMsg"]}';
if (""!=str)
	{
//alert('${model["errorMsg"]}'+x);
$("errordiv").html(str);
$("#user_name").focus();
	}
	</#if>
}
$(document).ready(function(){
	$('#user_name').focus();
});

	
</script>
</head>
<body>
     		<center>Oil Transaction System</center>


   <div align="center">
 
	<form name="myform" method="POST" action="login.html" onsubmit="return logincheck()" >
	<div id="errordiv" style="color:red;"></div>
	<table width=470px align=center cellspacing=0 cellpadding=6 style="BORDER:#E6e6e6 1px solid; font-family:'Helvetica,Arial,sans-serif'; font-size: 11pt; margin-top: 128px; color: black;width: 450px;">
	<TR height=38><TD colspan=3 align=center bgcolor="#636563" style="color:white;">
	<B>Please Login</B></TD></TR>
	<TR height=26 bgcolor=#F4F7EC>
	<TD width=30% style="padding-left:103px; font-size: 9pt; padding-top:30px;">User Name:</td>
	<td width=65% style="BORDER-RIGHT:#E6E6E6 1px solid; padding-top:30px;">
	<input type=text name=username id='user_name' maxlength="6"></td>
	</tr>
	<TR height=26 bgcolor=#F4F7EC>
	<TD width=30% style="padding-left:110px; font-size: 9pt; padding-top:30px; padding-bottom: 20px;">Password:</td>
	<td style="BORDER-RIGHT:#E6E6E6 1px solid;">
	<input type=password name=password  maxlength="15"></td>
	</tr>
	<TR height=26 bgcolor=#F4F7EC>

	<td style="BORDER-RIGHT:#fff 1px solid;padding-left: 180px; padding-top: 40px;  margin-left:30px;">
		</td><TD width=30%><input type="submit"  class="submitButton" value="login"  /></td>
		</tr>
	</table>	
  	<span id="Message"></span>
  
	   <script>
	   myFunction();
	   </script>
	  		  	   
	  
	</form>

</div>
</div>
</body>