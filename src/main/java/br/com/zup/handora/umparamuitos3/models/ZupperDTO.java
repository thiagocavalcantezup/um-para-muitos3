package br.com.zup.handora.umparamuitos3.models;

import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ZupperDTO {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    @NotEmpty
    @Size(min = 2)
    private Set<EnderecoDTO> enderecos;

    public ZupperDTO(@NotBlank String nome, @NotBlank @Email String email, Cargo cargo) {
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public Set<EnderecoDTO> getEnderecos() {
        return enderecos;
    }

}
