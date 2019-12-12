package br.com.alura.fornecedor.service;

import br.com.alura.fornecedor.model.InfoFornecedor;
import br.com.alura.fornecedor.repository.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

    @Autowired
    private InfoRepository repository;

    public InfoFornecedor getInfoPorEstado(String estado) {
        return repository.findByEstado(estado);
    }

}
