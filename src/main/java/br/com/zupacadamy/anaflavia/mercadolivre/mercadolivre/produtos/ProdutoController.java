package br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.produtos;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.categorias.CategoriaRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@PostMapping
	public ResponseEntity cadastrar(@RequestBody @Valid ProdutoDto produtoDto, UriComponentsBuilder uriBuilder) {
		Produto produto = produtoDto.converter(categoriaRepository);
		if (produto != null) {
			produtoRepository.save(produto);

			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
