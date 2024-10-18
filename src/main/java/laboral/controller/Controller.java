package laboral.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import laboral.dao.*;
import laboral.model.*;

/**
 * Servlet implementation class Controller
 */
@WebServlet(description = "Administra peticiones a la tabla Empleado/Nomina", urlPatterns = { "/empresa" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String opcion = request.getParameter("opcion");

		if (opcion == null) {
			// Si "opcion" es null, redirigir a una página predeterminada o mostrar un
			// mensaje de error.
			response.sendRedirect("index.jsp");
			return;
		}

		if (opcion.equals("crear")) {

			System.out.println("Usted a presionado la opcion crear");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/_views/crear.jsp");

			requestDispatcher.forward(request, response);

		} else if (opcion.equals("listar")) {

			EmpleadoDAO empleadoDAO = new EmpleadoDAO();
			List<Empleado> lista = new ArrayList<>();

			try {
				lista = empleadoDAO.obtenerEmpleados();

				for (Empleado empleado : lista) {
					System.out.println(empleado);
				}

				request.setAttribute("lista", lista);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/_views/listar.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Usted a presionado la opcion listar");

		} else if (opcion.equals("editar")) {

			String dniEmpleado = request.getParameter("dni");
			System.out.println("Editar: " + dniEmpleado);

			EmpleadoDAO empleadoDAO = new EmpleadoDAO();
			Empleado empleado = new Empleado();

			try {
				empleado = empleadoDAO.obtenerEmpleado(dniEmpleado);
				System.out.println(empleado);
				request.setAttribute("empleado", empleado);

				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/_views/editar.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (opcion.equals("eliminar")) {

			EmpleadoDAO empleadoDAO = new EmpleadoDAO();
			String dniEmpleado = request.getParameter("dni");

			try {
				empleadoDAO.eliminar(dniEmpleado);
				System.out.println("Registro eliminado satisfactoriamente...");

				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (opcion.equals("buscarEmpleado")) {
			
			System.out.println("Usted a presionado la opcion buscarEmpleado");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/_views/buscarEmpleado.jsp");

			requestDispatcher.forward(request, response);
		}
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String opcion = request.getParameter("opcion");

	    if (opcion.equals("guardar")) {
	        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
	        Empleado empleado = new Empleado();
	        
	        Nomina nomina = new Nomina();
	        NominaDAO nominaDAO = new NominaDAO();

	        empleado.setNombre(request.getParameter("nombre"));
	        
	        empleado.setDNI(request.getParameter("dni"));
	        nomina.setDniEmpleado(request.getParameter("dni"));
	        
	        String sexoString = request.getParameter("sexo");
	        char sexo = sexoString != null && !sexoString.isEmpty() ? sexoString.charAt(0) : ' ';
	        empleado.setSexo(sexo);

	        String categoriaString = request.getParameter("categoria");
	        String anyosString = request.getParameter("anyos");
	        int categoria = (categoriaString != null && !categoriaString.isEmpty()) ? Integer.parseInt(categoriaString) : 0;
	        int anyos = (anyosString != null && !anyosString.isEmpty()) ? Integer.parseInt(anyosString) : 0;
	        empleado.setCategoria(categoria);
	        empleado.setAnyos(anyos);
	        
	        nomina.sueldo(empleado);
	               
	        try {
	            empleadoDAO.guardar(empleado);
	            nominaDAO.guardar(empleado, nomina);
	            
	            System.out.println("Registro guardado satisfactoriamente...");
	            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
	            requestDispatcher.forward(request, response);

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	    } else if (opcion.equals("editar")) {
	        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
	        Empleado empleado = new Empleado();

	        empleado.setNombre(request.getParameter("nombre"));
	        empleado.setDNI(request.getParameter("dni"));
	        String sexoString = request.getParameter("sexo");
	        char sexo = sexoString != null && !sexoString.isEmpty() ? sexoString.charAt(0) : ' ';
	        empleado.setSexo(sexo);

	        String categoriaString = request.getParameter("categoria");
	        String anyosString = request.getParameter("anyos");

	        int categoria = (categoriaString != null && !categoriaString.isEmpty()) ? Integer.parseInt(categoriaString) : 0;
	        int anyos = (anyosString != null && !anyosString.isEmpty()) ? Integer.parseInt(anyosString) : 0;

	        empleado.setCategoria(categoria);
	        empleado.setAnyos(anyos);

	        try {
	            empleadoDAO.editar(empleado);
	            System.out.println("Registro editado satisfactoriamente...");
	            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
	            requestDispatcher.forward(request, response);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	    } else if (opcion.equals("calcularNomina")) {
	    	NominaDAO nominaDAO = new NominaDAO();
	    	Nomina nomina = new Nomina();
	    	
	    	String dniEmpleado = request.getParameter("dni");
	    	
	    	try {
	    		nomina = nominaDAO.obtenerNomina(dniEmpleado);
	    		request.setAttribute("nomina", nomina);
	    		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/calcularNomina.jsp");
	            requestDispatcher.forward(request, response);
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	}
	    	
	    }
	}

		// doGet(request, response);

}