package agenda.repository;

import agenda.entity.Assistencia;
import agenda.entity.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface AssistenciaRepository extends JpaRepository<Assistencia, Long> {

    @Query(value = "SELECT horario FROM agenda", nativeQuery = true)
    public List<Time> mostrarTodosOsHorarios ();

    @Query("SELECT s FROM Solicitacao s WHERE s.assistencia_id.id = :assistencia_id " +
            "AND s.dia_agendado LIKE CONCAT(:dia_agendado,'%')")
    public List<Solicitacao> mostrarTodosOsAgendamentos (
            @Param("assistencia_id") Long assistencia_id, @Param("dia_agendado")Date dia_agendado
            );

    @Query(value = "SELECT horario FROM agenda WHERE horario NOT IN (SELECT hora_agendada FROM solicitacao WHERE assistencia_id = :assistencia_id " +
            "AND dia_agendado LIKE :dia_agendado" + "%)", nativeQuery = true)
    public List<Time> mostrarTodosOsHorariosLivres (
            @Param("assistencia_id") Long assistencia_id, @Param("dia_agendado")Date dia_agendado
            );

    @Query("SELECT hora_agendada FROM Solicitacao s WHERE s.assistencia_id.id = :assistencia_id " +
            "AND s.dia_agendado LIKE CONCAT(:dia_agendado,'%')")
    public List<Time> mostrarTodosOsHorariosOcupados (
            @Param("assistencia_id") Long assistencia_id, @Param("dia_agendado")Date dia_agendado
    );
}


