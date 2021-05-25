package br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.usuarios;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@PostMapping
	public ResponseEntity<UsuarioDto> Cadastrar (@RequestBody @Valid UsuarioDto usuarioDto) {
		
		CadastroUsuario cadastroUsuario = usuarioDto.converter();
		cadastroUsuario = usuarioRepository.save(cadastroUsuario);
		return ResponseEntity.ok().body(usuarioDto);
}

}
