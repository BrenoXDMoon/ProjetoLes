package br.com.terrenobenzido.conf;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.terrenobenzido.modelo.dominio.Cliente;
import br.com.terrenobenzido.view.viewhelper.ClienteBean;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Cliente cliente = new Cliente();
	
	@Inject
	private ClienteBean bean;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// get request parameters for userID and password
		String user = request.getParameter("user");
		String pwd = request.getParameter("senha");

		HttpSession session = request.getSession(true);
		RequestDispatcher rd = null;
		Cliente clienteS = bean.login(user, pwd);

		if (clienteS != null) {
			session.setAttribute("status", false);
			
			session.setAttribute("cliente", clienteS);
			
			if (clienteS.getFuncao().equals("ADMIN")) {
				rd = request.getRequestDispatcher("admin/admin.xhtml");		
			}
			else {
				rd = request.getRequestDispatcher("cliente/perfil.xhtml");				
			}
			
			rd.forward(request, response);
		} else {
			
			session.setAttribute("status", true);
			response.sendRedirect("login.xhtml");
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
