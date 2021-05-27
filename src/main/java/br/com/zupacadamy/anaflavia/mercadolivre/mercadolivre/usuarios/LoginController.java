package br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.usuarios;

import org.springframework.security.core.AuthenticationException;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.usuarios.token.Token;
import br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.usuarios.token.TokenDto;

@RestController
public class LoginController {

	    private final AuthenticationManager authenticationManager;
	    private final Token tokenComponent;

	    public LoginController(AuthenticationManager authenticationManager, Token tokenComponent) {
	        this.authenticationManager = authenticationManager;
	        this.tokenComponent = tokenComponent;
	    }

	    @PostMapping(value = "/login")
	    public ResponseEntity<TokenDto> autentica(@RequestBody @Valid LoginDto request) {
	        UsernamePasswordAuthenticationToken login = request.converte();

	        try{
	            Authentication authentication = authenticationManager.authenticate(login);
	            String token = tokenComponent.geraToken(authentication);
	            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
	        } catch(AuthenticationException e) {
	            return ResponseEntity.badRequest().build();
	        }
	    }
	
}
