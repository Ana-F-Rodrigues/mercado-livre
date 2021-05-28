package br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.usuarios.BuscaUser;
import br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.usuarios.UsuarioRepository;
import br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.usuarios.token.Token;


@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	BuscaUser buscaUser;
	@Autowired
	Token token;
  
	    @Bean
	    @Override
	    protected AuthenticationManager authenticationManager() throws Exception{
	        return super.authenticationManager();
	    }

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 BCryptPasswordEncoder bCryptPassword = new  BCryptPasswordEncoder();
		 auth.userDetailsService(buscaUser)
		 .passwordEncoder(bCryptPassword);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers(HttpMethod.POST,"/categorias").permitAll()
        .antMatchers(HttpMethod.POST,"/produtos").permitAll()
        .antMatchers(HttpMethod.POST,"/usuarios").permitAll()
        .antMatchers(HttpMethod.POST,"/login").permitAll()
        .anyRequest().authenticated()
        .and().cors()
        .and().csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().addFilterBefore(
                new JwtFilter(token, usuarioRepository),
        UsernamePasswordAuthenticationFilter.class);
}

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
	
}
