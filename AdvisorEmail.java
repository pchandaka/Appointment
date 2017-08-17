package com.chandaka;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdvisorEmail
 */
@WebServlet("/AdvisorEmail")
public class AdvisorEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvisorEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		HttpSession session=null;
		String prof=request.getAttribute("prof").toString();
		 String time=request.getAttribute( "time").toString();
		 String sid=request.getAttribute( "sid").toString();
		 String reg=request.getAttribute( "reg").toString();
		 String month=request.getAttribute( "month").toString();
		 String day=request.getAttribute( "day").toString();
		 String mobile=request.getAttribute( "mobile").toString();
		 String semail=request.getAttribute( "email").toString();
		 
String from="appointment@saumag.edu";
		String to=null;
		try {

			
		
			con=DBConnectionUtil.getDBconnection();

			String query = "select EMAIL from Advisor where advisor=?";
			 pstmt= con.prepareStatement(query);
			pstmt.setString(1, prof);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				
			//to=	rs.getString("Email");
			}
			  EmailUtil eu= new EmailUtil();
		        eu.mainMethod(to,from,sid,reg,month,day,mobile,semail);
		        request.setAttribute( "time",time);

			
			   RequestDispatcher dispatcher = request.getRequestDispatcher("/StudentSave.jsp");
				dispatcher.forward(request, response);
	}catch(Exception e){
		e.printStackTrace();
	} finally {
		try {
			rs.close();
			pstmt.close();

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
	

}
