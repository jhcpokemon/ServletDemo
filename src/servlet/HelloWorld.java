package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "HelloWorld")
public class HelloWorld extends HttpServlet {
    //private String message;
    private PrintWriter printWriter;

    @Override
    public void init() throws ServletException {
        super.init();
        //message = "Hello world!";
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        printWriter = response.getWriter();
        printWriter.write(
                "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "\t<title>Hello World</title>\n" +
                        "</head>\n" +
                        "<body>\n");
//                        "<p>" + "<b>Name:</b> " + request.getParameter("name") + "</p>\n" +
//                        "<p>" + "<b>Age</b> " + request.getParameter("age") + "</p>\n" +
//                        "<p>" + "Math Flag: " + request.getParameter("maths")+"</p>\n"+
//                        "<p>" + "Physic Flag: " + request.getParameter("physics")+"</p>\n"+
//                        "<p>" + "Chemistry Flag: " + request.getParameter("chemistry")+"</p>\n"+
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            printWriter.print("<tr><td>" + paramName + "</td>\n<td>");
            String[] values = request.getParameterValues(paramName);
            if (values.length == 1) {
                String value = values[0];
                if (value.length() == 0)
                    printWriter.println("<i>No Value</i>");
                else
                    printWriter.print("<ul><li>" + value + "</li></ul>");
            } else {
                printWriter.println("<ul>");
                for (String value : values) {
                    printWriter.println("<li>" + value);
                }
                printWriter.println("</ul>");
            }
        }

        printWriter.println("</tr>\n</table>\n</body></html>");
        printWriter.write(
                "</body>\n" + "</html>");
    }


    @Override
    public void destroy() {
        super.destroy();
    }
}
