package br.ufscar.dc.dsw.controller;

//import java.util.List;
import br.ufscar.dc.dsw.service.spec.*;

import java.util.List;

//import java.security.Security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        Profissional prof = profissionalService.buscarPorId(idP);
        
        model.addAttribute("cliente", cliente);
        model.addAttribute("profissional", prof);

        return "consulta/cadastro";
    }

        
        
    @PostMapping("/salvar")
    public String salvar(@Valid Consulta consulta, BindingResult result, RedirectAttributes attr, Long id_profissional) {
        Cliente cliente= getClienteLogado();
        if( result.hasErrors() ){
            System.out.println("AQUI Ó\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(result.toString());
            return "cliente/cadastro";
        }
        consulta.setCancelada("false");
        consulta.setCliente( getClienteLogado() );
        consulta.setProfissional( profissionalService.buscarPorId( id_profissional ) );

        boolean deuProblema = false;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UsuarioDetails user = (UsuarioDetails) auth.getPrincipal();
        //se cliente logado, vamos ver se ele tem disponibilidade para a consulta
        if( user.getUsuario().getRole().equals("ROLE_CLIENTE")){
            List<Consulta> consultasCliente = service.buscarTodosPorIdCliente( cliente.getId() );
            List<Consulta> consultasProf = service.buscarTodosPorIdProfissional( consulta.getProfissional().getId() );
            System.out.println(consultasProf);

            for( Consulta e : consultasCliente){
                if( e.getCliente().getId() == consulta.getCliente().getId() ){//se cliente tem é o mesmo do elemento e
                    if( e.getDataConsulta().equals(consulta.getDataConsulta()) ){//se cliente ja tem consulta marcada no mesmo dia
                        if( e.getHoraConsulta().equals(consulta.getHoraConsulta()) ){//se cliente tem consulta marcada no mesmo dia e horário
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                            System.out.println("--------------------------------------ERROS DO SISTEMA--------------------------------------");
                            System.out.println("Não foi possível agendar uma consulta. Você já tem uma consulta marcada nesse horário!");
                            System.out.println("--------------------------------------ERROS DO SISTEMA--------------------------------------");
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                            deuProblema = true;
                        }
                    }
                    
                }
            }
            for( Consulta e : consultasProf){
                if( e.getProfissional().getId() == consulta.getProfissional().getId() ){//se cliente tem é o mesmo do elemento e
                    if( e.getDataConsulta().equals(consulta.getDataConsulta()) ){//se cliente ja tem consulta marcada no mesmo dia
                        if( e.getHoraConsulta().equals(consulta.getHoraConsulta()) ){//se cliente tem consulta marcada no mesmo dia e horário
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                            System.out.println("--------------------------------------ERROS DO SISTEMA--------------------------------------");
                            System.out.println("Não foi possível agendar uma consulta. O profissional selecionado não está disponível!");
                            System.out.println("--------------------------------------ERROS DO SISTEMA--------------------------------------");
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                            deuProblema = true;
                        }
                    }
                }
            }
        }
        if(deuProblema)
            return "redirect:/consultas/listar";
            
        service.salvar(consulta);

        attr.addFlashAttribute("success", "consulta.create.success");
        return "redirect:/consultas/listar";
    }

    @GetMapping("/cancelar/{id}")
    public String cancelar(@PathVariable("id") Long id_consulta, Long id_profissional, ModelMap model) {
        Consulta consulta = service.buscarPorId(id_consulta);
        consulta.setCancelada("true");
        service.salvar(consulta);
        return listar(model);
    }

    
}
