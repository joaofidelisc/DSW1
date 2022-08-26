package br.ufscar.dc.dsw.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Cliente")
public class Cliente extends User {

    @NotBlank
	@Size(min = 11, max = 14)
	@Column(nullable = false, unique = true, length = 60)
	private String cpf;

	@NotBlank
	@Column(nullable = false, unique = true, length = 50)
    private String email;

    @NotBlank
	@Size(min = 11, max = 15)
	@Column(nullable = false, unique = false, length = 60)
	private String telefone;

    @NotBlank
	@Size(min = 1, max = 60)
	@Column(nullable = false, unique = false, length = 60)
	private String genero;

    @NotBlank
	@Size(min = 8, max = 10)
	@Column(nullable = false, unique = false, length = 60)
	private String dataNascimento;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE, orphanRemoval = false)
	private List<Consulta> consultas;
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
    public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

    public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

    public String getdataNascimento() {
		return dataNascimento;
	}

	public void setdataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
}
