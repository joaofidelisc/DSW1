package br.ufscar.dc.dsw.domain;

import java.util.Date;

public class Cliente {
	
	private String email;
    private String senha;
    private Long cpf;
    private String nome; 
    private Long telefone; 
    private String sexo; //F ou M
    private Date data_nascimento;

    public Cliente() {
    	
    }
    
    public Cliente(Long cpf) {
        this.setCpf(cpf);
    }

    public Cliente(String email, String senha, Long cpf, String nome, Long telefone, String sexo, Date data_nascimento) 
    {
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.data_nascimento = data_nascimento;
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

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}    
}
