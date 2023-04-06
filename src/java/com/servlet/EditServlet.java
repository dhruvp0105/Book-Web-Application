package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editScreen")
public class EditServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String query = "select * from DHRUV.BOOK where BOOKNAME=?";
        String name = request.getParameter("bname");
        try (PrintWriter out = response.getWriter()) {

            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/BookWebApp", "dhruv", "dhruv");
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, name);
                ResultSet rs = ps.executeQuery();
                rs.next();

                out.println("<form action='editurl?bname=" + name + "' method='post'>");
                out.println("<table align='center'>");
                out.println("<tr>");
                out.println("<td>Book Name</td>");
                out.println("<td><input type='text' name='bookName' value='" + rs.getString(1) + "'></td>");
                out.println("</tr>");

                out.println("<tr>");
                out.println("<td>Book Edition</td>");
                out.println("<td><input type='text' name='bookEdition' value='" + rs.getString(2) + "'></td>");
                out.println("</tr>");

                out.println("<tr>");
                out.println("<td>Book Price</td>");
                out.println("<td><input type='text' name='bookPrice'value='" + rs.getString(3) + "'></td>");
                out.println("</tr>");

                out.println("<tr>");
                out.println("<td><input type='submit' value='Edit'></td>");
                out.println("<td><input type='reset' value='cancel'></td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("</form>");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
