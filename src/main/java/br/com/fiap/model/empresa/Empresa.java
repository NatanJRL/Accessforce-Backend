package br.com.fiap.model.empresa;

import br.com.fiap.model.endereco.Endereco;

public class Empresa {
    private Long id;
    private String nome;
    private TamanhoEmpresa tamanhoEmpresa;

    public Empresa(String nome, TamanhoEmpresa tamanhoEmpresa){
        this.nome = nome;
        this.tamanhoEmpresa = tamanhoEmpresa;
    }

    public Empresa(DadosInsercaoEmpresaDTO dados){
        this.nome = dados.nome();
        this.tamanhoEmpresa = dados.tamanhoEmpresa();
    }
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TamanhoEmpresa getTamanhoEmpresa() {
        return tamanhoEmpresa;
    }

    public void setTamanhoEmpresa(TamanhoEmpresa tamanhoEmpresa) {
        this.tamanhoEmpresa = tamanhoEmpresa;
    }
}