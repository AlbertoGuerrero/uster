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
	<h3>3º PASO: Seleccione un conductor</h3>    
	
	<table border="1">
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Apellido</th>
				<th>Licencia</th>
				<th>Opción</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="driver" items="${drivers}">
				<tr>
					<td>${driver.name}</td>
					<td>${driver.surname}</td>
					<td>${driver.license}</td>
					<td>
					<a class="button_add" href="${urlTrips}/select/${idVehicle}/<fmt:formatDate pattern="dd-MM-yyyy" value="${date}" />/${driver.id}" >Seleccionar</a>
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