<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Calcular Sueldo Empleado</title>
</head>
<body>
	<h1>Escribe del DNI del empleado:</h1>
	<form action="empresa" method="post">
		<input type="hidden" name="opcion" value="calcularNomina">
		<table border="1">
			<tr>
				<td>DNI Empleado:</td>
				<td><input type="text" name="dni" size="50"></td>
			</tr>
		</table>
		<input type="submit" value="Buscar">
	</form>
</body>
</html>