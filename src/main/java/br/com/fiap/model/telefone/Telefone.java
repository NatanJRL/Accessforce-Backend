package br.com.fiap.model.telefone;

public class Telefone {
    private String numero;
    private int ddd;
    private String observacoes;
    private TipoTelefone tipoTelefone;

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
