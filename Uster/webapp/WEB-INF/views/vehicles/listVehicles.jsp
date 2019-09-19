<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Listado de Vehículos</title>
<spring:url value="/vehicles" var="urlVehicles" />
<spring:url value="/resources" var="urlPublic" />
<link href="${urlPublic}/css/styles.css" rel="stylesheet" >
</head>
<body>
	<jsp:include page="../includes/head.jsp" />

	<h3>Listado de Vehículos</h3>
	
	<c:if test="${message !=null}">
		<div class="box">${message}</div>
	</c:if>

	<a  class="button_add" href="${urlVehicles}/create">Añadir vehículo</a>

	<br>
	<br>

	<table border="1">
		<thead>
			<tr>
				<th>Marca</th>
				<th>Modelo</th>
				<th>Matrícula</th>
				<th>Licencia Requerida</th>
				<th>Opciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="vehicle" items="${vehicles.content}">
				<tr>
					<td>${vehicle.brand}</td>
					<td>${vehicle.model}</td>
					<td>${vehicle.plate}</td>
					<td>${vehicle.licenseRequired}</td>
					<td>
					<a class="button_edit" href="${urlVehicles}/edit/${vehicle.id}" >Editar</a>
					<a class="button_delete" href="${urlVehicles}/delete/${vehicle.id}" onclick='return confirm("¿Estás seguro?")'>Eliminar</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<c:if test="${emptyList != null}">
		<div class="box">${emptyList}</div>
	</c:if>
	
	<div class="button_page" >
		<a class="button_add" href="${urlVehicles}/indexPaginate?page=${vehicles.number - 1}">Anterior</a>
		<a class="button_add" href="${urlVehicles}/indexPaginate?page=${vehicles.number + 1}">Siguiente</a>
	</div>
	
	<jsp:include page="../includes/foot.jsp" />
</body>
</html>