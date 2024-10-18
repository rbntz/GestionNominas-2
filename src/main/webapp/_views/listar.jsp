<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado Empleados</title>
</head>
<body>
	<h1>Listado Empleado</h1>

	<table border="1">
		<tr>
			<td>Nombre</td>
			<td>DNI</td>
			<td>Sexo</td>
			<td>Categoria</td>
			<td>Años trabajados</td>
		</tr>
		<c:forEach var="empleado" items="${lista}">
			<tr>
				<td>
					<a href="empresa?opcion=editar&dni=<c:out value="${empleado.getDNI()}"></c:out>">
						<c:out value="${empleado.getDNI()}"></c:out>
					</a>
				</td>
				<td><c:out value="${empleado.getNombre()}"></c:out></td>
				<td><c:out value="${empleado.getSexo()}"></c:out></td>
				<td><c:out value="${empleado.getCategoria()}"></c:out></td>
				<td><c:out value="${empleado.getAnyos()}"></c:out></td>
				<td>
					<a href="empresa?opcion=eliminar&dni=<c:out value="${empleado.getDNI()}"></c:out>">
						Eliminar 
					</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>