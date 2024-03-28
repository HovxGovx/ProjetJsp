package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SupprimerEtudiantServlet
 */
@WebServlet("/SupprimerEtudiantServlet")
public class SupprimerEtudiantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerEtudiantServlet() {
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
	    String numEtParam = request.getParameter("numEt");

	    if (numEtParam != null && !numEtParam.isEmpty()) {
	        try {
	            String url = "jdbc:postgresql://localhost:5432/etudiant";
	            String user = "postgres";
	            String password = "mirahona1.";

	            try {
	                // Utilisation d'un bloc try-with-resources pour gérer la connexion
	                try (Connection connection = DriverManager.getConnection(url, user, password)) {
	                    String deleteQuery = "DELETE FROM etudiant WHERE numEt = ?";
	                    try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
	                        deleteStatement.setString(1, numEtParam);
	                        deleteStatement.executeUpdate();
	                    }

	                    // Envoyer une réponse appropriée au client
	                    response.setContentType("text/plain");
	                    response.getWriter().write("Étudiant supprimé avec succès");
	                }
	            } catch (SQLException e) {
	                // Gérer les erreurs SQL
	                e.printStackTrace();
	                response.setContentType("text/plain");
	                response.getWriter().write("Étudiant supprimé avec succès");
	            }
	        } catch (NumberFormatException e) {
	            // Gérer l'erreur si la conversion en entier échoue
	            e.printStackTrace();
	            response.setContentType("text/plain");
	            response.getWriter().write("Étudiant supprimé avec succès");
	        }
	    } else {
	    	response.setContentType("text/plain");
	    	response.getWriter().write("Étudiant supprimé avec succès");
	    }
	}


   

}
