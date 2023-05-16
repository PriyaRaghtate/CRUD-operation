package controler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Db;
import model.User;

@WebServlet("/home")
public class Home extends HttpServlet {
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
//int pageID=Integer.parseInt(request.getParameter("page"));
		
	//	int start=pageID;
		out.print("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css'>");
		out.print("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js'></script>");
		out.print("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js'></script>");
		int total=5;
		
		Db d=new Db();
		try {
			ArrayList<User> ul=d.getAllUser();
		//	ArrayList<User> ul=d.getUserByPage(start, total);
			out.print("<table class='table'>");
			out.print("<thead>");
			out.print("<tr>");
			out.print("<th>ID</th>");
			out.print("<th>Name</th>");
			out.print("<th>Passord</th>");
			out.print("<th>Email</th>");
			out.print("<th>Action</th>");
			out.print("</tr>");
			out.print("</thead>");
			out.print("<tbody>");
			for(User u:ul)
			{
				out.print("<tr>");
				out.print("<td>"+u.getUid()+"</td>");
				out.print("<td>"+u.getUname()+"</td>");
				out.print("<td>"+u.getUpass()+"</td>");
				out.print("<td>"+u.getUemail()+"</td>");
				out.print("<td><a class='btn btn-primary' href='update?id"+u.getUid()+"'>Update</a> <a class='btn btn-danger' href='delete?id="+u.getUid()+"'>Delete</a></td>");
		        out.print("</tr>");
			}
			out.print("</tbody>");
			out.print("</table>");
			
			
			
			
			
		}
			catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
