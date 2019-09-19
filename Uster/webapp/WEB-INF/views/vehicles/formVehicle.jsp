<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Añadir vehículo</title>
<spring:url value="/vehicles/save" var="urlForm" />
<spring:url value="/resources" var="urlPublic" />
<link href="${urlPublic}/css/styles.css" rel="stylesheet" >
</head>
<body>
	<jsp:include page="../includes/head.jsp" />
	
	<h3>Datos del vehículo</h3>

	<spring:hasBindErrors name="vehicle">
		<div class='alert alert-danger' role='alert'>
			Por favor corrija los siguientes errores:
			<ul>
				<c:forEach var="error" items="${errors.allErrors}">
					<li><spring:message message="${error}" /></li>
				</c:forEach>
			</ul>
		</div>
	</spring:hasBindErrors>

	<form:form action="${urlForm}" method="POST" modelAttribute="vehicle">
		<form:hidden path="id" />
		<label for="brand">Marca:</label>
		<form:input type="text" path="brand" id="brand"
			required="required" /> <br><br>
		<label for="model">Modelo:</label>
		<form:input type="text" path="model" id="model"
			required="required" /> <br><br>
		<label for="plate">Matrícula:</label>
		<form:input type="text" path="plate" id="plate"
			required="required" /> <br><br>
		<label for="licenseRequired">Licencia Requerida:</label>
		<form:select id="licenseRequired" path="licenseRequired" class="form-control">
        	<form:option value="A">A</form:option>
            <form:option value="B">B</form:option>  
            <form:option value="C">C</form:option> 
            <form:option value="D">D</form:option> 
            <form:option value="E">E</form:option>                                
        </form:select>   <br><br>
		<button class="button_add" type="submit" >Guardar</button>
	</form:form>
	<jsp:include page="../includes/foot.jsp" />
</body>
</html>