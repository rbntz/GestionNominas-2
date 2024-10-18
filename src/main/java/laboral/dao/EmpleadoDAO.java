package laboral.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import laboral.conexion.Conexion;
import laboral.model.*;

public class EmpleadoDAO {
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;

	// Método que guarda un empleado en BBDD.
	public boolean guardar(Empleado empleado) throws SQLException {

		String sql = null;

		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			connection.setAutoCommit(false);
			sql = "INSERT INTO Empleado (nombre, DNI, sexo, categoria, anyos) VALUES (?, ?, ?, ?, ?)";
			statement = connection.prepareStatement(sql);

			statement.setString(1, empleado.getNombre());
			statement.setString(2, empleado.getDNI());
			statement.setString(3, String.valueOf(empleado.getSexo()));
			statement.setInt(4, empleado.getCategoria());
			statement.setInt(5, empleado.getAnyos());

			estadoOperacion = statement.executeUpdate() > 0;
			
			connection.commit();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

		return estadoOperacion;
	}

	// Método que edita un empleado en BBDD.
	public boolean editar(Empleado empleado) throws SQLException {

		String sql = null;

		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			connection.setAutoCommit(false);
			sql = "UPDATE Empleado SET nombre=?, sexo=?, categoria=?, anyos=? WHERE dni=?";
			System.out.println("Hola");
			statement = connection.prepareStatement(sql);

			statement.setString(1, empleado.getNombre());
			statement.setString(2, String.valueOf(empleado.getSexo()));
			statement.setInt(3, empleado.getCategoria());
			statement.setInt(4, empleado.getAnyos());

			statement.setString(5, empleado.getDNI());

			estadoOperacion = statement.executeUpdate() > 0;
			
			connection.commit();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

		return estadoOperacion;
	}

	// Método que elimina un empleado en BBDD.
	public boolean eliminar(String dniEmpleado) throws SQLException {

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			connection.setAutoCommit(false);
			sql = "DELETE FROM Empleado WHERE dni=?";
			statement = connection.prepareStatement(sql);

			statement.setString(1, dniEmpleado);

			estadoOperacion = statement.executeUpdate() > 0;
			connection.commit();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

		return estadoOperacion;
	}

	// Método que lista todos los empleados de la base de datos.
	public List<Empleado> obtenerEmpleados() throws SQLException {
		ResultSet resultSet = null;
		List<Empleado> listaEmpleados = new ArrayList<>();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM Empleado";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				Empleado empleado = new Empleado();

				empleado.setNombre(resultSet.getString(1));
				empleado.setDNI(resultSet.getString(2));
				empleado.setSexo(resultSet.getString(3).charAt(0));
				empleado.setCategoria(resultSet.getInt(4));
				empleado.setAnyos(resultSet.getInt(5));

				listaEmpleados.add(empleado);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaEmpleados;
	}

	// Método para obtener un empleado en concreto de la BBDD.
	public Empleado obtenerEmpleado(String dniEmpleado) throws SQLException {
		ResultSet resultSet = null;
		Empleado empleado = new Empleado();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM Empleado WHERE DNI = '" + dniEmpleado + "'";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {

				empleado.setNombre(resultSet.getString(1));
				empleado.setDNI(resultSet.getString(2));
				empleado.setSexo(resultSet.getString(3).charAt(0));
				empleado.setCategoria(resultSet.getInt(4));
				empleado.setAnyos(resultSet.getInt(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return empleado;
	}

	// Obtener conexion pool
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
	}

}
