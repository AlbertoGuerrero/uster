<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Añadir conductor</title>
<spring:url value="/drivers/save" var="urlForm" />
<spring:url value="/resources" var="urlPublic" />
<link href="${urlPublic}/css/styles.css" rel="stylesheet" >
</head>
<body>
	<jsp:include page="../includes/head.jsp" />
	
	<h3>Datos del conductor</h3>

	<spring:hasBindErrors name="driver">
		<div class='alert alert-danger' role='alert'>
			Por favor corrija los siguientes errores:
			<ul>
				<c:forEach var="error" items="${errors.allErrors}">
					<li><spring:message message="${error}" /></li>
				</c:forEach>
			</ul>
		</div>
	</spring:hasBindErrors>

	<form:form action="${urlForm}" method="POST" modelAttribute="driver">
		<form:hidden path="id" />
		<label for="name">Nombre:</label>
		<form:input type="text" path="name" id="name"
			required="required" /> <br><br>
		<label for="surname">Apellido:</label>
		<form:input type="text" path="surname" id="surname"
			required="required" /> <br><br>
		<label for="license">Licencia:</label>
		<form:select id="license" path="license" class="form-control">
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