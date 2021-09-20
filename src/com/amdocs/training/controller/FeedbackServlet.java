package com.amdocs.training.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amdocs.training.config.DBUtil;
@WebServlet("/feedback")
public class FeedbackServlet extends HttpServlet {
	
	Connection conn = DBUtil.getConnection();
	
	@Override // POST to put data in the datatable
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("username");
		String email = req.getParameter("email");
		String feed = req.getParameter("feed");
	
		String query = "Insert into feedback (user_id, name, email, feedback) values(?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setString(4, feed);
			ps.executeUpdate();
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.html");
		    dispatcher.forward(req, resp);
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}
	}
	
	@Override // GET to pick data from the databale
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();  
        resp.setContentType("text/html");  
        out.println("<html><body>"); 
        try {
        	Statement stmt = conn.createStatement();  
            ResultSet rs = stmt.executeQuery("select * from feedback"); 
            out.println("<table border=1 width=50% height=50%>");  
            out.println("<tr><th>UserId</th><th>Name</th><th>FeedbackId</th><th>Email</th><th>Feedback</th><tr>");
            while (rs.next()) 
            {  
                int id = rs.getInt("user_id");  
                String n = rs.getString("name");  
                int f = rs.getInt("f_id");
                String e = rs.getString("email");
                String r = rs.getString("feedback");
   
                out.println("<tr><td>" + id + "</td><td>" + n + "</td><td>" + f + "</td><td>" + e + "</td><td>" + r + "</td></tr>");   
            }  
            out.println("</table>");  
            out.println("</html></body>");  
            //conn.close();  
        }catch (Exception e) {  
            out.println(e.getMessage()); 
        }
	}
}
