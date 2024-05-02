package br.com.fiap.model.empresa;

import br.com.fiap.model.endereco.Endereco;

public class Empresa {
    private Long id;
    private String cnpj;
    private String nome;
    private TamanhoEmpresa tamanhoEmpresa;
    private Endereco endereco;

    public Empresa(String cnpj, String nome, TamanhoEmpresa tamanhoEmpresa, Endereco endereco){
        this.cnpj = cnpj;
        this.nome = nome;
        this.tamanhoEmpresa = tamanhoEmpresa;
        this.endereco = endereco;
    }


}