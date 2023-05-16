package controler;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Db;
import model.User;

@WebServlet("/register")
public class registerserv extends HttpServlet {
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String uname = request.getParameter("uname");
		String upass = request.getParameter("upass");
		String cupass = request.getParameter("cupass");
		String uemail = request.getParameter("uemail");
		String policy = request.getParameter("policy");
		out.print("This is servlet<br>");
		
		Db db=new Db();
		try {
			if (!db.checkUser(uemail)) {

				User u = new User(uname, upass, uemail);
				System.out.println("Data 1 : " + u);
			
				try {

					int a = db.save(u);

					if (a > 0)
						out.print("User register<a href='login.html'>Login</a> ");
					else
						out.print("Error");

				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
			else
			{
				out.print("<br>Your Email ID is already Used Please Login ");
				out.print("<br><a href='login.html'>Login</a> ");

			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
