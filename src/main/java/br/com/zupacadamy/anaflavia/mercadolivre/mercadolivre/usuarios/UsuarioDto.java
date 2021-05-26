package br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.usuarios;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.usuarios.validacoes.UniqueValue;


public class UsuarioDto {
	
	@NotBlank
    @Email
    @UniqueValue(domainClass = CadastroUsuario.class, fieldName= "login")
    private String login;

    @NotBlank
    @Length(min = 6)
    private String senha;

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }


    
	
	public UsuarioDto(@NotBlank @Email String login, @NotBlank @Length(min = 6) String senha)
	{
		this.login = login;
		this.senha = senha;
	}

	public CadastroUsuario converter()
	{
		 CadastroUsuario cadastroUsuario = new CadastroUsuario(this.login, this.senha);
		 return cadastroUsuario;
	}

	

}
