package br.com.alura.loja.service.client;

import br.com.alura.loja.controller.dto.InfoEntregaDTO;
import br.com.alura.loja.controller.dto.VoucherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("transportador")
@Component
public interface TransportadorClient {

    @PostMapping("/entrega")
    VoucherDTO reservaEntrega(InfoEntregaDTO entregaDTO);

}
