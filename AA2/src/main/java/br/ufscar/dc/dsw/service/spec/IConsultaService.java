package br.ufscar.dc.dsw.service.spec;
import java.util.List;

import br.ufscar.dc.dsw.domain.Consulta;
//import ch.qos.logback.core.net.server.Client;

public interface IConsultaService {
    Consulta buscarPorId(Long Id);

    List<Consulta> buscarTodos();

    void salvar(Consulta consulta);

    void excluir(Long id);

    //void agendar(Consulta consulta);
    //List<Consulta> buscarPorCliente(Client cliente);
    //List<Consulta> buscarPorProfissional(Profissinal profissional);

}
