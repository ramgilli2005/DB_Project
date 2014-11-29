<!DOCTYPE html>
<#include "header.ftl"/>
 <link rel="stylesheet" href="../css/main.css">
<html lang="en">
<title>Oil Transaction System</title>
<hr/>
<body>
<div id="mainpage">
	<#if model['Page']??>
		<#include "${model['Page']}.ftl"/>
	</#if>
</div>
	
<div id="footerdiv">
<#include "footer.ftl"/>
</div>
<#include "styles.ftl"/>
</body>
</html>