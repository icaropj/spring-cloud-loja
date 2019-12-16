package br.com.alura.loja.service;

import br.com.alura.loja.controller.dto.CompraDTO;
import br.com.alura.loja.controller.dto.InfoFornecedorDTO;
import br.com.alura.loja.controller.dto.InfoPedidoDTO;
import br.com.alura.loja.modelo.Compra;
import br.com.alura.loja.service.cleint.FornecedorClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

    @Autowired
    private FornecedorClient fornecedorClient;

    public Compra realizeCompra(CompraDTO compra) {
        InfoFornecedorDTO infoPorEstado = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
        InfoPedidoDTO pedido = fornecedorClient.realizaPedido(compra.getItens());
        System.out.println(infoPorEstado.getEndereco());

        Compra compraSalva = new Compra();
        compraSalva.setPedidoId(pedido.getId());
        compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());
        compraSalva.setEnderecoDestino(compra.getEndereco().toString());
        return compraSalva;
    }

}
