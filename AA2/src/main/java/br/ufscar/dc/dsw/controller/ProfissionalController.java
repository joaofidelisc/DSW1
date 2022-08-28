package br.ufscar.dc.dsw.controller;

//import java.util.List;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
    public String salvar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr, @RequestParam("file") MultipartFile file, BCryptPasswordEncoder encoder) throws IOException {
        System.out.println("\n\n1");
		String nomeArquivo = StringUtils.cleanPath(file.getOriginalFilename());
		profissional.setQualificacoes(file.getBytes());
		profissional.setNomeArquivo(nomeArquivo);
		System.out.println(profissional.getNomeArquivo());

		if( result.hasErrors() ){
			System.out.println(result.toString());
            return "profissional/cadastro";
        }
		System.out.println("\n\n2");
        
        profissional.setEnabled(true);
        profissional.setRole("ROLE_PROF");
		profissional.setPassword(encoder.encode(profissional.getPassword()));

        service.salvar(profissional);
        attr.addFlashAttribute("success", "profissional.create.success");
        return "redirect:/profissionais/listar";
    }

    @GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("profissional", service.buscarPorId(id));
		return "profissional/cadastro";
	}
	
	@PostMapping("/editar")
    public String editar(@Valid Profissional prof, BindingResult result, RedirectAttributes attr) {
        prof.setRole("ROLE_PROF");
		if (result.getFieldErrorCount() > 1 || result.getFieldError("cpf") != null) {
			return "profissional/cadastro";
	    }

		service.salvar(prof);
		attr.addFlashAttribute("sucess", "profissional.edit.sucess");
	 	return "redirect:/profissionais/listar";
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

    @GetMapping(value = "/download/{id}")
	public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id) {
		Profissional profissional= service.buscarPorId(id);

		// set content type
		response.setContentType("application/pdf");
		
		// add response header (caso queira forçar o download)
		//response.addHeader("Content-Disposition", "attachment; filename=" + professional.getName());
		try {
			// copies all bytes to an output stream
			response.getOutputStream().write(profissional.getQualificacoes());
			
			// flushes output stream
			response.getOutputStream().flush();
		} catch (IOException e) {
			System.out.println("Error :- " + e.getMessage());
			System.out.print("Error :- ");
		}
	}
}