package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Administrator;

import org.springframework.data.repository.CrudRepository;

public interface AdminDAO extends CrudRepository<Administrator, String> {
    
}
