package agenda.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "agenda_tecnica", name = "equipamento",
        uniqueConstraints = { @UniqueConstraint(name = "UNIQUE_NAME", columnNames = "tipo_equipamento") })
public class Equipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "tipo_equipamento", nullable = false, length = 100)
    private String name;
    public List<Solicitacao> getSolicitacoes() {
        return solicitacoes;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "equipamento")
    private List<Solicitacao> solicitacoes;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipamento that = (Equipamento) o;
        return id.equals(that.id) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Equipamento() {
    }

    public Equipamento(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
