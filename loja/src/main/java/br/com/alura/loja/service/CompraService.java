package br.com.alura.loja.service;

import br.com.alura.loja.controller.dto.*;
import br.com.alura.loja.modelo.Compra;
import br.com.alura.loja.modelo.CompraState;
import br.com.alura.loja.repository.CompraRepository;
import br.com.alura.loja.service.client.FornecedorClient;
import br.com.alura.loja.service.client.TransportadorClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CompraService {

    final Logger LOG = LoggerFactory.getLogger(CompraService.class);

    @Autowired
    private FornecedorClient fornecedorClient;

    @Autowired
    private TransportadorClient transportadorClient;

    @Autowired
    private CompraRepository compraRepository;

    @HystrixCommand(threadPoolKey = "getByIdThreadPool")
    public Compra getById(Long id) {
        return compraRepository.findById(id).orElse(new Compra());
    }

    @HystrixCommand(fallbackMethod = "realizaCompraFallback", threadPoolKey = "realizaCompraThreadPool")
    public Compra realizeCompra(CompraDTO compra) {
        Compra compraSalva = new Compra();
        compraSalva.setCompraState(CompraState.RECEBIDO);
        compraSalva.setEnderecoDestino(compra.getEndereco().toString());
        compraRepository.save(compraSalva);
        compra.setId(compraSalva.getId());

        LOG.info("buscando informações do fornecedor de {}", compra.getEndereco().getEstado());
        InfoFornecedorDTO infoPorEstado = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());

        LOG.info("realizando um pedido");
        InfoPedidoDTO pedido = fornecedorClient.realizaPedido(compra.getItens());
        compraSalva.setPedidoId(pedido.getId());
        compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());
        compraSalva.setCompraState(CompraState.PEDIDO_REALIZADO);
        compraRepository.save(compraSalva);

        InfoEntregaDTO entregaDTO = new InfoEntregaDTO();
        entregaDTO.setPedidoId(pedido.getId());
        entregaDTO.setDataParaEntrega(LocalDate.now().plusDays(pedido.getTempoDePreparo()));
        entregaDTO.setEnderecoOrigem(infoPorEstado.getEndereco());
        entregaDTO.setEnderecoDestino(compra.getEndereco().toString());
        VoucherDTO voucher = transportadorClient.reservaEntrega(entregaDTO);
        compraSalva.setCompraState(CompraState.RESERVA_ENTREGA_REALIZADA);
        compraSalva.setDataParaEntrega(voucher.getPrevisaoParaEntrega());
        compraSalva.setVoucher(voucher.getNumero());
        compraRepository.save(compraSalva);

//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        return compraSalva;
    }

    public Compra realizaCompraFallback(CompraDTO compraDTO) {
        if (compraDTO.getId() != null) {
            return compraRepository.findById(compraDTO.getId()).get();
        }
        Compra compraFallback = new Compra();
        compraFallback.setEnderecoDestino(compraDTO.getEndereco().toString());
        return compraFallback;
    }

}
