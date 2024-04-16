package com.example.ykhaledcustomersupport;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "signupServlet", value = "/signup")
public class SignupServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect GET requests to the signup page
        request.getRequestDispatcher("/WEB-INF/jsp/view/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Accessing the userDB from LoginServlet (assuming it's accessible)
        Map<String, String> userDB = LoginServlet.userDB;

        // Check if username is already taken
        if (userDB.containsKey(username)) {
            // User already exists, redirect back to signup page with a message
            request.setAttribute("signupFailed", true);
            request.getRequestDispatcher("WEB-INF/jsp/view/signup.jsp").forward(request, response);
        } else {
            // Add new user to the userDB Map
            userDB.put(username, password);
            // Redirect to login page with success message
            request.setAttribute("signupSuccess", true);
            response.sendRedirect(request.getContextPath() + "/login?signupSuccess=true");
        }
    }
}