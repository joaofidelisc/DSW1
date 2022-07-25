package br.ufscar.dc.dsw.domain;

import java.sql.Time;
import java.util.Date;

public class Consulta {
	
	private Long num_consulta;
    private Date data_consulta;
    private Time hora_consulta;
    private Long cpf_profissional;
    private Long cpf_cliente;
    private boolean cancelada; 
    
    
	
	public Consulta(Long num_consulta, Date data_consulta, Time hora_consulta, Long cpf_profissional, Long cpf_cliente, boolean cancelada) {
		super();
		this.num_consulta = num_consulta;
		this.data_consulta = data_consulta;
		this.hora_consulta = hora_consulta;
		this.cpf_profissional = cpf_profissional;
		this.cpf_cliente = cpf_cliente;
		this.cancelada = cancelada;
	}
	public Long getNum_consulta() {
		return num_consulta;
	}
	public void setNum_consulta(Long num_consulta) {
		this.num_consulta = num_consulta;
	}
	public Date getData_consulta() {
		return data_consulta;
	}
	public void setData_consulta(Date data_consulta) {
		this.data_consulta = data_consulta;
	}
	public Time getHora_consulta() {
		return hora_consulta;
	}
	public void setHora_consulta(Time hora_consulta) {
		this.hora_consulta = hora_consulta;
	}
	public Long getCpf_profissional() {
		return cpf_profissional;
	}
	public void setCpf_profissional(Long cpf_profissional) {
		this.cpf_profissional = cpf_profissional;
	}
	public Long getCpf_cliente() {
		return cpf_cliente;
	}
	public void setCpf_cliente(Long cpf_cliente) {
		this.cpf_cliente = cpf_cliente;
	}
	public boolean isCancelada() {
		return cancelada;
	}
	public void setCancelada(boolean cancelada) {
		this.cancelada = cancelada;
	}

    
    
    
	    
}
