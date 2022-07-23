//package br.ufscar.dc.dsw.domain;

import java.time.LocalDate;

public class Consulta {
    private int8 num_consulta;
    private time dia_horario;
    private int profissional;  
    private boolean cancelada; 
    private int cliente; 

    public Consulta(int8 num_consulta, time dia_horario, int profissional, boolean cancelada, int cliente){
        this.num_consulta = num_consulta;
        this.dia_horario = dia_horario;
        this.profissional = profissional;
        this.cancelada = cancelada;
        this.cliente = cliente;
    }

    public int8 getNumConsulta() {
        return num_consulta;
    }

    public void setNumConsulta(int8 num_consulta) {
        this.num_consulta = num_consulta;
    }
}
