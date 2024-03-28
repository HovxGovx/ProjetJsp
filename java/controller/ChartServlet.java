package controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;

/**
 * Servlet implementation class ChartServlet
 */
@WebServlet("/ChartServlet")
public class ChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChartServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/png");
        ServletOutputStream out = response.getOutputStream();
        try {
            JFreeChart chart = createChart();
            ChartUtils.writeChartAsPNG(out, chart, 300, 250);
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.setContentType("text/plain");
            response.getWriter().println("Erreur lors de la génération du graphique : " + e.getMessage());
            return;
        } finally {
            out.close();
        }
    }

    private JFreeChart createChart() throws SQLException {
        try {
            HistogramDataset dataset = createDataset();
            JFreeChart chart = ChartFactory.createHistogram(
                    "Moyennes de classe",
                    "Moyenne", // Axe X : Moyenne
                    "Effectif", // Axe Y : Effectif
                    dataset,
                    PlotOrientation.VERTICAL,
                    true, true, false);
            
            return chart;
        } catch (SQLException e) {
            e.printStackTrace();
        }  
        return null;
    }

   

    private double[] collectClassData() throws SQLException {
        List<Double> moyennes = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/etudiant";
            String user = "postgres";
            String password = "mirahona1.";
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                String query = "SELECT moyenne FROM etudiant";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                     ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        moyennes.add(resultSet.getDouble("moyenne"));
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erreur lors de la récupération des données de la classe.");
        }
        // Convertir la liste en tableau de doubles
        double[] dataArray = new double[moyennes.size()];
        for (int i = 0; i < moyennes.size(); i++) {
            dataArray[i] = moyennes.get(i);
        }
        return dataArray;
    }

    private HistogramDataset createDataset() throws SQLException {
        HistogramDataset dataset = new HistogramDataset();
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/etudiant";
            String user = "postgres";
            String password = "mirahona1.";
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                double[] data = collectData(connection); // Collecte des données pour l'histogramme
                double classAverage = calculateClassAverage(data);
                double maxAverage = calculateMaxAverage(data);
                double minAverage = calculateMinAverage(data);
                
                dataset.addSeries("Moyenne maximale", new double[]{maxAverage}, 1);
                dataset.addSeries("Moyenne minimale", new double[]{minAverage}, 1);
                dataset.addSeries("Moyenne de la classe", new double[]{classAverage}, 1);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }

    private double calculateClassAverage(double[] data) {
        double sum = 0;
        for (double value : data) {
            sum += value;
        }
        return sum / data.length;
    }

    private double calculateMaxAverage(double[] data) {
        double max = Double.MIN_VALUE;
        for (double value : data) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    private double calculateMinAverage(double[] data) {
        double min = Double.MAX_VALUE;
        for (double value : data) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

   
    private double[] collectData(Connection connection) throws SQLException {
        List<Double> moyennes = new ArrayList<>();
        String query = "SELECT moyenne FROM etudiant";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                moyennes.add(resultSet.getDouble("moyenne"));
            }
        }
        // Convertir la liste en tableau de doubles
        double[] dataArray = new double[moyennes.size()];
        for (int i = 0; i < moyennes.size(); i++) {
            dataArray[i] = moyennes.get(i);
        }
        return dataArray;
    }

    private String[] collectNames(Connection connection) throws SQLException {
        List<String> names = new ArrayList<>();
        String query = "SELECT nom FROM etudiant";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                names.add(resultSet.getString("nom"));
            }
        }
        // Convertir la liste en tableau de Strings
        String[] nameArray = new String[names.size()];
        for (int i = 0; i < names.size(); i++) {
            nameArray[i] = names.get(i);
        }
        return nameArray;
    }

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
