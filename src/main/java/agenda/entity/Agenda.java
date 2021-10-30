package agenda.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(schema = "agenda_tecnica", name = "agenda")
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "horario", nullable = false)
    private Time horario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getHorario() {
        return horario;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agenda agenda = (Agenda) o;
        return id.equals(agenda.id) && horario.equals(agenda.horario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, horario);
    }

    public Agenda() {
    }

    public Agenda(Long id, Time horario) {
        this.id = id;
        this.horario = horario;
    }
}
