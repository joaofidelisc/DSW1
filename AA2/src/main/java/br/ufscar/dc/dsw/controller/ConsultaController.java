package br.ufscar.dc.dsw.controller;

//import java.util.List;
import br.ufscar.dc.dsw.service.spec.*;

import java.security.Security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.*;
import br.ufscar.dc.dsw.security.UsuarioDetails;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private IConsultaService service;

    @Autowired
	private IClienteService clienteService;

	@Autowired
	private IProfissionalService profissionalService;

    private Cliente getClienteLogado(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails user = (UsuarioDetails) auth.getPrincipal();
		return clienteService.buscarPorId(user.getId());
    }

    private Profissional getProfissionalLogado(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails user = (UsuarioDetails) auth.getPrincipal();
		return profissionalService.buscarPorId(user.getId());
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Consulta consulta){
        return "consulta/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UsuarioDetails user = (UsuarioDetails) auth.getPrincipal();
        System.out.println("ConsultaController:59");
        if( user.getUsuario().getRole().equals("ROLE_CLIENTE")){
            Long id_cliente = getClienteLogado().getId();
            model.addAttribute("consultas", service.buscarTodosPorIdCliente( id_cliente ));
        }else if( user.getUsuario().getRole().equals("ROLE_PROF") ){
            Long id_prof = getProfissionalLogado().getId();
            model.addAttribute("consultas", service.buscarTodosPorIdProfissional( id_prof ));

        }
        return "consulta/lista";
    }

    @GetMapping("/agendar/{id_profissional}")
    public String agendar(@PathVariable("id_profissional") Long idP, Consulta consulta, ModelMap model){
                Cliente cliente= getClienteLogado();
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("idC: " + cliente.getId());
        System.out.println("idP: " + idP);
        Profissional prof = profissionalService.buscarPorId(idP);
        
        
        System.out.println("Cliente:" + cliente.getUsername());
        System.out.println("Profissional:" + prof.getUsername());

        model.addAttribute("cliente", cliente);
        model.addAttribute("profissional", prof);
        return "consulta/cadastro";
    }

        
        
    @PostMapping("/salvar")
    public String salvar(@Valid Consulta consulta, BindingResult result, RedirectAttributes attr, Long id_profissional) {
        if( result.hasErrors() ){
            System.out.println("AQUI Ó\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(result.toString());
            return "cliente/cadastro";
        }
        consulta.setCancelada("false");
        //consulta.setDataConsulta("2022-01-16");
        //consulta.setHoraConsulta("16:01");
        consulta.setCliente( getClienteLogado()  );
        consulta.setProfissional( profissionalService.buscarPorId( id_profissional ) );
        service.salvar(consulta);

        attr.addFlashAttribute("success", "consulta.create.success");
        return "redirect:/consultas/listar";
    }

    
}