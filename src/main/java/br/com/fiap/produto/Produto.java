package br.com.fiap.produto;

import java.math.BigDecimal;

public class Produto {
    private Long id;
    private String nome;
    private BigDecimal valor;
    private String descricao;

    public Produto(String nome, BigDecimal valor, String descricao){
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }
}
