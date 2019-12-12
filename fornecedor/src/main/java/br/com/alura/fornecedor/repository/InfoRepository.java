package br.com.alura.fornecedor.repository;

import br.com.alura.fornecedor.model.InfoFornecedor;
import org.springframework.data.repository.CrudRepository;

public interface InfoRepository extends CrudRepository<InfoFornecedor, Long> {

    InfoFornecedor findByEstado(String estado);

}
