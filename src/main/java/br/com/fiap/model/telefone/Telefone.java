package br.com.fiap.model.telefone;

import br.com.fiap.model.cliente.DadosInsercaoClienteDTO;

public class Telefone {

    private Long id;

    private String numero;
    private int ddd;
    private String observacoes;
    private TipoTelefone tipoTelefone;

    public Telefone(DadosInsercaoTelefone dados){
        this.ddd = dados.ddd();
        this.numero = dados.numero();
        this.tipoTelefone = dados.tipoTelefone();
    }

    //Construtor para resposta do banco


    public Telefone(Long id, String numero, int ddd, String observacoes, TipoTelefone tipoTelefone) {
        this.id = id;
        this.numero = numero;
        this.ddd = ddd;
        this.observacoes = observacoes;
        this.tipoTelefone = tipoTelefone;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero){this.numero = numero;}
    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public TipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(TipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }
}
