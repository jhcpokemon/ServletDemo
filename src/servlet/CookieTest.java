package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "CookieTest")
public class CookieTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie name = new Cookie("name", request.getParameter("name"));
        Cookie age = new Cookie("age", request.getParameter("age"));
        name.setMaxAge(60 * 60 * 24);
        age.setMaxAge(60 * 60 * 24);
        response.addCookie(name);
        response.addCookie(age);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Setting Cookies Example";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " +
                        "transitional//en\">\n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<ul>\n" +
                "  <li><b>Name</b>: "
                + request.getParameter("name") + "\n" +
                "  <li><b>Age</b>: "
                + request.getParameter("age") + "\n" +
                "</ul>\n" +
                "</body></html>");
    }
}
