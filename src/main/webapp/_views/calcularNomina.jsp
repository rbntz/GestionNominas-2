<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nómina Empleado</title>
</head>
<body>
	<h1>Nómina Empleado</h1>

	<table border="1">
		<tr>
			<td>DNI</td>
			<td>Sueldo</td>
		</tr>
		<c:forEach var="nomina" items="${nomina}">
			<tr>
				<td><c:out value="${nomina.getDniEmpleado()}"></c:out></td>
				<td><c:out value="${nomina.getSueldoCalculado()}"></c:out></td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>