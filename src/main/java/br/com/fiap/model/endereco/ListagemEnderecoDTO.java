package br.com.fiap.model.endereco;

public record ListagemEnderecoDTO (
        String estado,
        String pais
        ){

        public ListagemEnderecoDTO(String estado, String pais) {
                this.estado = estado;
                this.pais = pais;
        }
}
