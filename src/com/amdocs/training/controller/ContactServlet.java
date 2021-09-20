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

@WebServlet("/contact")
public class ContactServlet extends HttpServlet {
	
	Connection conn = DBUtil.getConnection();
	
	@Override // POST to put data in the datatable
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("username");
		String email = req.getParameter("email");
		int phone = Integer.parseInt(req.getParameter("phone"));
		String msg = req.getParameter("msg");
		
		String query = "Insert into contact (user_id, name, email, phone_no, messege) values(?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setInt(4, phone);
			ps.setString(5, msg);
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
            ResultSet rs = stmt.executeQuery("select * from contact"); 
            out.println("<table border=1 width=50% height=50%>");  
            out.println("<tr><th>UserId</th><th>Name</th><th>Email</th><th>PhoneNo</th><th>Message</th><th>ContactId</th><tr>");
            while (rs.next()) 
            {  
                int id = rs.getInt("user_id");  
                String n = rs.getString("name");  
                int pn = rs.getInt("Phone_no");
                String e = rs.getString("Email");
                String m = rs.getString("Messege");
                int c = rs.getInt("contact_id");
    
                out.println("<tr><td>" + id + "</td><td>" + n + "</td><td>" + e + "</td><td>" + pn + "</td><td>" + m + "</td><td>" + c + "</td></tr>");   
            }  
            out.println("</table>");  
            out.println("</html></body>");  
            conn.close();  
        }catch (Exception e) {  
            out.println("Error"); 
        }
	}
}
