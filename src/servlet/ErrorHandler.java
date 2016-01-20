package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ErrorHandler")
public class ErrorHandler extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        servletName = servletName == null ? "Unknown" : servletName;
        String reqURI = (String) request.getAttribute("javax.servlet.error.request_uri");
        reqURI = reqURI == null ? "Unknown" : reqURI;
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String title = "Error/Exception Information";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " +
                        "transitional//en\">\n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n");

        if (throwable == null && status == null) {
            out.println("<h2>Error information is missing</h2>");
            out.println("Please return to the <a href=\"" +
                    response.encodeURL("http://localhost:8082/") +
                    "\">Home Page</a>.");
        } else if (status != null) {
            out.println("Status Code: " + status);
        } else {
            out.println("<h2>Error information</h2>");
            out.println("Servlet Name : " + servletName +
                    "</br></br>");
            out.println("Exception Type : " +
                    throwable.getClass().getName() +
                    "</br></br>");
            out.println("The request URI: " + reqURI +
                    "<br><br>");
            out.println("The exception message: " +
                    throwable.getMessage());
        }
        out.println("</body>");
        out.println("</html>");
    }
}
