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

@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String query = "select * from DHRUV.BOOK";
        try (PrintWriter out = response.getWriter()) {

            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/BookWebApp", "dhruv", "dhruv");
                PreparedStatement ps = con.prepareStatement(query);

                ResultSet rs = ps.executeQuery();
                out.println("<table border='1' align='center'>");
                out.println("<tr>");
                out.println("<th>Book Name</th>");
                out.println("<th>Book Edition</th>");
                out.println("<th>Book Price</th>");
                out.println("<th>Edit</th>");
                out.println("<th>Delete</th>");
                out.println("</tr>");

                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getString(1) + "</td>");
                    out.println("<td>" + rs.getString(2) + "</td>");
                    out.println("<td>" + rs.getDouble(3) + "</td>");
                    out.println("<td><a href='editScreen?bname=" + rs.getString(1) + "'>Edit</a></td>");
                    out.println("<td><a href='deleteUrl?bname=" + rs.getString(1) + "'>Delete</a></td>");
                    out.println("</tr>");
                }
                out.println("</table>");

            } catch (Exception e) {
                e.printStackTrace();
            }
            out.println("<a href='index.html'>Home</a>");
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
