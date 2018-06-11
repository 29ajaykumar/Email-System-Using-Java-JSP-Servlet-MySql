/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reply;

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
@WebServlet(name = "replyServletUNSEEN", urlPatterns = {"/replyServletUNSEEN"})
public class replyServletUNSEEN extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

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

        String content = request.getParameter("messageContent");
        String emailNo = request.getParameter("emailNo");
        ReplyEmailUNSEEN obj = new ReplyEmailUNSEEN();
        obj.replyEmail(content, emailNo, username, password);

        RequestDispatcher rd = request.getRequestDispatcher("header.jsp");
        rd.include(request, response);

        /* TODO output your page here. You may use following sample code. */
        out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Frameset//EN\" \"http://www.w3.org/TR/html4/frameset.dtd\">");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet replyServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1><br><br><br><br><br><br><center style='color:green'>reply SuccessFully</center><br><br><br><br></h1>");
        out.println("<h1>" + content + "<br><br><br><br></h1>");
        //out.println("<h1>" + emailNo + "<br><br><br><br><br><br><br></h1>");
        out.println("</body>");
        out.println("</html>");

        RequestDispatcher rd1 = request.getRequestDispatcher("footer.html");
        rd1.include(request, response);

    }

}
