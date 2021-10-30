package agenda.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "agenda_tecnica", name = "assistencia",
        uniqueConstraints = { @UniqueConstraint(name = "UNIQUE_NAME", columnNames = "nome") })
public class Assistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "assistencia_id")
    private List<Solicitacao> solicitacoes;


    public Assistencia(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Assistencia() {
    }


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
        Assistencia that = (Assistencia) o;
        return id.equals(that.id) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}