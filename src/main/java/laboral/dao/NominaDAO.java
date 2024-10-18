package laboral.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import laboral.conexion.Conexion;
import laboral.model.Empleado;
import laboral.model.Nomina;

public class NominaDAO {
	
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;

	// Método para guardar nómina en BBDD.
	public boolean guardar(Empleado empleado, Nomina nomina) throws SQLException {		
		String sql = null;
		
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			
			connection.setAutoCommit(false);
			sql = "INSERT INTO Nomina (empleado_dni, sueldoCalculado) VALUES (?, ?)";
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, nomina.getDniEmpleado());
			statement.setDouble(2, nomina.getSueldoCalculado());;

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

	// Método para eliminar nómina en BBDD.
	public boolean eliminar(String dniEmpleado) throws SQLException {		
		String sql = null;
		
		estadoOperacion = false;
		connection = obtenerConexion();
		
		try {
			
			connection.setAutoCommit(false);
			sql = "DELETE FROM nomina WHERE empleado_dni = '" + dniEmpleado + "'";
			
			statement = connection.prepareStatement(sql);

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

	// Método para obtener lista de nóminas.
	public List<Nomina> obtenerNominas() throws SQLException {
		ResultSet resultSet = null;
		List<Nomina> listaNominas = new ArrayList<>();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM Nomina";
			
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				Nomina nomina = new Nomina();
				
				nomina.setDniEmpleado(resultSet.getString(1));
				nomina.setSueldoCalculado(resultSet.getDouble(2));
				
				listaNominas.add(nomina);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaNominas;
	}

	// Método para obtener una nómina en concreto de la BBDD.
	public Nomina obtenerNomina(String dniEmpleado) throws SQLException {
		ResultSet resultSet = null;
		
		Nomina nomina = new Nomina();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM Nomina WHERE empleado_dni = '" + dniEmpleado + "'";
			
			statement = connection.prepareStatement(sql);

			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				nomina.setDniEmpleado(resultSet.getString(1));
				nomina.setSueldoCalculado(resultSet.getDouble(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nomina;
	}

	// Obtener conexión pool
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
	}

}