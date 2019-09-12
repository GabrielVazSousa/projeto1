package br.edu.insper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/signup")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAO dao = new DAO();
		User user = new User();
		String pass1 = request.getParameter("password");
		String pass2 = request.getParameter("password2");
		
		if(pass1.contentEquals(pass2)) {
			user.setName(request.getParameter("name"));
			user.setPassword(request.getParameter("password"));
			var result = dao.adiciona(user);
			PrintWriter out = response.getWriter();
			if (result == 0) {
				 response.sendRedirect("/Projeto1/Login.html");
				 dao.close();
			}else if(result == 1) {
				 out.println("<html><body>");
				 out.println("[ERRO] Usuário: " + user.getName()+" já cadastrado");
				 out.println("</body></html>");
				 dao.close();
			}else {
				 out.println("<html><body>");
				 out.println("[ERRO INTERNO]");
				 out.println("</body></html>");
				 dao.close();
			}
			
		}else {
			PrintWriter out = response.getWriter();
			 out.println("<html><body>");
			 out.println("ERRO - Senhas não batem");
			 out.println("</body></html>");
		}
	}		
}


