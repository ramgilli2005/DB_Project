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
	 
	var a = document.myform.cardno.value;
	var b = document.myform.cardname.value;
	var c = document.myform.month.value;
	var d = document.myform.year.value;
	var e = document.myform.cvv.value;
	
	if(a==''||b==''||c==''||d==''||e==''){
		alert(a);
		alert(b);
		alert(c);
		alert(d);
		alert(e);
	
		alert("Please fill in card details");
		return false;
	}
	
	var f = document.myform.pwd.value;
	var g = document.myform.confirmpwd.value;
	
	if(f != g){
		alert("Passwords do not match");
		return false;
	}
	
	var emailvalid = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	if(!u.match(emailvalid))
	{
		alert("Enter a valid Email ID!");
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
		document.myform.getElementById("user_name").focus();
	}
<#else>

</#if>

}
$(document).ready(function(){
	$('#user_name').focus();	
});

function backFunc(){
	location.href = "login.html";
}
	
</script>
</head>
<body>
     		<center>Oil Transaction System</center>


   <div align="center">
 	
	<form name="myform" method="POST" action="signup.html" onsubmit="return signupcheck()" >
	
	
		
	<table width=470px align=center cellspacing=0 cellpadding=6 style="BORDER:#E6e6e6 1px solid; font-family:'Helvetica,Arial,sans-serif'; font-size: 11pt; color: black;width: 450px;">
	<TR height=38><TD colspan=3 align=center bgcolor="#636563" style="color:white;">
	<B>Enter Details</B></TD></TR>
	<TR height=15 bgcolor=#add8e6>
	<TD width=50% style="padding-left:103px; font-size: 9pt;">First Name:</td>
	<td width=65% style="BORDER-RIGHT:#E6E6E6 1px solid;">
	<input type=text name=fname id='fname' maxlength="20"></td>
	</tr>
	
	<TR height=15 bgcolor=#add8e6>
	<TD width=50% style="padding-left:103px; font-size: 9pt;">Last Name:</td>
	<td width=65% style="BORDER-RIGHT:#E6E6E6 1px solid;">
	<input type=text name=lname id='lname' maxlength="20"></td>
	</tr>
	
	<TR height=15 bgcolor=#add8e6>
	<TD width=50% style="padding-left:103px; font-size: 9pt;">Password:</td>
	<td width=65% style="BORDER-RIGHT:#E6E6E6 1px solid;">
	<input type=password name=pwd id='pwd' maxlength="20"></td>
	</tr>
	
	<TR height=15 bgcolor=#add8e6>
	<TD width=50% style="padding-left:103px; font-size: 9pt;">Confirm Password:</td>
	<td width=65% style="BORDER-RIGHT:#E6E6E6 1px solid;">
	<input type=password name=confirmpwd id='confirmpwd' maxlength="20"></td>
	</tr>
	
	<TR height=15 bgcolor=#add8e6>	
	<TD width=50% style="padding-left:103px; font-size: 9pt;">Social Security No:</td>
	<td width=65% style="BORDER-RIGHT:#E6E6E6 1px solid;">
	<input type=text name=ssn id='ssn' maxlength="9"></td>
	</tr>

	<TR height=15 bgcolor=#add8e6>
	<TD width=50% style="padding-left:103px; font-size: 9pt;">Address:</td>
	<td width=65% style="BORDER-RIGHT:#E6E6E6 1px solid;">
	<input type=text name=address id='address' maxlength="20"></td>
	</tr>
	
	<TR height=15 bgcolor=#add8e6>
	<TD width=50% style="padding-left:103px; font-size: 9pt;">City:</td>
	<td width=65% style="BORDER-RIGHT:#E6E6E6 1px solid;">
	<input type=text name=city id='city' maxlength="20">
	</td>
	</tr>
	
	<TR height=15 bgcolor=#add8e6>
	<TD width=50% style="padding-left:103px; font-size: 9pt;">State:</td>
	<td width=65% style="BORDER-RIGHT:#E6E6E6 1px solid;">
	<input type=text name=state id='state' maxlength="20">
	</td>
	</tr>
	
	<TR height=15 bgcolor=#add8e6>
	<TD width=50% style="padding-left:103px; font-size: 9pt;">ZIP:</td>
	<td width=65% style="BORDER-RIGHT:#E6E6E6 1px solid;">
	<input type=text name=zip id='zip' maxlength="6">
	</td>
	</tr>
	
	<TR height=15 bgcolor=#add8e6>
	<TD width=50% style="padding-left:103px; font-size: 9pt;">Phone no:</td>
	<td width=65% style="BORDER-RIGHT:#E6E6E6 1px solid;">
	<input type=text name=phno id='phno' maxlength="10"></td>
	</tr>
	
	<TR height=15 bgcolor=#add8e6>
	<TD width=50% style="padding-left:103px; font-size: 9pt;">Mobile No:</td>
	<td width=65% style="BORDER-RIGHT:#E6E6E6 1px solid;">
	<input type=text name=mobno id='mobno' maxlength="10"></td>
	</tr>
	
	<TR height=15 bgcolor=#add8e6>
	<TD width=50% style="padding-left:103px; font-size: 9pt;">Email:</td>
	<td width=65% style="BORDER-RIGHT:#E6E6E6 1px solid;">
	<input type=text name=email id='email' maxlength="20"></td>
	</tr>
	<tr bgcolor=red>
	<TD colspan=3 align="center" width=50% style="font-size: 12pt;">Card Details</td>
	</tr>
	<TR height=15 bgcolor=#add8e6>
	<TD width=50% style="padding-left:103px; font-size: 9pt;">Card Number</td>
	<td width=65% >
	<input type=text name=cardno id='cardno' maxlength="16"></td>
	</tr>
	
	<TR height=15 bgcolor=#add8e6>
	<TD width=50% style="padding-left:103px; font-size: 9pt;">Name On Card</td>
	<td width=65% >
	<input type=text name=cardname id='cardname' maxlength="20"></td>
	</tr>
	<TR height=15 bgcolor=#add8e6>
	<td></td>
	<td style="font-size: 9pt;">&nbsp;Month &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Year</td>
	
	</tr>
	<TR height=15 bgcolor=#add8e6>
	<TD width=50% style="padding-left:103px; padding-top: -5px; font-size: 9pt;">Expiry</td>
	<td width=5% >
	<input type=text name=month id='month' maxlength="2" style="width:50px;"> / <input type=text name=year id='year' maxlength="2" style="width:50px;"></td>
	</tr>
	
	<TR height=15 bgcolor=#add8e6>
	<TD width=50% style="padding-left:103px; font-size: 9pt;">CVV</td>
	<td width=65% >
	<input type=password name=cvv id='cvv' maxlength="3" style="width:50px;">
	</tr>
	
	<TR height=15 bgcolor=#add8e6>
	
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