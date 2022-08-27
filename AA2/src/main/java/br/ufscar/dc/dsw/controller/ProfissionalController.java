package br.ufscar.dc.dsw.controller;

//import java.util.List;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Profissional;

@Controller
@RequestMapping("/profissionais")
public class ProfissionalController {
    
    @Autowired
    private IProfissionalService service;
    
    @GetMapping("/cadastrar")
    public String cadastrar(Profissional profissional){
        return "profissional/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model){
        model.addAttribute("profissionais", service.buscarTodos() );
        return "profissional/lista"; 
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr) {
        if( result.hasErrors() ){
            System.out.println(result.toString());
            return "profissional/cadastro";
        }
        
        profissional.setEnabled(true);
        profissional.setRole("ROLE_PROF");
        service.salvar(profissional);
        attr.addFlashAttribute("success", "profissional.create.success");
        return "redirect:/profissionais/listar";
    }

    // @GetMapping("/editar/{id}")
	// public String preEditar(@PathVariable("id") Long id, ModelMap model) {
	// 	model.addAttribute("cliente", service.buscarPorId(id));
	// 	return "cliente/cadastro";
	// }
	
	// @PostMapping("/editar")
	// public String editar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
	// 	if (result.getFieldErrorCount() > 1 || result.getFieldError("cpf") == null) {
	// 		return "cliente/cadastro";
	// 	}

	// 	service.salvar(cliente);
	// 	attr.addFlashAttribute("sucess", "cliente.edit.sucess");
	// 	return "redirect:/clientes/listar";
	// }
}