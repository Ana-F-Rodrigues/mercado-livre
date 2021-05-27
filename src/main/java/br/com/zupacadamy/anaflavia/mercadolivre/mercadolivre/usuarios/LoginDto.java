package br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.usuarios;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginDto {
	
	 private String login;
	    private String senha;

	    public LoginDto(String login, String senha) {
	        this.login = login;
	        this.senha = senha;
	    }

	    public UsernamePasswordAuthenticationToken converte() {
	        return new UsernamePasswordAuthenticationToken(login, senha);
	    }

	    public String getLogin() {
	        return login;
	    }

	    public String getSenha() {
	        return senha;
	    }

}
