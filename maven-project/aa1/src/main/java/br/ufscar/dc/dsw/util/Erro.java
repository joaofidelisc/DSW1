package br.ufscar.dc.dsw.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class Erro implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private final List<String> listaErros;
	

	public Erro() {
		listaErros = new ArrayList<>();
	}
	
	public Erro(String msg) {
		listaErros = new ArrayList<>();
		listaErros.add(msg);
	}
	public void add(String msg) {
		listaErros.add(msg);
	}
	
	public boolean temErro() {
		return !listaErros.isEmpty();
	}
	
	public List<String> getErros(){
		return listaErros;
	}
	public void limpa() {
		listaErros.clear();
	}
	
}
