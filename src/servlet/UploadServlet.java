package servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "UploadServlet")
public class UploadServlet extends HttpServlet {
    private String path;
    private boolean isMultipart;
    private int maxSize = 50 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file;

    @Override
    public void init() throws ServletException {
        super.init();
        path = getServletContext().getInitParameter("file_store_path");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        isMultipart = ServletFileUpload.isMultipartContent(request);
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=utf-8");
        if (!isMultipart) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>No file uploaded</p>");
            out.println("</body>");
            out.println("</html>");
            return;
        }

        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory(maxMemSize, new File("c:\\temp"));
        ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
        upload.setSizeMax(maxSize);
        try {
            List<FileItem> list = upload.parseRequest(request);
            Iterator<FileItem> iterator = list.iterator();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            while (iterator.hasNext()) {
                FileItem item = iterator.next();
                if (!item.isFormField()) {
                    String fileName = item.getName();
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(path + fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(path + "\\" + fileName);
                    }
                    item.write(file);
                    out.print("Upload file " + fileName + "<br/>");
                }
            }

            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new ServletException("Get method with " + getClass().getName() + ",please use post method.");
    }
}
