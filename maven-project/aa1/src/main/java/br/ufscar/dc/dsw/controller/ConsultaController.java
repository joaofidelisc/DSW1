package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
					//paginaCadastroConsulta(request, response);
					break;
				case "/insercao":
					insere(request, response);
				case "/cancelar":
					//cancelar(request, response);
					break;
				case "/atualizacao":
					atualize(request, response);
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

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

	public void insere(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
        
		Long num_consulta = Long.parseLong(request.getParameter("num_consulta"));
		Date data_consulta = new SimpleDateFormat("YYYY-MM-dd").parse(request.getParameter("data_consulta"));
        Time hora_consulta = request.getParameter("hora_consulta");								
		Long cpf_profissional = Long.parseLong(request.getParameter("cpf_profissional"));
		Long cpf_cliente = Long.parseLong(request.getParameter("cpf_cliente"));
		boolean cancelada = false;							
        
        Consulta consulta = new Consulta(num_consulta, data_consulta, hora_consulta, cpf_profissional, cpf_cliente,cancelada);
        dao.insert(consulta);
        response.sendRedirect("consulta/lista.jsp");
				
	}

	public void cancelar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
				//verificar se ainda tem 3 dias ou mais da consulta, se sim, pode cancelar
	}

	public void atualize(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
        
		Long num_consulta = Long.parseLong(request.getParameter("num_consulta"));
		Date data_consulta = new SimpleDateFormat("YYYY-MM-dd").parse(request.getParameter("data_consulta"));
		Time hora_consulta = request.getParameter("hora_consulta");								
		Long cpf_profissional = Long.parseLong(request.getParameter("cpf_profissional"));
		Long cpf_cliente = Long.parseLong(request.getParameter("cpf_cliente"));

		boolean cancelada = false;
			if(request.getParameter("cancelada").equals("true")){
				cancelada = true;
			}else{
				cancelada=false;
			}					
				
		Consulta consulta = new Consulta(num_consulta, data_consulta, hora_consulta, cpf_profissional, cpf_cliente,cancelada);
		dao.update(consulta);
		response.sendRedirect("consulta/lista.jsp");
	}




	

}
