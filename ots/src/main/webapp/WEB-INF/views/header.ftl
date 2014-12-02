<#import "/spring.ftl" as spring />
 <html>
 	<title>Oil Transaction System</title>
 	<head>
 	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>		
 	</head>
 	<script>
 	$(document).ready(function(){
 	<#if Session['clientId']??>

 	 <#else>
	location.href="login.html";
	</#if>
  	});	
 	</script>
 	<style>
 	#div2, #div3 {
 		top: -34px;
 		left: 85px;
 		height: 0px;
 	}
 	 	#div4{
 		top: -34px;
 		left: 185px;
 		height: 0px;
 	}
 	#div5{
 		top: -34px;
 		left: 285px;
 		height: 0px;
 	}
 	
 	</style>
 <body>
 <div id="headerdiv">
	<div class="container">
	<br/>
		 <div class="dropdown"id="div1" >
		 <#if Session['userType']??>
        <#if Session["userType"] = "customer">
		<button class="btn btn-default dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">Client
        <span class="caret"></span></button>
		
        <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
          <li role="presentation"><a role="menuitem" tabindex="-1" href="clienthome.html">Trades</a></li>
          <li role="presentation" class="divider"></li>
          <li role="presentation"><a role="menuitem" tabindex="-1" href="#">About Us</a></li>
        </ul>
		</div>
		 <div class="dropdown" id="div2">
        <button class="btn btn-default dropdown-toggle" type="button" id="menu2" data-toggle="dropdown">Transactions
        <span class="caret"></span></button>
		
        <ul class="dropdown-menu" role="menu" aria-labelledby="menu2">
          <li role="presentation"><a role="menuitem" tabindex="-1" href="clienttxn.html">Transactions</a></li>
          <li role="presentation"><a role="menuitem" tabindex="-1" href="clienttxnhist.html">Transaction History</a></li>
          <li role="presentation" class="divider"></li>
          <li role="presentation"><a role="menuitem" tabindex="-1" href="#">About Us</a></li>
        </ul>
        </div>
        </#if>
		</#if>
		<#if Session["Sys_Position"]??>
		<#if Session["Sys_Position"] = "trader">
		 <div class="dropdown">
		<button class="btn btn-default dropdown-toggle" type="button" id="menu3" data-toggle="dropdown">Trader
        <span class="caret"></span></button>
        <ul class="dropdown-menu" role="menu" aria-labelledby="menu3">
          <li role="presentation"><a role="menuitem" tabindex="-1" href="tamhome.html">Trader Approvals</a></li>
          <li role="presentation"><a role="menuitem" tabindex="-1" href="traderpendingcancel.html">View Pending Txns</a></li>
          
          <li role="presentation" class="divider"></li>
          <li role="presentation"><a role="menuitem" tabindex="-1" href="#">About Us</a></li>
        </ul>
        <div class="dropdown" id="div4">
        <button class="btn btn-default dropdown-toggle" type="button" id="menu2" data-toggle="dropdown">Transactions
        <span class="caret"></span></button>
		
        <ul class="dropdown-menu" role="menu" aria-labelledby="menu2">
          <li role="presentation"><a role="menuitem" tabindex="-1" href="trdtxnhist.html">Transaction History</a></li>
          <li role="presentation" class="divider"></li>
          <li role="presentation"><a role="menuitem" tabindex="-1" href="#">About Us</a></li>
        </ul>
        </div>
        <div class="dropdown" id="div3">
        <button class="btn btn-default dropdown-toggle" type="button" id="menu3" data-toggle="dropdown">Payment
        <span class="caret"></span></button>
		
        <ul class="dropdown-menu" role="menu" aria-labelledby="menu3">
          <li role="presentation"><a role="menuitem" tabindex="-1" href="trdpymntapproval.html">Payment Approval</a></li>
          <li role="presentation" class="divider"></li>
          <li role="presentation"><a role="menuitem" tabindex="-1" href="#">About Us</a></li>
        </ul>
        </div>
         <div class="dropdown" id="div5">
        <button class="btn btn-default dropdown-toggle" type="button" id="menu5" data-toggle="dropdown">Search
        <span class="caret"></span></button>
		
        <ul class="dropdown-menu" role="menu" aria-labelledby="menu5">
          <li role="presentation"><a role="menuitem" tabindex="-1" href="searchclnt.html">Search Client</a></li>
          <li role="presentation" class="divider"></li>
          <li role="presentation"><a role="menuitem" tabindex="-1" href="#">About Us</a></li>
        </ul>
        </div>
        </#if>
		<#if Session["Sys_Position"] = "manager">
		 <div class="dropdown">
		<button class="btn btn-default dropdown-toggle" type="button" id="menu3" data-toggle="dropdown">Manager
        <span class="caret"></span></button>
        <ul class="dropdown-menu" role="menu" aria-labelledby="menu3">
          <li role="presentation"><a role="menuitem" tabindex="-1" href="Mgrtxnhist.html">Transaction History</a></li>
          <li role="presentation"><a role="menuitem" tabindex="-1" href="Mgrtxnaggr.html">Aggregate</a></li>
          
          <li role="presentation" class="divider"></li>
          <li role="presentation"><a role="menuitem" tabindex="-1" href="#">About Us</a></li>
        </ul>
       </#if>
		<#if Session["Sys_Position"] = "admin">
		 <div class="dropdown">
		<button class="btn btn-default dropdown-toggle" type="button" id="menu3" data-toggle="dropdown">Admin
        <span class="caret"></span></button>
        <ul class="dropdown-menu" role="menu" aria-labelledby="menu3">
          <li role="presentation"><a role="menuitem" tabindex="-1" href="adminoil.html">View Oil Price</a></li>
          <li role="presentation"><a role="menuitem" tabindex="-1" href="adminupdoil.html">Update Oil Price</a></li>
          
          <li role="presentation" class="divider"></li>
          <li role="presentation"><a role="menuitem" tabindex="-1" href="#">About Us</a></li>
        </ul>
       </#if>
		</#if>
     
      		<div style="float:right; color:yellow; padding-left: 20px;">
		      <span id="welcome">Welcome ${uname}
			    </span>
			     <span id="logout" style="padding-left: 10px;"><a href="login.html">Logout</a></span>
 			</div>
 		
		</div>
		<br/>	    
	
    </div>
	
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	</div>
	</body> 
</html>