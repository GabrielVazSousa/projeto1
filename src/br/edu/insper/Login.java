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
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAO dao = new DAO();
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));

		
		List<User> daoList = dao.checkLogin(user);
		PrintWriter out = response.getWriter();
		
		if(daoList.isEmpty()) {
			
			out.println("<html><body>");
			out.println("ERRO");
			out.println("<form action='/Projeto1/Login.html' form method='get'>");
			out.println("<input type='submit' value='Voltar'>");
			out.println("</form>");
			out.println("</body></html>");
		}
		else {
			out.println("<html><body>");
			out.println("Bem vindo: "+ daoList.get(0).getName());
			out.println("<form action='/Projeto1/Login.html' form method='get'>");
			out.println("<input type='submit' value='Voltar'>");
			out.println("</form>");
			out.println("<form action='/Projeto1/VisualizaNotas.jsp' form method='get'>");
			out.println("<input type='submit' value='Visualizar Notas'>");
			out.println("</form>");
			out.println("<form action='/Projeto1/CriaNotas.html' form method='get'>");
			out.println("<input type='submit' value='Criar Nota'>");
			out.println("</form>");
			out.println("</body></html>");
		}
	}		
}


