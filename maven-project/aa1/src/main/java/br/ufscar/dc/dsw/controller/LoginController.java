package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.controller.util.Erro;
import br.ufscar.dc.dsw.dao.LoginDAO;
import br.ufscar.dc.dsw.domain.Administrador;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Login;
import br.ufscar.dc.dsw.domain.Profissional;

@WebServlet(urlPatterns = {"/login.jsp", "/logout.jsp"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Erro listaErros = new Erro();
    	
    	if(request.getParameter("btnLogin") != null) {//usuario esta fazendo login
    		String email = request.getParameter("email");
    		String senha = request.getParameter("senha");
    		if ( email == null || email.isEmpty()) { //não preencheu campo de login
    			listaErros.add("Preencha o campo de email!");
    		}
    		if( senha == null || email.isEmpty() ) {//não preencheu campo de senha
    			listaErros.add("Preenhca o campo de senha!");
    		}
    		if( !listaErros.temErro() ) {//se todos os campos foram preenchidos corretamente
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
    	    		}else {
        	    		listaErros.add("Senha incorreta, tente novamente!");
        	    	}
    	    		return;
    	    	}else {
    	    		listaErros.add("Usuário não encontrado!");
    	    	}
    		}
    	}
    	request.getSession().invalidate();//logout ou login não funcionou
    	request.setAttribute("mensages", listaErros);
    	RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
    	rd.forward(request, response);
    }
}
