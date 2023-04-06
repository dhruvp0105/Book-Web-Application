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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String query = "insert into DHRUV.BOOK values(?,?,?)";

        String bookName = request.getParameter("bookName");
        String bookEdition = request.getParameter("bookEdition");
        double bookPrice = Double.parseDouble(request.getParameter("bookPrice"));

        try (PrintWriter out = response.getWriter()) {

            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/BookWebApp", "dhruv", "dhruv");
                PreparedStatement ps = con.prepareStatement(query);

                ps.setString(1, bookName);
                ps.setString(2, bookEdition);
                ps.setDouble(3, bookPrice);

                int count = ps.executeUpdate();
                if (count == 1) {
                    out.println("<h2>Record is Registered Successfully</h2>");
                } else {
                    out.println("<h2>Record is not Registered Successfully</h2>");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            out.println("<a href='index.html'>Home</a>");
            out.println("<br>");
            out.println("<a href='bookList'>Book List</a>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
