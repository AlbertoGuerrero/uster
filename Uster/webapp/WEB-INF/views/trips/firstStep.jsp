<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Gestionar un viaje</title>
<spring:url value="/resources" var="urlPublic" />
<spring:url value="/trips/secondStep" var="urlForm" />
<link href="${urlPublic}/css/styles.css" rel="stylesheet" >
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>
<body>
	<jsp:include page="../includes/head.jsp" />
	<h3>1º PASO: Seleccione una fecha</h3>    
	<form:form action="${urlForm}" method="POST" modelAttribute="trip">
		<form:input type="text" path="date" id="date" required="required"  />
		<br><br>
		<button class="button_add" type="submit" >Siguiente</button>  
	</form:form>      
	<jsp:include page="../includes/foot.jsp" />
	
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script>
	$(function() {
		$("#date").datepicker({
			dateFormat : 'dd-mm-yy'
		});
	});
</script>
	
</body>
</html>

