package br.com.alura.loja.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class CompraDTO {

    @JsonIgnore
    private Long id;

    private List<ItemCompraDTO> itens = new ArrayList<>();

    private EnderecoDTO endereco;

    public List<ItemCompraDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemCompraDTO> itens) {
        this.itens = itens;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
