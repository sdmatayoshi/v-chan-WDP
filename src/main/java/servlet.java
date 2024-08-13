
import java.io.IOException;
import java.lang.ProcessHandle.Info;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet")
public class servlet extends HttpServlet {
	private static final String URL = "jdbc:mariadb://localhost:3306/v-chan";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static ArrayList<String> names() {
    	ArrayList<String> noms = new ArrayList<>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection link = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = link.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM usuarios");

            
            while (resultSet.next()) {
                String nombre = resultSet.getString("usu_nombre");
            	//String dato = resultSet.getString("");
                //System.out.println(dato);
                noms.add(nombre);
            }
            resultSet.close();
            statement.close();
            link.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return noms;
    }
    
    public static ArrayList<String> ids() {
    	ArrayList<String> ids = new ArrayList<>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection link = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = link.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM usuarios");

            
            while (resultSet.next()) {
                String id = resultSet.getString("id");
            	//String dato = resultSet.getString("");
                //System.out.println(dato);
                ids.add(id);
            }
            resultSet.close();
            statement.close();
            link.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return ids;
    }
    
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        // Llamar al método de prueba de la base de datos
      

        //response.getWriter().println("<h1>Consulta a la base de datos realizada.</h1>");
        for(int i = 0;i<servlet.names().size();i++ ) {
        	 response.getWriter().println("<h3>["+servlet.ids().get(i)+"] "+servlet.names().get(i)+"</h3>");
        }
    }
}
