package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Profissional;

public interface IProfissionalService {

	Profissional buscarPorCpf(String cpf);

	Profissional buscarPorId(Long id);

	List<Profissional> buscarTodos();

	void salvar(Profissional profissional);

	List<Profissional> buscarPorEspecialidade(String especialidade);

    List<Profissional> buscarPorAreaDeConhecimento(String areaDeConhecimento);

}