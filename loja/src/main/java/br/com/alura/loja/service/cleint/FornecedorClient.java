package br.com.alura.loja.service.cleint;

import br.com.alura.loja.controller.dto.InfoFornecedorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("fornecedor")
public interface FornecedorClient {

    @GetMapping("/info/{estado}")
    InfoFornecedorDTO getInfoPorEstado(@PathVariable("estado") String estado);

}
