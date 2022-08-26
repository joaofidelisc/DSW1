package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Consulta")
public class Consulta extends AbstractEntity<Long> {
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
	private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "profissional_id")
	private Profissional profissional;

    @NotBlank
	@Size(min = 8, max = 12)
	@Column(nullable = false, unique = false, length = 60)
	private String dataConsulta;
    
	@Column(nullable = false, unique = false)
	private int horaConsulta;

    public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

    public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

    public String getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(String data) {
		this.dataConsulta = data;
	}

    public int getHoraConsulta() {
		return horaConsulta;
	}

	public void setHoraConsulta(int hour) {
		this.horaConsulta = hour;
	}
}
