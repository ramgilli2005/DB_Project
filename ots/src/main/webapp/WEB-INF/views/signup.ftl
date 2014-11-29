<#import "/spring.ftl" as spring />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="../js/jquery-1.11.1.min.js"></script>
<title>WELCOME TO OTS</title>

<script>

function signupcheck(){
	var x=document.myform.fname.value;
	var y=document.myform.lname.value;
	var z = document.myform.ssn.value;
	var w = document.myform.mobno.value;
	var u = document.myform.email.value;
	var p = document.myform.address.value;
	var q = document.myform.city.value;
	var r = document.myform.zip.value;
	if(x=='' || y=='' || z=='' || u==''||p==''||q==''||r==''||w=='')
	  {
		alert("Please fill in all required details");
	 	return false;
	  }
	  
	 
	}
	 
function myFunction()
{

	//var ed = document.myform.getElementById("Message");
	<#if model['errorMsg']??>
	var str='${model["errorMsg"]}';
	if (""!=str)
	{
		alert('${model["errorMsg"]}');
		//ed.style.display='block';	
		document.myform.getElementById("user_name").focus();
	}
<#else>
//ed.style.display='none';
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
 	
	<form name="myform" method="POST" action="signup.html" onsubmit="return signupcheck()" >
	
	
		
	<table width=470px align=center cellspacing=0 cellpadding=6 style="BORDER:#E6e6e6 1px solid; font-family:'Helvetica,Arial,sans-serif'; font-size: 11pt; color: black;width: 450px;">
	<TR height=38><TD colspan=3 align=center bgcolor="#636563" style="color:white;">
	<B>Enter Details</B></TD></TR>
	<TR height=15 bgcolor=#F4F7EC>
	<TD width=50% style="padding-left:103px; font-size: 9pt;">First Name:</td>
	<td width=65% style="BORDER-RIGHT:#E6E6E6 1px solid;">
	<input type=text name=fname id='fname' maxlength="20"></td>
	</tr>
	
	<TR height=15 bgcolor=#F4F7EC>
	<TD width=50% style="padding-left:103px; font-size: 9pt;">Last Name:</td>
	<td width=65% style="BORDER-RIGHT:#E6E6E6 1px solid;">
	<input type=text name=lname id='lname' maxlength="20"></td>
	</tr>

	<TR height=15 bgcolor=#F4F7EC>	
	<TD width=50% style="padding-left:103px; font-size: 9pt;">Social Security No:</td>
	<td width=65% style="BORDER-RIGHT:#E6E6E6 1px solid;">
	<input type=text name=ssn id='ssn' maxlength="20"></td>
	</tr>

	<TR height=15 bgcolor=#F4F7EC>
	<TD width=50% style="padding-left:103px; font-size: 9pt;">Address:</td>
	<td width=65% style="BORDER-RIGHT:#E6E6E6 1px solid;">
	<input type=text name=address id='address' maxlength="20"></td>
	</tr>
	
	<TR height=15 bgcolor=#F4F7EC>
	<TD width=50% style="padding-left:103px; font-size: 9pt;">City:</td>
	<td width=65% style="BORDER-RIGHT:#E6E6E6 1px solid;">
	<input type=text name=city id='city' maxlength="20">
	</td>
	</tr>
	
	<TR height=15 bgcolor=#F4F7EC>
	<TD width=50% style="padding-left:103px; font-size: 9pt;">State:</td>
	<td width=65% style="BORDER-RIGHT:#E6E6E6 1px solid;">
	<input type=text name=state id='state' maxlength="20">
	</td>
	</tr>
	
	<TR height=15 bgcolor=#F4F7EC>
	<TD width=50% style="padding-left:103px; font-size: 9pt;">ZIP:</td>
	<td width=65% style="BORDER-RIGHT:#E6E6E6 1px solid;">
	<input type=text name=zip id='zip' maxlength="20">
	</td>
	</tr>
	
	<TR height=15 bgcolor=#F4F7EC>
	<TD width=50% style="padding-left:103px; font-size: 9pt;">Phone no:</td>
	<td width=65% style="BORDER-RIGHT:#E6E6E6 1px solid;">
	<input type=text name=phno id='phno' maxlength="20"></td>
	</tr>
	
	<TR height=15 bgcolor=#F4F7EC>
	<TD width=50% style="padding-left:103px; font-size: 9pt;">Mobile No:</td>
	<td width=65% style="BORDER-RIGHT:#E6E6E6 1px solid;">
	<input type=text name=mobno id='mobno' maxlength="20"></td>
	</tr>
	
	<TR height=15 bgcolor=#F4F7EC>
	<TD width=50% style="padding-left:103px; font-size: 9pt;">Email:</td>
	<td width=65% style="BORDER-RIGHT:#E6E6E6 1px solid;">
	<input type=text name=email id='email' maxlength="20"></td>
	</tr>
	
	<TR height=15 bgcolor=#F4F7EC>
	
	<td style="BORDER-RIGHT:#fff 1px solid;padding-left: 180px; padding-top: 40px;  margin-left:30px;">
		</td><TD width=50%><input type="submit"  class="submitButton" value="Register"  />
		&nbsp;&nbsp;&nbsp;
		<input type="button"  class="submitButton" onClick="backFunc()" value="Cancel"  /></td>
		</tr>
	</tr>
	</table>
		
  	<span id="Message" style="color:red;"></span>
  
	   <script>
	   myFunction();
	   </script>
	  
	</form>

</div>

</body>
</html>