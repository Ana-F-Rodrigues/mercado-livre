package br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.usuarios;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<CadastroUsuario, Long> {
	
  Optional<CadastroUsuario> findByLogin(String userName);



}
