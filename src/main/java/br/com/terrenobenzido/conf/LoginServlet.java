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
		cliente.setEmail(user);
		cliente.setSenha(pwd);

		Cliente clienteS = bean.login(cliente);

		if (clienteS != null) {
			if (clienteS.getFuncao().equals("ADMIN")) {
				response.sendRedirect("admin/admin.xhtml");			
			}
			else {
				response.sendRedirect("cliente/perfil.xhtml");				
			}
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.xhtml");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
