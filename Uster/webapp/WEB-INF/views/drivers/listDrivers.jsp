<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Listado de conductores</title>
<spring:url value="/drivers" var="urlDrivers" />
<spring:url value="/resources" var="urlPublic" />
<link href="${urlPublic}/css/styles.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../includes/head.jsp" />

	<h3>Listado de conductores</h3>

	<c:if test="${message !=null}">
		<div class="box">${message}</div>
	</c:if>

	<a class="button_add" href="${urlDrivers}/create">Añadir conductor</a>

	<br>
	<br>

	<table border="1">
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Apellido</th>
				<th>Licencia</th>
				<th>Opciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="driver" items="${drivers.content}">
				<tr>
					<td>${driver.name}</td>
					<td>${driver.surname}</td>
					<td>${driver.license}</td>
					<td><a class="button_edit"
						href="${urlDrivers}/edit/${driver.id}">Editar</a> <a
						class="button_delete" href="${urlDrivers}/delete/${driver.id}"
						onclick='return confirm("¿Estás seguro?")'>Eliminar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<c:if test="${emptyList != null}">
		<div class="box">${emptyList}</div>
	</c:if>
	
	<div class="button_page" >
		<a class="button_add" href="${urlDrivers}/indexPaginate?page=${drivers.number - 1}">Anterior</a>
		<a class="button_add" href="${urlDrivers}/indexPaginate?page=${drivers.number + 1}">Siguiente</a>
	</div>
	
	<jsp:include page="../includes/foot.jsp" />
</body>
</html>