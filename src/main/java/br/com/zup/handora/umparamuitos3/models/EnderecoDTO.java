package br.com.zup.handora.umparamuitos3.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EnderecoDTO {

    @NotBlank
    private String logradouro;

    @NotBlank
    private String numero;

    @NotBlank
    private String bairro;

    private String complemento;

    @NotBlank
    private String cidade;

    @NotBlank
    private String estado;

    @NotBlank
    @Pattern(regexp = "^[0-9]{5}(-|)[0-9]{3}$")
    @Size(min = 8, max = 9)
    private String cep;

    public EnderecoDTO(@NotBlank String logradouro, @NotBlank String numero,
                       @NotBlank String bairro, String complemento, @NotBlank String cidade,
                       @NotBlank String estado, @NotBlank String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public Endereco toModel() {
        String novoCep = cep.replaceAll("[^0-9]", "");

        return new Endereco(logradouro, numero, bairro, complemento, cidade, estado, novoCep);
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCep() {
        return cep;
    }

}
