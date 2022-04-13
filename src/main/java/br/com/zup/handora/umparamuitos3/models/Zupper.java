package br.com.zup.handora.umparamuitos3.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name = "zuppers")
public class Zupper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private Cargo cargo;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "zupper_id")
    private Set<Endereco> enderecos = new HashSet<>();

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Zupper() {}

    public Zupper(String nome, @Email String email, Cargo cargo) {
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
    }

    public void adicionar(Endereco endereco) {
        enderecos.add(endereco);
    }

    public Long getId() {
        return id;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

}
