package br.ufscar.dc.dsw.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.IUserDAO;
import br.ufscar.dc.dsw.domain.User;

@Component
public class UniqueCPFValidator implements ConstraintValidator<UniqueCPF, String> {
    
    @Autowired
    private IUserDAO dao;

    @Override
    public boolean isValid(String CPF, ConstraintValidatorContext context){
        if (dao !=null){
            User user = dao.findBycpf(CPF);
            return user == null;
        }
        return true;
    }
}
