package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.ConsultaDAO;
import br.ufscar.dc.dsw.domain.Consulta;

@WebServlet(urlPatterns = {"/consultas/*"})
public class ConsultaController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private ConsultaDAO dao;
	
	@Override
	public void init() {
		dao = new ConsultaDAO();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		doGet(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
String action = request.getPathInfo();
		
		if(action == null) {
			action = "";
		}
		
		try {
			switch (action) {   
				case "/cadastro":
					//paginaCadastroCliente(request, response);
					break;
				case "/insercao":
					//insere(request, response);
				case "/remocao":
					//remove(request, response);
					break;
				case "/edicao":
					//paginaEdicaoCliente(request, response);
					break;
				case "/atualizacao":
					//atualize(request, response);
					break;
				default:
					paginaListaConsultas(request, response);
					break;
			}
			
		} catch (RuntimeException | IOException /*| ServletException*/ e) {
			throw new ServletException(e);
		}
		
	}
	
	public void paginaListaConsultas(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		List<Consulta> listaConsultas = dao.getAll();
		request.setAttribute("listaConsultas", listaConsultas);
		RequestDispatcher dispatcher = request.getRequestDispatcher("consulta/lista.jsp");
		dispatcher.forward(request, response);		
	}
	

}
