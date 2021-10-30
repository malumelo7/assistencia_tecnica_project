package agenda.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "agenda_tecnica", name = "cliente",
        uniqueConstraints = { @UniqueConstraint(name = "UNIQUE_CPF", columnNames = "cpf") })
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "assistencia_id")
    private List<Solicitacao> solicitacoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id) && nome.equals(cliente.nome) && cpf.equals(cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cpf);
    }

    public Cliente() {
    }

    public Cliente(Long id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public Cliente(Long id) {
        this.id = id;
        this.nome = null;
        this.cpf = null;
    }
    public Cliente(String cpf) {
        this.id = null;
        this.nome = null;
        this.cpf = cpf;
    }

    public Cliente(String nome, String cpf) {
        this.id = null;
        this.nome = nome;
        this.cpf = cpf;
    }
    
}
