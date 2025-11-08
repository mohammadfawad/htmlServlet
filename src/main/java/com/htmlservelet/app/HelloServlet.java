/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htmlservelet.app;

/**
 *
 * @author mmoha
 */

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/helloServlet") // URL: http://localhost:8080/MyWebApp/helloServlet
public class HelloServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get query parameter: ?name=Fawad
        String name = request.getParameter("name");
        if (name == null || name.isEmpty()) {
            name = "Guest";
        } response.getWriter().println("Hello from Servlet!");

        // Set response type
        response.setContentType("text/html");

        // Write HTML response
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Hello, " + name + "!</h2>");
        out.println("<p>This response is generated from a Java Servlet.</p>");
        out.println("</body></html>");
    }
}

