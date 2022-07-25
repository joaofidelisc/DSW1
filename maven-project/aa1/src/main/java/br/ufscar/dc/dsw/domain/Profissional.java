package br.ufscar.dc.dsw.domain;

public class Profissional {
	
	private String email;
    private String senha;
    private Long cpf;
    private String nome; 
	private String areaConhecimento;
	private String especialidade;
	private String local_pdf;

    public Profissional(Long cpf) {
        this.setCpf(cpf);
    }

    public Profissional(String email, String senha, Long cpf, String nome, String areaConhecimento, String especialidade, String local_pdf) 
    {
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.nome = nome;
		this.areaConhecimento = areaConhecimento;
		this.especialidade = especialidade;
		this.local_pdf = local_pdf;
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

	public String getAreaConhecimento() {
		return areaConhecimento;
	}

	public void setAreaConhecimento(String areaConhecimento) {
		this.areaConhecimento = areaConhecimento;
	}

	public String getEspecialidade(){
		return especialidade;
	}

	public void setEspecialidade(String especialidade){
		this.especialidade = especialidade;
	}

	public String getLocal_pdf(){
		return local_pdf;
	}

	public void setLocal_pdf(String local_pdf){
		this.local_pdf = local_pdf;
	}  
}
