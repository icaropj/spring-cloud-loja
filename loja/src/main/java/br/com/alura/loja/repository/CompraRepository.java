package br.com.alura.loja.repository;

import br.com.alura.loja.modelo.Compra;
import org.springframework.data.repository.CrudRepository;

public interface CompraRepository extends CrudRepository<Compra, Long> {
}
