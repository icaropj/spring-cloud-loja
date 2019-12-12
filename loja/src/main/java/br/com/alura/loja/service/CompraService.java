package br.com.alura.loja.service;

import br.com.alura.loja.controller.dto.CompraDTO;
import br.com.alura.loja.controller.dto.InfoFornecedorDTO;
import br.com.alura.loja.service.cleint.FornecedorClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

    @Autowired
    private FornecedorClient fornecedorClient;

    public void realizeCompra(CompraDTO compra) {
        InfoFornecedorDTO infoPorEstado = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
        System.out.println(infoPorEstado.getEndereco());
    }

}
