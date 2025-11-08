/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htmlservelet.app;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author mmoha
 */

@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("eid"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");

        try (PrintWriter out = resp.getWriter()) {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test?zeroDateTimeBehavior=CONVERT_TO_NULL", 
                "root", ""
            );

            PreparedStatement ps = con.prepareStatement("INSERT INTO customer VALUES (?, ?, ?, ?)");
            ps.setInt(1, id);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, email);
            ps.executeUpdate();

            out.println("Record Inserted Successfully!");
            System.out.println("✅ Record Inserted");

            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
