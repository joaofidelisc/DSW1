package br.ufscar.dc.dsw.dao;

import java.util.List;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@SuppressWarnings("unchecked")
public interface IClienteDAO extends CrudRepository<Cliente, String> {

	@Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);

    Cliente findById(long id);

    Cliente findByCpf(long cpf);

	Cliente findByUsername(String email);

	List<Cliente> findAll();
	
	Cliente save(Cliente user);

	void deleteById(Long id);

    //fazer um getDataConsultas ou estalivre()
}
