package com.amdocs.training.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
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

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	
	Connection conn = DBUtil.getConnection();
	
	@Override // POST to put data in the datatable
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = req.getParameter("username");
		String pass = req.getParameter("password");
		String email = req.getParameter("email");
		int phone = Integer.parseInt(req.getParameter("phone"));
		String address = req.getParameter("address");
		
		String query = "Insert into user1 (name, phone_no, email, address, reg_date, password, upload_photo) values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, name);
			ps.setInt(2, phone);
			ps.setString(3, email);
			ps.setString(4, address);
			ps.setDate(5, new Date(System.currentTimeMillis()));
			ps.setString(6, pass);
			ps.setString(7, "file.img");
			ps.executeUpdate();
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.html");
		    dispatcher.forward(req, resp);
		}catch (SQLException e){
			
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
            ResultSet rs = stmt.executeQuery("select * from user1"); 
            out.println("<table border=1 width=50% height=50%>");  
            out.println("<tr><th>UserId</th><th>Name</th><th>PhoneNo</th><th>Email</th><th>Address</th><th>RegDate</th><th>Password</th><th>UploadPhoto</th><tr>");
            while (rs.next()) 
            {  
                int id = rs.getInt("user_id");  
                String n = rs.getString("name");  
                int pn = rs.getInt("phone_no");
                String e = rs.getString("email");
                String a = rs.getString("address");
                Date d = rs.getDate("reg_date");
                String p = rs.getString("password");
                String up = rs.getString("upload_photo");
    
                out.println("<tr><td>" + id + "</td><td>" + n + "</td><td>" + pn + "</td><td>" + e + "</td><td>" + a + "</td><td>" + d + "</td><td>" + p + "</td><td>" + up + "</td></tr>");   
            }  
            out.println("</table>");  
            out.println("</html></body>");  
            conn.close();  
        }catch (Exception e) {  
            out.println("Error"); 
        }
        
	}
	
}
