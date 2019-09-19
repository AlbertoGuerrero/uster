<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/" var="urlRoot" />

<style type="text/css">
#menu {
	text-align: center;
}

#menu li {
	display: inline;
	text-align: center;
}

#menu li a {
	font-family: Arial;
	font-size: 20px;
	text-decoration: none;
	padding: 10px;
	background-color: #2175bc;
	color: #fff;
}

#menu li a:hover {
	background-color:black;
	margin-top: -2;
	padding-bottom: 12px;
	]
}

#title {
	background-color:blue;
	color: #fff;
	text-align: center;
	font-size:30px;
}
</style>

<div id="title" ><h1>Reservas USTER</h1></div>

<div id="menu">
	<ul>
		<li><a href="${urlRoot}home">Principal</a></li>
		<li><a href="${urlRoot}vehicles/indexPaginate?page=0">Vehículos</a></li>
		<li><a href="${urlRoot}drivers/indexPaginate?page=0">Conductores</a></li>
		<li><a href="${urlRoot}trips/indexPaginate?page=0">Listado de viajes</a></li>
		<li><a href="${urlRoot}trips/addTrip">Gestionar viaje</a></li>
	</ul>
</div>



