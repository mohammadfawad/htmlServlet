/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htmlservelet.app;

import com.htmlservelet.app.model.Customer;
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
        
        Customer customer =  new Customer();
        customer.setId((long)id);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        

        try (PrintWriter out = resp.getWriter()) {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test?zeroDateTimeBehavior=CONVERT_TO_NULL", 
                "root", ""
            );

            PreparedStatement ps = con.prepareStatement("INSERT INTO customer VALUES (?, ?, ?, ?)");
            ps.setLong(1, customer.getId());
            ps.setString(2, customer.getFirstName());
            ps.setString(3, customer.getLastName());
            ps.setString(4, customer.getEmail());
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
