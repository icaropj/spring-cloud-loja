package br.com.alura.loja.service;

import br.com.alura.loja.controller.dto.CompraDTO;
import br.com.alura.loja.controller.dto.InfoFornecedorDTO;
import br.com.alura.loja.controller.dto.InfoPedidoDTO;
import br.com.alura.loja.modelo.Compra;
import br.com.alura.loja.service.client.FornecedorClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

    final Logger LOG = LoggerFactory.getLogger(CompraService.class);

    @Autowired
    private FornecedorClient fornecedorClient;

    @HystrixCommand(fallbackMethod = "realizaCompraFallback")
    public Compra realizeCompra(CompraDTO compra) {
        LOG.info("buscando informações do fornecedor de {}", compra.getEndereco().getEstado());
        InfoFornecedorDTO infoPorEstado = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());

        LOG.info("realizando um pedido");
        InfoPedidoDTO pedido = fornecedorClient.realizaPedido(compra.getItens());

        Compra compraSalva = new Compra();
        compraSalva.setPedidoId(pedido.getId());
        compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());
        compraSalva.setEnderecoDestino(compra.getEndereco().toString());



//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        return compraSalva;
    }

    public Compra realizaCompraFallback(CompraDTO compraDTO) {
        Compra compraFallback = new Compra();
        compraFallback.setEnderecoDestino(compraDTO.getEndereco().toString());
        return compraFallback;
    }

}
