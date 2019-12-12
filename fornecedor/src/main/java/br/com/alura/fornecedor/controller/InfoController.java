package br.com.alura.fornecedor.controller;

import br.com.alura.fornecedor.model.InfoFornecedor;
import br.com.alura.fornecedor.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {

    @Autowired
    private InfoService service;

    @GetMapping("/{estado}")
    public InfoFornecedor getInfoPorEstado(@PathVariable String estado) {
        return service.getInfoPorEstado(estado);
    }

}
