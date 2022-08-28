package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

//import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Consulta;
//import br.ufscar.dc.dsw.domain.Profissional;

@SuppressWarnings("unchecked")
public interface IConsultaDAO extends CrudRepository<Consulta, Long> {
    
    Consulta findById(long id);

    List<Consulta> findAll();

    List<Consulta> findAllBycliente_id(long cliente_id);

    List<Consulta> findAllByprofissional_id(long cliente_id);

    Consulta save(Consulta consulta);

    // List<Consulta> buscarPorCliente(Cliente cliente);
    // List<Consulta> buscarPorProfissional(Profissional profissional);

    // Consulta agendar(Consulta consulta);
}
