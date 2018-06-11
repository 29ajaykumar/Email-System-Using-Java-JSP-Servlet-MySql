/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delete;

import com.User.UserPass;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ajay
 */
@WebServlet(name = "deleteSEENMailServlet", urlPatterns = {"/deleteSEENMailServlet"})
public class deleteSEENMailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String deleteemailNo = request.getParameter("deleteemailNo");
//            DeleteEmail obj = new DeleteEmail();
//            obj.deleteEmail(deleteemailNo);

            response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");//HTTP 1.1
            response.setHeader("Pragma", "no-cache");//HTTP 1.0
            response.setHeader("Expires", "0");// for proxy

            String username = null;
            String password = null;
            HttpSession session = request.getSession();
            if (session.getAttribute("username") != null) {
                username = (String) session.getAttribute("username");
                // out.print("<font style='color:navy'>Welcome " + username + "</font>");
                // request.setAttribute("username", username);
            } else {
                request.setAttribute("Error1", "Plz Do login First");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }

            UserPass obju = new UserPass();
            password = obju.Userpass(username);

            DeleteMailSEEN sample = new DeleteMailSEEN();
            int emailno = Integer.parseInt(deleteemailNo);
            sample.getMails(emailno, username, password);

            RequestDispatcher rd = request.getRequestDispatcher("header.jsp");
            rd.include(request, response);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Delete Mail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1><br><br><br><br><br><br><center style='color:red'>Mail Delete Successfully</center><br><br><br><br><br><br></h1>");
            //out.println("<h1><br><br><br><br><br><br>"+deleteemailNo+"<br><br><br><br><br><br></h1>");
            out.println("</body>");
            out.println("</html>");

            RequestDispatcher rd1 = request.getRequestDispatcher("footer.html");
            rd1.include(request, response);
        }
    }

}
