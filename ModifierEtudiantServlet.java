package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModifierEtudiantServlet
 */
@WebServlet("/ModifierEtudiantServlet")
public class ModifierEtudiantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierEtudiantServlet() {
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
        try {
            String nouvelleNumEtudiant = request.getParameter("numet");
            String nouveauNom = request.getParameter("nom");
            double nouvelleMoyenne = Double.parseDouble(request.getParameter("moyenne"));
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            if (!nouvelleNumEtudiant.isEmpty()  && !nouveauNom.isEmpty()) {
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
            try  {
            	String url = "jdbc:postgresql://localhost:5432/etudiant";
                String user = "postgres";
                String password = "mirahona1.";

                connection = DriverManager.getConnection(url, user, password);
                String updateQuery = "UPDATE etudiant SET nom = ?, moyenne = ? WHERE numEt = ?";
                preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setString(1, nouveauNom);
                preparedStatement.setDouble(2, nouvelleMoyenne);
                preparedStatement.setString(3, nouvelleNumEtudiant);
                preparedStatement.executeUpdate();

                    RequestDispatcher dispatcher = request.getRequestDispatcher("afficherEtudiants.jsp");
                    dispatcher.forward(request, response);
                            
            } catch (SQLException e) {
            	// Gérer les erreurs SQL
                e.printStackTrace();
                response.getWriter().write("Erreur lors de la modification de l'étudiant");
            }

           }         
        } catch (NumberFormatException e) {
            // Gérer les erreurs de conversion de paramètres
            e.printStackTrace();
            response.getWriter().write("Erreur de conversion de paramètres");
        }
    }
	
}
