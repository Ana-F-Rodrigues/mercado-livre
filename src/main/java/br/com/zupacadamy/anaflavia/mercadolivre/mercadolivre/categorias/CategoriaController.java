package br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.categorias;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	

	@Autowired
	private CategoriaRepository categoriaRepository;
	@PostMapping
	public ResponseEntity<CategoriaDto> Cadastrar (@RequestBody @Valid CategoriaDto categoriaDto) {
		
		Categoria categoria = categoriaDto.converter(categoriaRepository);
		categoria = categoriaRepository.save(categoria);
		return ResponseEntity.ok().body(categoriaDto);
}

}
