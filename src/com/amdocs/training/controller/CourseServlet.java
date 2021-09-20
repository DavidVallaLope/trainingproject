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
@WebServlet("/course")
public class CourseServlet extends HttpServlet {
	
	Connection conn = DBUtil.getConnection();
	
	@Override // POST to put data in the datatable
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = req.getParameter("name");
		String desc = req.getParameter("desc");
		int fee = Integer.parseInt(req.getParameter("fee"));
	
		String query = "Insert into course (c_name, c_desp, c_fees, c_resource) values(?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, desc);
			ps.setInt(3, fee);
			ps.setString(4, name+".mp4");
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
            ResultSet rs = stmt.executeQuery("select * from course"); 
            out.println("<table border=1 width=50% height=50%>");  
            out.println("<tr><th>CourseId</th><th>Name</th><th>Desp</th><th>Fees</th><th>Resource</th><tr>");
            while (rs.next()) 
            {  
                int id = rs.getInt("course_id");  
                String n = rs.getString("c_name");  
                String d = rs.getString("c_desp");
                String e = rs.getString("c_fees");
                String r = rs.getString("c_resource");
   
                out.println("<tr><td>" + id + "</td><td>" + n + "</td><td>" + d + "</td><td>" + e + "</td><td>" + r + "</td></tr>");   
            }  
            out.println("</table>");  
            out.println("</html></body>");  
            conn.close();  
        }catch (Exception e) {  
            out.println("Error"); 
        }
	}
}
