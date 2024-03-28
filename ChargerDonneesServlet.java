package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ChargerDonneesServlet
 */
@WebServlet("/ChargerDonneesServlet")
public class ChargerDonneesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        try {
            // Explicitly load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establish a database connection
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
        String url = "jdbc:postgresql://localhost:5432/etudiant";
        String user = "postgres";
        String password = "mirahona1.";
		 try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM etudiant;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                StringBuilder tableRows = new StringBuilder();
                
                while (resultSet.next()) {
                	 // Récupérer la moyenne de l'étudiant
                    double moyenne = resultSet.getDouble("moyenne");

                    // Déterminer l'observation en fonction de la moyenne
                    String observation;
                    if (moyenne >= 10) {
                        observation = "Admis";
                    } else if (moyenne >= 5) {
                        observation = "Redoublant";
                    } else {
                        observation = "Exclus";
                    }

                    // Ajouter la ligne au tableau avec les boutons "Supprimer" et "Modifier"
                    tableRows.append("<tr>")
                            .append("<td>").append(resultSet.getString("numEt")).append("</td>")
                            .append("<td>").append(resultSet.getString("nom")).append("</td>")
                            .append("<td>").append(moyenne).append("</td>")
                            .append("<td>").append(observation).append("</td>")
                            .append("<td>")
                            .append("<button class=\"cssbuttons-io\" onclick=\"modifierEtudiant(" + resultSet.getString("numEt") + ", '" + resultSet.getString("nom") + "', " + moyenne + ")\"><span>Modifier</span></button>")
                            .append("<button class=\"cssbuttons-io2\" onclick=\"supprimerEtudiant(").append(resultSet.getString("numEt")).append(")\"><span>Supprimer</span></button>")
                            .append("</td>")
                            .append("</tr>"); 
                }

                response.getWriter().println(tableRows.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Tsy tafa ilay tableau.");
        }
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
