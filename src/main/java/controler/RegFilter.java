package controler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

@WebFilter("/register")
public class RegFilter extends HttpFilter implements Filter {
   
    public RegFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String uname=request.getParameter("uname");
		String upass=request.getParameter("upass");
		String cupass=request.getParameter("cupass");
		String uemail=request.getParameter("uemail");
		String policy=request.getParameter("policy");
		uname=uname.trim();
		if(!upass.equals(cupass)) {
			request.getRequestDispatcher("index.html").include(request, response);
			out.print("<p style='color:red'>Not match</p>");
		}
		
		
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
