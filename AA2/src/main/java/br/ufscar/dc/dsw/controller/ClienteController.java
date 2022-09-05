package br.ufscar.dc.dsw.controller;

//import java.util.List;
import br.ufscar.dc.dsw.service.spec.IClienteService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.dao.IUserDAO;
import br.ufscar.dc.dsw.domain.Cliente;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    private IClienteService service;
    @Autowired
    private IUserDAO userDao;
    
    @GetMapping("/cadastrar")
    public String cadastrar(Cliente cliente){
        return "cliente/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model){
        model.addAttribute("clientes", service.buscarTodos() );
        return "cliente/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr, BCryptPasswordEncoder encoder) {
        if( userDao.findBycpf( cliente.getCpf()) != null ){
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("--------------------------------------ERROS DO SISTEMA--------------------------------------");
            System.out.println("CPF já foi cadastrado, tente novamente com um outro CPF");
            return "redirect:/erro";
        }
        if( result.hasErrors() ){
            System.out.println(result.toString());
            return "redirect:/erro";
        }
        cliente.setEnabled(true);
        cliente.setRole("ROLE_CLIENTE");
        cliente.setPassword(encoder.encode(cliente.getPassword()));
        service.salvar(cliente);
        attr.addFlashAttribute("success", "cliente.create.success");
        return "redirect:/clientes/listar";
    }

    @GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cliente", service.buscarPorId(id));
        model.addAttribute("senhaParam", service.buscarPorId(id).getPassword());
		return "cliente/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
        cliente.setRole("ROLE_CLIENTE");
		if (result.getFieldErrorCount() > 1 || result.getFieldError("cpf") != null) {
			return "cliente/cadastro";
		}

		service.salvar(cliente);
		attr.addFlashAttribute("sucess", "cliente.edit.sucess");
		return "redirect:/clientes/listar";
	}
    
    @GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		//se tem consulta agendada
        //if (service.editoraTemLivros(id)) {
			//model.addAttribute("fail", "editora.delete.fail");
		//} else {
			service.excluir(id);
			model.addAttribute("sucess", "cliente.delete.sucess");
		//}
		return listar(model);
	}
}
