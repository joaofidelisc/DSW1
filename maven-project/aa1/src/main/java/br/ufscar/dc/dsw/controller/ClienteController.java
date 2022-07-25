package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.domain.Cliente;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/clientes/*"})
public class ClienteController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ClienteDAO dao;
	
	@Override
	public void init() {
		dao = new ClienteDAO();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException{
		doGet(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException{
		
		String action = request.getPathInfo();
		
		if(action == null) {
			action = "";
		}
		
		try {
			switch (action) {   
				case "/cadastro":
					paginaCadastroCliente(request, response);
					break;
				case "/insercao":
					insere(request, response);
				case "/remocao":
					remove(request, response);
					break;
				case "/edicao":
					paginaEdicaoCliente(request, response);
					break;
				case "/atualizacao":
					atualize(request, response);
					break;
				default:
					paginaListaClientes(request, response);
					break;
			}
			
		} catch (RuntimeException | IOException | ServletException e) {
			throw new ServletException(e);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void paginaCadastroCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setAttribute("flagReadonly", "");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/formulario.jsp");
		dispatcher.forward(request, response);		
	}
	
	private void paginaListaClientes(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		List<Cliente> listaClientes = dao.getAll();
		request.setAttribute("listaClientes", listaClientes);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/lista.jsp");
		dispatcher.forward(request, response);		
	}
	
	private void insere(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException{
		request.setCharacterEncoding("UTF-8");
		
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		Long cpf;
		cpf = Long.parseLong(request.getParameter("cpf"));
		String nome = request.getParameter("nome");
		Long telefone;
		telefone = Long.parseLong(request.getParameter("telefone"));
		String sexo = request.getParameter("sexo"); 
		String str = request.getParameter("data_nascimento");
		Date data_nascimento = new SimpleDateFormat("YYYY-MM-dd").parse(request.getParameter("data_nascimento"));
		
		Cliente cliente = new Cliente(email,senha,cpf,nome, telefone, sexo,data_nascimento);
		
		dao.insert(cliente);
		response.sendRedirect("lista");
	}
	
	private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
		Long cpf = Long.parseLong(request.getParameter("cpf"));
		Cliente cliente = new Cliente(cpf);
		dao.delete(cliente);
		response.sendRedirect("lista");
	}
	
	private void atualize(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException, ParseException {
		request.setCharacterEncoding("UTF-8");

		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		Long cpf;
		cpf = Long.parseLong(request.getParameter("cpf"));
		
		Long telefone;
		telefone = Long.parseLong(request.getParameter("telefone"));
		String sexo = request.getParameter("sexo"); 
		String str = request.getParameter("data_nascimento");
		Date data_nascimento = new SimpleDateFormat("YYYY-MM-dd").parse(request.getParameter("data_nascimento"));
		
		Cliente cliente = new Cliente(email,senha,cpf,nome, telefone, sexo,data_nascimento);
		dao.update(cliente);
		response.sendRedirect("lista");
	}
	
	private void paginaEdicaoCliente(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
		Long cpf = Long.parseLong( request.getParameter("cpf"));
		Cliente cliente = dao.get(cpf);
		
		request.setAttribute("cliente",cliente);
		request.setAttribute("flagReadonly", "readonly");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/formulario.jsp");
		dispatcher.forward(request, response);
	}
}
