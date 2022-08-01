package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.ConsultaDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Login;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = {"/consultas/*"})
public class ConsultaController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private ConsultaDAO dao;
	private Erro erros;
	
	@Override
	public void init() {
		dao = new ConsultaDAO();
		erros = new Erro();
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
					paginaCadastroConsulta(request, response);
					break;
				case "/insercao":
					insere(request, response);
					break;
				case "/cancelar":
					cancelar(request, response);
					break;
				default:
					paginaListaConsultas(request, response);
					break;
			}
			
		} catch (RuntimeException | IOException 	| ParseException | SQLException e) {
			throw new ServletException(e);
		}
		if(erros.temErro()) {
			request.setAttribute("mensagens", erros);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
			dispatcher.forward(request, response);
			erros.limpa();
		}
	}

	public void paginaCadastroConsulta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		Login lgn = (Login) request.getSession().getAttribute("usuarioLogado"); 
		if( lgn != null && lgn.getTipoLogin() == 3 ) {//apenas clientes podem agendar consultas
			request.setAttribute("flagReadonly", "");
			String str = request.getParameter("cpf_profissional");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/consulta/formulario.jsp");
			dispatcher.forward(request, response);
		}else {
				erros.add("Acesso negado! Apenas clientes podem agendar consultas");
		}
		
		
	}
	
	public void paginaListaConsultas(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		Login lgn = (Login) request.getSession().getAttribute("usuarioLogado");
		if( lgn != null && (lgn.getTipoLogin() == 2 || lgn.getTipoLogin() == 3) ) {//apenas clientes  e profissionais podem listar consultas
			
			List<Consulta> listaConsultas = dao.getAllByEmail( lgn.getEmail(), lgn.getTipoLogin() );
			request.setAttribute("listaConsultas", listaConsultas);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/consulta/lista.jsp");
			dispatcher.forward(request, response);
		}else {
			erros.add("Acesso negado! Apenas clientes e profissionais podem listar consultas");
		}
			
		
	}

	public void insere(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException, SQLException{
		request.setCharacterEncoding("UTF-8");
		Login lgn = (Login) request.getSession().getAttribute("usuarioLogado"); 
		if( lgn != null && lgn.getTipoLogin() == 3 ) {
			Date data_consulta = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("data_consulta"));
	        Time hora_consulta = java.sql.Time.valueOf( request.getParameter("hora_consulta") );
			Long cpf_profissional = Long.parseLong(request.getParameter("cpf_profissional"));
			Long cpf_cliente = Long.parseLong(request.getParameter("cpf_cliente"));
			boolean cancelada = false;
			
			java.sql.Date data_consulta_SQLDATE= new java.sql.Date(data_consulta.getTime());
			if( !dao.verificaSeClienteDisponivel(data_consulta_SQLDATE, hora_consulta, cpf_cliente) ) {
				erros.add("Você já tem uma consulta agendada nesta data! Tente agendar em outra data");
			}
			if( !dao.verificaSeProfissionalDisponivel(data_consulta_SQLDATE, hora_consulta, cpf_profissional) ) {
				erros.add("O profissional escolhido não está disponível nesta data! Agende com outro ou tente mudar a data");
			}
				if( erros.temErro()) {
				return;
			}
			
			
	        Consulta consulta = new Consulta(data_consulta, hora_consulta, cpf_profissional, cpf_cliente,cancelada);
	        dao.insert(consulta);
	        response.sendRedirect("consulta/lista.jsp");
		}else {
			erros.add("Acesso negado! Apenas clientes podem agendar consultas");
		}
	}

	public void cancelar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException{
		Login lgn = (Login) request.getSession().getAttribute("usuarioLogado"); 
		if( lgn != null && (lgn.getTipoLogin() == 2 || lgn.getTipoLogin() == 3 ) ) {
			Consulta consulta = dao.get( Long.parseLong(request.getParameter("num_consulta") ) );
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Locale loc = new Locale("BR");
			
			Date data_consulta = consulta.getData_consulta();
			int hora_consulta = consulta.getHora_consulta().getHours();
			float data_consulta_mili = data_consulta.getTime() + hora_consulta*60*60*1000;
			
			Calendar calendar_atual = GregorianCalendar.getInstance(loc);
			
			double diffEmMilisegundos = data_consulta_mili - calendar_atual.getTimeInMillis();
			double diaEmMili = 24*60*60*1000;
			double diffEmDias = diffEmMilisegundos/diaEmMili;
			double comp = 3.00000;
			double diff = diffEmDias- comp;
			if( (diff ) < 0 ) {
				erros.add("Consultas apenas podem ser canceladas com 3 dias de antecedência!");
				return;
			}
			
			consulta.setCancelada(true);
			dao.update(consulta);
			response.sendRedirect("lista");
		}else {
			erros.add("Acesso negado! Apenas clientes e profissionais podem cancelar consultas");
		}
			
	}
}
