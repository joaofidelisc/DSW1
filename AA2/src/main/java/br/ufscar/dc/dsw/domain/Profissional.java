package br.ufscar.dc.dsw.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.Lob;

@SuppressWarnings("serial")
@Entity
@Table(name="Profissional")
public class Profissional extends User {
    
	@NotBlank
	@Size(min = 11, max = 14)
	@Column(nullable = false, unique = true, length = 60)
	private String cpf;

	@NotBlank
	@Column(nullable = false, unique = true, length = 50)
    private String email;
	
	@NotBlank
	@Column(nullable = false, length = 100)
	private String nomeArquivo;

	@Lob
	private byte[] qualificacoes;

    @NotBlank
	@Column(nullable = false, length = 50)
	private String areaDeConhecimento;

    @NotBlank
	@Column(nullable = false, length = 50)
	private String especialidade;

	@OneToMany(mappedBy = "profissional", cascade = CascadeType.REMOVE, orphanRemoval = false)
	private List<Consulta> consultas;
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String Cpf) {
		this.cpf = Cpf;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getQualificacoes() {
		return qualificacoes;
	}

	public void setQualificacoes(byte[] qualificacoes) {
		this.qualificacoes = qualificacoes;
	}

	public String getAreaDeConhecimento() {
		return areaDeConhecimento;
	}

	public void setAreaDeConhecimento(String areaDeConhecimento) {
		this.areaDeConhecimento = areaDeConhecimento;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
}