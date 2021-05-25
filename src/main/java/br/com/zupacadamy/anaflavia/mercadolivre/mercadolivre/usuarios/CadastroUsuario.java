package br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.usuarios;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class CadastroUsuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Email
	private String login;
	
	@NotBlank
    @Length(min = 6)
	private String senha;
	
	@PastOrPresent
	@NotNull
	private LocalDateTime dataCadastro = LocalDateTime.now();
	
	@Deprecated
	public CadastroUsuario() {
		
	}


	public CadastroUsuario(@NotBlank @Email String login, @NotBlank @Length(min = 6) String senha)
	{
		this.login = login;
		this.senha = new BCryptPasswordEncoder().encode(senha);
		
	}
	
	
	
	

}
