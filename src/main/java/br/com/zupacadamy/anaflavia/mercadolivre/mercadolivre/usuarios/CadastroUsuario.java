package br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.usuarios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class CadastroUsuario implements UserDetails {
	
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
	
	
	 @ManyToMany(fetch = FetchType.EAGER)
	  private List<Perfil> perfis = new ArrayList<>();
	
	@Deprecated
	public CadastroUsuario() {
		
	}


	public CadastroUsuario(@NotBlank @Email String login, @NotBlank @Length(min = 6) String senha)
	{
		this.login = login;
		this.senha = new BCryptPasswordEncoder().encode(senha);
		
	}


	public Long getId() {
		return id;
	}


	public String getLogin() {
		return login;
	}


	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return perfis;
	}


	@Override
	public String getPassword() {
		return senha;
	}


	@Override
	public String getUsername() {
		return login;
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	
	
	
	

}
