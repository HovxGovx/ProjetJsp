package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddStudentServlet
 */
@WebServlet(name = "AddStudent", urlPatterns = { "/AddStudent" })
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/etudiant";
            String user = "postgres";
            String password = "mirahona1.";

            Connection connection1 = DriverManager.getConnection(url, user, password);


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Forward the request to an error page
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
		// Retrieve parameters from the form
        String numEt = request.getParameter("numEt");
        String nom = request.getParameter("nom");
        double moyenne = Double.parseDouble(request.getParameter("moyenne"));
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            String url = "jdbc:postgresql://localhost:5432/etudiant";
            String user = "postgres";
            String password = "mirahona1.";

            connection = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO etudiant (numEt, nom, moyenne) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, numEt);
            preparedStatement.setString(2, nom);
            preparedStatement.setDouble(3, moyenne);

            preparedStatement.executeUpdate();

            RequestDispatcher dispatcher = request.getRequestDispatcher("afficherEtudiants.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            ((Throwable) e).printStackTrace();
            System.out.println("Exception details ,tsy tafiditra satria  :"+ e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}

}
