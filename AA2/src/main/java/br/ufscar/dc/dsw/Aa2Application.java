package br.ufscar.dc.dsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.domain.*;
import br.ufscar.dc.dsw.dao.*;

@SpringBootApplication
public class Aa2Application {

	public static void main(String[] args) {
		SpringApplication.run(Aa2Application.class, args);
	}


	@Bean
	// public CommandLineRunner demo(UserDAO usuarioDAO, ProfessionalDAO locadoraDAO, BCryptPasswordEncoder encoder) {
	public CommandLineRunner demo(IUserDAO usuarioDAO, BCryptPasswordEncoder encoder) {
		return (args) -> {
		 	// User user = new User();
		    // 	user.setCpf("44071481870");
		    // 	user.setEnabled(true);
		    // 	user.setName("Luiz Lipe");
		    // 	user.setPassword(encoder.encode("123"));
		    // 	user.setRole("ROLE_ADMIN");
		    // 	user.setUsername("luiz@mail.com");
		    // 	usuarioDAO.save(user);
 
			// Cliente client1 = new Cliente();
			// client1.setUsername("joao@mail.com");
			// client1.setName("João Vitor Fidelis Cardozo");
			// client1.setPassword(encoder.encode("123"));
			// client1.setRole("ROLE_CLIENTE");
			// client1.setCpf("41438691874");
			// client1.setTelefone("16999922222");
			// client1.setGenero("M");
			// client1.setdataNascimento("1998-06-10");
			// client1.setEnabled(true);
			// usuarioDAO.save(client1);
			
			// Profissional client2 = new Profissional();
			// client2.setUsername("matteus@mail.com");
			// client2.setName("Matteus Souza");
			// client2.setPassword(encoder.encode("123"));
			// client2.setRole("ROLE_PROF");
			// client2.setCpf("99999999900");
			// client2.setAreaDeConhecimento("Medicina");
            // 		client2.setEspecialidade("Cardiologista");
            // 		client2.setNomeArquivo("arq.pdf");
			// client2.setEnabled(true);
			// usuarioDAO.save(client2);
		};
	}

}
