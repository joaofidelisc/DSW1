package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
//import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//import br.ufscar.dsw.profissionais.domain.AbstractEntity;

@Entity
@Table(name = "User")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends AbstractEntity<Long> {
    
    @NotBlank
	@Size(min = 11, max = 14)
	@Column(nullable = false, unique = true, length = 60)
	private String cpf;

    @Column(nullable = false, length = 45)
    private String username;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = false, length = 240)
    private String password;
    
    @Column(nullable = false, length = 45)
    private String role;

	@Column(nullable = false)
    private boolean enabled;

    public String getCpf() {
		return cpf;
	}

	public void setCpf(String Cpf) {
		this.cpf = Cpf;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	

}