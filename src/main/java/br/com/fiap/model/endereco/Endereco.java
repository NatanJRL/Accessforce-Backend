package br.com.fiap.model.endereco;

public class Endereco {
    private String cep;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;

    public Endereco(String cep, String rua, String bairro, String cidade, String estado, String pais) {
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }
    public Endereco(DadosInsercaoEnderecoDTO enderecoDTO){
        if (enderecoDTO.cep() != null){
            this.cep = enderecoDTO.cep();
        }
        if (enderecoDTO.rua() != null){
            this.rua = enderecoDTO.rua();
        }
        if (enderecoDTO.bairro() != null){
            this.rua = enderecoDTO.bairro();
        }
        if (enderecoDTO.cidade() != null){
            this.rua = enderecoDTO.cidade();
        }
        this.estado = enderecoDTO.estado();

        this.pais = enderecoDTO.pais();
    }
    public Endereco(String estado, String pais){
        this.estado = estado;
        this.pais = pais;
    }
    public Endereco(){}

    public String getCep() {
        return cep;
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getPais() {
        return pais;
    }

}

