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

			/*User user = new User();
            user.setCpf("44071481870");
			user.setUsername("sara@mail.com");
			user.setName("Sara");
			user.setPassword("123");
			user.setRole("ADMIN");
			user.setEnabled(true);
			usuarioDAO.save(user);

            Cliente clt = new Cliente();
            clt.setConsultas(null);
            clt.setCpf("44071481870");
            clt.setEnabled(true);
            clt.setGenero("M");
            clt.setName("Luiz");
            clt.setPassword(encoder.encode("123"));
            clt.setRole("ROLE_ADMIN");
            clt.setTelefone("149984424121");
            clt.setUsername("luiz@mail.com");
            clt.setdataNascimento("16/01/2000");
            usuarioDAO.save(clt);

	// 		byte[] ba2 = {1,2,3,4,5};
	// 		Professional professional1 = new Professional();
	// 		professional1.setUsername("luiz@mail.com");
	// 		professional1.setName("Luiz Felipe");
	// 		professional1.setPassword(encoder.encode("123"));
	// 		professional1.setRole("PROF");
	// 		professional1.setCpf("255255255127");
	// 		professional1.setEmail("luiz@mail.com");
	// 		professional1.setExpertise("Cardiologista");
	// 		professional1.setKnowledgeArea("Medicina");
	// 		professional1.setQualifications(ba2);
	// 		professional1.setEnabled(true);
	// 		professional1.setFilename("teste");
	// 		usuarioDAO.save(professional1);

	// 		Professional professional2 = new Professional();
	// 		professional2.setUsername("italo@mail.com");
	// 		professional2.setName("Italo Ribeiro");
	// 		professional2.setPassword(encoder.encode("123"));
	// 		professional2.setRole("PROF");
	// 		professional2.setCpf("127127127255");
	// 		professional2.setEmail("italo@mail.com");
	// 		professional2.setExpertise("Psicologia");
	// 		professional2.setKnowledgeArea("Psicologia");
	// 		professional2.setEnabled(true);
	// 		professional2.setFilename("teste");
	// 		usuarioDAO.save(professional2);
			
			Cliente client1 = new Cliente();
			client1.setUsername("joao@mail.com");
			client1.setName("João Vitor Fidelis Cardozo");
			client1.setPassword(encoder.encode("123"));
			client1.setRole("ROLE_CLIENTE");
			client1.setCpf("41438691874");
			client1.setTelefone("16999922222");
			client1.setGenero("M");
			client1.setdataNascimento("1998-06-10");
			client1.setEnabled(true);
			usuarioDAO.save(client1);
			
			Cliente client2 = new Cliente();
			client2.setUsername("matteus@mail.com");
			client2.setName("Matteus Souza");
			client2.setPassword(encoder.encode("123"));
			client2.setRole("ROLE_PROF");
			client2.setCpf("11111111111111");
			client2.setTelefone("99999999999");
			client2.setGenero("M");
			client2.setdataNascimento("1999-05-04");
			client2.setEnabled(true);
			usuarioDAO.save(client2);*/
		};
	}

}
