package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditSuccessServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String query = "update DHRUV.BOOK set BOOKEDITION=? ,BOOKPRICE=? where BOOKNAME = ? ";

        String name = request.getParameter("bname");

        try (PrintWriter out = response.getWriter()) {
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/BookWebApp", "dhruv", "dhruv");
                PreparedStatement ps = con.prepareStatement(query);

                String bookEdition = request.getParameter("bookEdition");
                Double bookPrice = Double.parseDouble(request.getParameter("bookPrice"));

                ps.setString(3, name);
                ps.setString(1, bookEdition);
                ps.setDouble(2, bookPrice);
                int count = ps.executeUpdate();

                if (count == 1) {
                    out.println("Record Updated Successfully");
                } else {
                    out.println("Record is not Updated Successfully");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            out.println("<br>");
            out.println("<a href='index.html'>Home</a>");
            out.println("<br>");
            out.println("<a href='bookList'>Book List</a>");
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
