package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 * Servlet implementation class StatisticsServlet
 */
@WebServlet("/StatisticsServlet")
public class StatisticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatisticsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            try {
                Class.forName("org.postgresql.Driver");
                String url = "jdbc:postgresql://localhost:5432/etudiant";
                String user = "postgres";
                String password = "mirahona1.";
                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    double classAverage = calculateClassAverage(connection);
                    double minAverage = calculateMinAverage(connection);
                    double maxAverage = calculateMaxAverage(connection);
                    // Respond with class statistics
                    response.setContentType("text/html");
                    PrintWriter out = response.getWriter();
                    out.println("<html><body>");
                    out.println("<p class=\"title\" style=\"margin-top: 10px;\">Class Statistics</>");
                    out.println("<p>Moyenne de classe: " + classAverage + "</p>");
                    out.println("<p>Moyenne minimal: " + minAverage + "</p>");
                    out.println("<p>Moyenne maximal: " + maxAverage + "</p>");
                    out.println("</body></html>");
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                response.getWriter().println("Error processing statistics.");
            }
        }

        private double calculateClassAverage(Connection connection) throws SQLException {
            String query = "SELECT AVG(moyenne) AS class_average FROM etudiant";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("class_average");
                }
                return 0;
            }
        }

        private double calculateMinAverage(Connection connection) throws SQLException {
            String query = "SELECT MIN(moyenne) AS min_average FROM etudiant";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("min_average");
                }
                return 0;
            }
        }

        private double calculateMaxAverage(Connection connection) throws SQLException {
            String query = "SELECT MAX(moyenne) AS max_average FROM etudiant";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("max_average");
                }
                return 0;
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
