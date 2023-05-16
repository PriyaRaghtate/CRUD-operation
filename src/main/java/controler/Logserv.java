package controler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Db;


@WebServlet("/logserv")
public class Logserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String uemail = request.getParameter("uemail");
		String upass = request.getParameter("upass");
		Db db=new Db();
		try {
			if(db.login(uemail, upass)) {
				request.getRequestDispatcher("home").include(request, response);
				//response.sendRedirect("home");
				System.out.println("Successful");
			}
			else {
				request.getRequestDispatcher("login.html").include(request, response);
				out.print("usernae and pass is not matched");
				System.out.println("not matched");
			}
		} catch (ClassNotFoundException e) {
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
