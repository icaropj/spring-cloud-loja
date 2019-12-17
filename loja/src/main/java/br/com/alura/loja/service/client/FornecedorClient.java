package br.com.alura.loja.service.client;

import br.com.alura.loja.controller.dto.InfoFornecedorDTO;
import br.com.alura.loja.controller.dto.InfoPedidoDTO;
import br.com.alura.loja.controller.dto.ItemCompraDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient("fornecedor")
@Component
public interface FornecedorClient {

    @GetMapping("/info/{estado}")
    InfoFornecedorDTO getInfoPorEstado(@PathVariable("estado") String estado);

    @PostMapping("/pedido")
    InfoPedidoDTO realizaPedido(List<ItemCompraDTO> itens);
}
