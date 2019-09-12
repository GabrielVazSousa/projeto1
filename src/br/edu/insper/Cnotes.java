package br.edu.insper;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/cnota")
public class Cnotes extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAO dao = new DAO();
		Note note = new Note();
		note.setName(request.getParameter("name"));
		note.setLabel(request.getParameter("label"));
		note.setText(request.getParameter("text"));
		var result = dao.createNote(note);
		PrintWriter out = response.getWriter();
		if (result == 0) {
			 out.println("<html><body>");
			 out.println("Nota: " + note.getName()+" criada com sucesso");
			 out.println("</body></html>");
			 dao.close();
		}else {
			 out.println("<html><body>");
			 out.println("[ERRO ao criar nota]");
			 out.println("</body></html>");
			 dao.close();
		}
		
		
	}		
}
