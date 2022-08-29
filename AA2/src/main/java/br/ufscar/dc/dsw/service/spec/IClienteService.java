package br.ufscar.dc.dsw.service.spec;

import java.util.List;
import java.util.Calendar;

import br.ufscar.dc.dsw.domain.Cliente;

public interface IClienteService {
    Cliente buscarPorId(Long Id);

    Cliente buscarPorCpf(Long Id);

    List<Cliente> buscarTodos();

	void salvar(Cliente cliente);

	void excluir(Long id);
	
	boolean temConsultaMarcada(Long id_cliente, Calendar data);

}
