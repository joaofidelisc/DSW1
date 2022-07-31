package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.util.Erro;
import br.ufscar.dc.dsw.dao.LoginDAO;
import br.ufscar.dc.dsw.domain.Login;

@WebServlet(urlPatterns = {"/login.jsp", "/logout.jsp"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Erro erros = new Erro();

    	if(request.getParameter("btnLogin") != null) {//usuario esta fazendo login
    		String email = request.getParameter("email");
    		String senha = request.getParameter("senha");
    		if ( email == null || email.isEmpty()) { //não preencheu campo de login
    			erros.add("O campo de email não foi preenchido!");
    		}
    		if( senha == null || senha.isEmpty() ) {//não preencheu campo de senha
    			erros.add("O campo de senha não foi preenchido!");
    		}
    		if( !erros.temErro() ) {//se todos os campos foram preenchidos corretamente
    			LoginDAO daoLogin = new LoginDAO();
    	    	Login login = null;
				try {
					login = daoLogin.buscaLoginPorEmail( email );
				} catch (SQLException e) {
					e.printStackTrace();
				}
    	    	if( login != null) {//login encontrado
    	    		if( login.getSenha().equals(senha) ) {
            	    	request.getSession().setAttribute("usuarioLogado", login);//iniciando a sessao
            	    	response.sendRedirect(request.getContextPath() + "/home.jsp");
        	    		return;
    	    		}else {
        	    		erros.add("Senha incorreta, tente novamente!");
        	    	}
    	    	}else {
    	    		erros.add("Usuário não encontrado!" );
    	    	}
    		}
    	}
    	String str = "/erro.jsp";
    	if( request.getServletPath().equals("/logout.jsp") ) {
    		str = "/home.jsp";    		
    	}else {
    		request.setAttribute("mensagens", erros);
    	}
    	request.getSession().invalidate();//logout ou login não funcionou
    	
    	RequestDispatcher rd = request.getRequestDispatcher(str);
    	rd.forward(request, response);
    }
}
