package br.com.fiap.model.usuario;

import br.com.fiap.model.endereco.Endereco;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Usuario {
    private Long id;
    private String email;
    private String senha;
    private LocalDate dataDeRegistro;
    private String nomeCompleto;
    private LocalDate dataNascimento;
    private Endereco endereco;
    public Usuario(){}

    public Usuario(String email, String senha, String nomeCompleto,String dataNascimento, Endereco endereco){
        this.email = email;
        this.senha = senha;
        this.nomeCompleto = nomeCompleto;
        this.dataDeRegistro = LocalDate.now();
        this.dataNascimento = formataDataSQL(dataNascimento);
        this.endereco = endereco;
    }
    public Usuario(Long id, String email, String senha, String nomeCompleto, String dataRegistro, String dataNascimento, Endereco endereco){
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.nomeCompleto = nomeCompleto;
        this.dataDeRegistro = formataDataSQL(dataRegistro);
        this.dataNascimento = formataDataSQL(dataNascimento);
        this.endereco = endereco;
    }


    public static LocalDate formataData(String data){
        if (data == null || data.isBlank()){
            throw new IllegalArgumentException("Data inválida");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(data, formatter);

    }

    private LocalDate formataDataSQL(String data) {
        if (data == null || data.isBlank()){
            throw new IllegalArgumentException("Data inválida");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(data, formatter);
    }
    public static boolean validaCep(String cep){
        int tamanhoCep = cep.length();
        return tamanhoCep == 8;
    }
    public static boolean validaData(String data){
        try{
            formataData(data);
            return true;
        }catch(DateTimeParseException ex){
            System.out.println("Data inválida.");
            return false;
        }
    }

    private int retornaIdade(){
        LocalDate dataAtual = LocalDate.now();
        Period periodo = Period.between(dataNascimento, dataAtual);
        return periodo.getYears();
    }



    @Override
    public String toString() {
        return "--------------------------------------------------" + "\n" +
                "Usuario:\n " +
                " Id: '" + this.getId() + '\'' + "\n " +
                " Nome: '" + nomeCompleto + '\'' + "\n " +
                " Idade: '" + retornaIdade() + " anos" + '\'' + "\n " +
                " Email: '" + email + '\'' + "\n " +
                " Data de registro: '" + this.dataDeRegistro.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + '\'';
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDate getDataDeRegistro() {
        return dataDeRegistro;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setDataDeRegistro(LocalDate dataDeRegistro) {
        this.dataDeRegistro = dataDeRegistro;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}

