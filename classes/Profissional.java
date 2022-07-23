// package br.ufscar.dc.dsw.domain;

import java.util.Date;

public class Profissional {
	
	private String email;
    private String senha;
    private long cpf;
    private String nome; 
	private String areaDeConhecimento;
	private String especialidade;
	private String localPDF;

    public Profissional(long cpf) {
        this.setCpf(cpf);
    }

    public Profissional(String email, String senha, long cpf, String nome, String areaDeConhecimento, String especialidade, String localPDF) 
    {
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.nome = nome;
		this.areaDeConhecimento = areaDeConhecimento;
		this.especialidade = especialidade;
		this.localPDF = localPDF;
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

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAreaDeConhecimento() {
		return areaDeConhecimento;
	}

	public void setAreaDeConhecimento(String areaDeConhecimento) {
		this.areaDeConhecimento = areaDeConhecimento;
	}

	public String getEspecialidade(){
		return especialidade;
	}

	public void setEspecialidade(String especialidade){
		this.especialidade = especialidade;
	}

	public String getLocalPDF(){
		return localPDF;
	}

	public void setLocalPDF(String localPDF){
		this.localPDF = localPDF;
	}
	
	public static void main(String[] args){
		Profissional profissional_clt = new Profissional(414);
		profissional_clt.setAreaDeConhecimento("Magia");
		System.out.println("CPF Profissional:"+profissional_clt.getCpf());
		System.out.println("Area conhecimento:"+profissional_clt.getAreaDeConhecimento());

	}     
}
