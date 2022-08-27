package br.ufscar.dc.dsw.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name="Profissional")
public class Profissional extends User {	
	// @NotBlank
	@Column(nullable = true, length = 100)
	private String nomeArquivo;

    @NotBlank
	@Column(nullable = false, length = 50)
	private String areaDeConhecimento;

    @NotBlank
	@Column(nullable = false, length = 50)
	private String especialidade;

	@Column(nullable = false, length = 45)
    private String role;

	@OneToMany(mappedBy = "profissional", cascade = CascadeType.REMOVE, orphanRemoval = false)
	private List<Consulta> consultas;

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

	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
}