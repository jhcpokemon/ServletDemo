package servlet;

import com.mysql.jdbc.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "DatabaseAccess")
public class DatabaseAccess extends HttpServlet {
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_PATH = "jdbc:mysql://localhost/test";
    private static final String USER_NAME = "root";
    private static final String PW = "940310";
    private Driver driver;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String title = "Database Result";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " +
                        "transitional//en\">\n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n");
        try {
            driver = (Driver) Class.forName(JDBC_DRIVER).newInstance();
            Connection connection = DriverManager.getConnection(DB_PATH, USER_NAME, PW);
            Statement statement = connection.createStatement();
            String sql = "select * from student";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("sname");
                Integer age = resultSet.getInt("age");
                String sex = resultSet.getString("sex");
                String place = resultSet.getString("nativeplace");
                System.out.println(place);
                out.print("Name:" + name + "<br/>" +
                        "Age:" + age + "<br/>"
                        + "Sex:" + sex + "<br/>"
                        + "Native Place:" + place + "<br/>");
            }
            out.println("</body></html>");
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            DriverManager.deregisterDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
