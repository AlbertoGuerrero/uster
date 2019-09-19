<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Gestionar un viaje</title>
<spring:url value="/resources" var="urlPublic" />
<spring:url value="/trips" var="urlTrips" />
<link href="${urlPublic}/css/styles.css" rel="stylesheet" >
</head>
<body>
	<jsp:include page="../includes/head.jsp" />
	<h3>2º PASO: Seleccione un vehículo</h3>    
	
	<table border="1">
		<thead>
			<tr>
				<th>Marca</th>
				<th>Modelo</th>
				<th>Matrícula</th>
				<th>Licencia Requerida</th>
				<th>Opción</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="vehicle" items="${vehicles}">
				<tr>
					<td>${vehicle.brand}</td>
					<td>${vehicle.model}</td>
					<td>${vehicle.plate}</td>
					<td>${vehicle.licenseRequired}</td>
					<td>
					<a class="button_add" href="${urlTrips}/select/${vehicle.id}/<fmt:formatDate pattern="dd-MM-yyyy" value="${date}" />" >Seleccionar</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<c:if test="${emptyList != null}">
		<div class="box">${emptyList}</div>
	</c:if>
	
	<jsp:include page="../includes/foot.jsp" />
</body>
</html>