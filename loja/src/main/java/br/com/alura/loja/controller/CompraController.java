package br.com.alura.loja.controller;

import br.com.alura.loja.controller.dto.CompraDTO;
import br.com.alura.loja.modelo.Compra;
import br.com.alura.loja.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraService service;

    @PostMapping
    public Compra realizaCompra(@RequestBody CompraDTO compra) {

        return service.realizeCompra(compra);
    }

    @GetMapping("/{id}")
    public Compra getById(@PathVariable Long id) {
        return service.getById(id);
    }

}
