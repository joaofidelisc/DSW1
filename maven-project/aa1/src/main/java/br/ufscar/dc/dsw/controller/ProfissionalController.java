package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;


import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/profissionais/*"})
public class ProfissionalController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ProfissionalDAO dao;
	
	@Override
	public void init() {
		dao = new ProfissionalDAO();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
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
					paginaCadastroProfissional(request, response);
					break;
				case "/insercao":
					insere(request, response);
				case "/remocao":
					remove(request, response);
					break;
				case "/edicao":
					paginaEdicaoProfissional(request, response);
					break;
				case "/atualizacao":
					atualize(request, response);
					break;
				default:
					paginaListaProfissionais(request, response);
					break;
			}
			
		} catch (RuntimeException | IOException | ServletException e) {
			throw new ServletException(e);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void paginaCadastroProfissional(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		request.setAttribute("flagReadonly", "");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/formulario.jsp");
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
		
		String areaConhecimento = request.getParameter("areaConhecimento");
		String especialidae = request.getParameter("especialidade");
		String local_pdf= request.getParameter("local_pdf");
		
		Profissional profissional = new Profissional(email, senha, cpf, nome, areaConhecimento, especialidae, local_pdf);
		
		dao.insert(profissional);
		response.sendRedirect("lista");		
	}
	
	private void paginaListaProfissionais(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		List<Profissional> listaProfissionais = dao.getAll();
		request.setAttribute("listaProfissionais", listaProfissionais);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/lista.jsp");
		dispatcher.forward(request, response);
	}
	
	private void paginaEdicaoProfissional(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		Long cpf = Long.parseLong( request.getParameter("cpf"));
		Profissional profissional = dao.get(cpf);
		
		request.setAttribute("profissional",profissional);
		request.setAttribute("flagReadonly", "readonly");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/formulario.jsp");
		dispatcher.forward(request, response);
	}
	
	private void atualize(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, ParseException {
			request.setCharacterEncoding("UTF-8");

			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			Long cpf;
			cpf = Long.parseLong(request.getParameter("cpf"));
			
			String areaConhecimento = request.getParameter("areaConhecimento");
			String especialidae = request.getParameter("especialidade");
			String local_pdf= request.getParameter("local_pdf");
			
			Profissional profissional = new Profissional(email, senha, cpf, nome, areaConhecimento, especialidae, local_pdf);
			
			dao.update(profissional);
			response.sendRedirect("lista");
		}
	
	private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
		Long cpf = Long.parseLong(request.getParameter("cpf"));
		Profissional profissional = new Profissional(cpf);
		dao.delete(profissional);
		response.sendRedirect("lista");
	}
	
}
