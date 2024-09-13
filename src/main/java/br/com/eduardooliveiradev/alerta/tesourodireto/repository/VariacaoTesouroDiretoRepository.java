package br.com.eduardooliveiradev.alerta.tesourodireto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eduardooliveiradev.alerta.tesourodireto.model.VariacaoTesouroDireto;

@Repository
public interface VariacaoTesouroDiretoRepository extends JpaRepository<VariacaoTesouroDireto, Long>{

	VariacaoTesouroDireto findByTituloIgnoreCase(String titulo);

}
