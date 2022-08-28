package br.ufscar.dc.dsw.dao;
import java.util.List;
import br.ufscar.dc.dsw.domain.Profissional;
import org.springframework.data.repository.CrudRepository;

@SuppressWarnings("unchecked")
public interface IProfissionalDAO extends CrudRepository<Profissional, String> {
    Profissional findByCpf(String cpf);

	Profissional findById(long id);

	Profissional save(Profissional user);	

	List<Profissional> findAll();

    void deleteById(Long id);
    
	List<Profissional> findByEspecialidade(String especialidade);

    List<Profissional> findByAreaDeConhecimento(String areaDeConhecimento);
}
