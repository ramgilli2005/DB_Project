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
 <body>
 <div id="headerdiv">
	<div class="container">
	<br/>
		 <div class="dropdown">
        <button class="btn btn-default dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">Tutorials
        <span class="caret"></span></button>
        <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
          <li role="presentation"><a role="menuitem" tabindex="-1" href="#">HTML</a></li>
          <li role="presentation"><a role="menuitem" tabindex="-1" href="#">CSS</a></li>
          <li role="presentation"><a role="menuitem" tabindex="-1" href="#">JavaScript</a></li>
          <li role="presentation" class="divider"></li>
          <li role="presentation"><a role="menuitem" tabindex="-1" href="#">About Us</a></li>
        </ul>
     
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