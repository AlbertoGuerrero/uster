<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Listado de viajes</title>
<spring:url value="/trips" var="urlTrips" />
<spring:url value="/resources" var="urlPublic" />
<link href="${urlPublic}/css/styles.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../includes/head.jsp" />

	<h3>Listado de viajes</h3>

	<c:if test="${message !=null}">
		<div class="box">${message}</div>
	</c:if>

	<table border="1">
		<thead>
			<tr>
				<th>Marca</th>
				<th>Modelo</th>
				<th>Matrícula</th>
				<th>Licencia Requerida</th>
				<th>Nombre</th>
				<th>Apellido</th>
				<th>Licencia</th>
				<th>Fecha Viaje</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="trip" items="${trips.content}">
				<tr>
					<td>${trip.vehicle.brand}</td>
					<td>${trip.vehicle.model}</td>
					<td>${trip.vehicle.plate}</td>
					<td>${trip.vehicle.licenseRequired}</td>
					<td>${trip.driver.name}</td>
					<td>${trip.driver.surname}</td>
					<td>${trip.driver.license}</td>
					<td><fmt:formatDate pattern="dd-MM-yyyy" value="${trip.date}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<c:if test="${emptyList != null}">
		<div class="box">${emptyList}</div>
	</c:if>
	
	<div class="button_page" >
		<a class="button_add" href="${urlTrips}/indexPaginate?page=${trips.number - 1}">Anterior</a>
		<a class="button_add" href="${urlTrips}/indexPaginate?page=${trips.number + 1}">Siguiente</a>
	</div>
	
	<jsp:include page="../includes/foot.jsp" />
</body>
</html>