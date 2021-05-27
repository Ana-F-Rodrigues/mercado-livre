package br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.usuarios.token;



import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.usuarios.CadastroUsuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class Token {
	
	 @Value("${mercadolivre.jwt.expiration}")
	    private String expiration;

	    @Value("${mercadolivre.jwt.secret}")
	    private String secret;

	    public String geraToken(Authentication authentication) {
	        CadastroUsuario logado = (CadastroUsuario) authentication.getPrincipal();
	        Date agora = new Date();
	        Date dataExpiracao = new Date(agora.getTime() + Long.parseLong(expiration));

	        return Jwts.builder()
	                .setIssuer("API do Mercado Livre")
	                .setSubject(logado.getId().toString())
	                .setIssuedAt(agora)
	                .setExpiration(dataExpiracao)
	                .signWith(SignatureAlgorithm.HS256, secret)
	                .compact();
	    }

	    public boolean isTokenValid(String token){
	        try {
	            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
	            return true;
	        } catch(Exception e) {
	            return false;
	        }
	    }

	    public Long getIdDoUsuarioDoToken(String token) {
	        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
	        return Long.parseLong(claims.getSubject());
	    }

}
