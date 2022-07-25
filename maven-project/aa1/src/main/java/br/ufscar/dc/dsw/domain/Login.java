package br.ufscar.dc.dsw.domain;

public class Login {
	
	private String email;
	private String senha;
	private int tipoLogin;
	
	public Login() {	}
	
	public Login(String email, String senha, int tipoLogin) {
		this.email = email;
		this.senha = senha;
		this.tipoLogin = tipoLogin;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getTipoLogin() {
		return tipoLogin;
	}

	public void setTipoLogin(int tipoLogin) {
		this.tipoLogin = tipoLogin;
	}

}
