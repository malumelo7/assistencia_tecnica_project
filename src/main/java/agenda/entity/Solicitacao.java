package agenda.entity;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.sql.Date;


@Entity
@Table(schema = "agenda_tecnica", name = "Solicitacao")
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "hora_agora")
    private LocalDateTime hora_agora = LocalDateTime.now();

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "dia_agendado", nullable = false)
    private Date dia_agendado;

    @Column(name = "hora_agendada", nullable = false)
    private Time hora_agendada;

    @JoinColumn(name = "assistencia_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_solicitacao_assistencia"))

    @ManyToOne
    private Assistencia assistencia_id;

    @JoinColumn(name = "cliente_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_solicitacao_cliente"))

    @ManyToOne
    private Cliente cliente_id;


    @JoinColumn(name = "equipamento_id", nullable = true, referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_solicitacao_equipamento"))

    @ManyToOne
    private Equipamento equipamento;

    public Solicitacao(Long id, String descricao, LocalDateTime hora_agora, Date dia_agendado, Time hora_agendada, Assistencia assistencia_id, Cliente cliente_id, Equipamento equipamento) {
        this.id = id;
        this.descricao = descricao;
        this.hora_agora = hora_agora;
        this.dia_agendado = dia_agendado;
        this.hora_agendada = hora_agendada;
        this.assistencia_id = assistencia_id;
        this.cliente_id = cliente_id;
        this.equipamento = equipamento;
    }

    public Solicitacao(String descricao, LocalDateTime hora_agora, Date dia_agendado, Time hora_agendada, Assistencia assistencia_id, Cliente cliente_id, Equipamento equipamento) {
        
        this.descricao = descricao;
        this.hora_agora = hora_agora;
        this.dia_agendado = dia_agendado;
        this.hora_agendada = hora_agendada;
        this.assistencia_id = assistencia_id;
        this.cliente_id = cliente_id;
        this.equipamento = equipamento;
    }

    public Solicitacao() {
    }

    public Assistencia getAssistencia_id() {
        return assistencia_id;
    }

    public void setAssistencia_id(Assistencia assistencia_id) {
        this.assistencia_id = assistencia_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setHora_agora(LocalDateTime hora_agora) {
        this.hora_agora = hora_agora;
    }

    public void setDia_agendado(Date dia_agendado) {
        this.dia_agendado = dia_agendado;
    }

    public void setHora_agendada(Time hora_agendada) {
        this.hora_agendada = hora_agendada;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }


    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getHora_agora() {
        return hora_agora;
    }

    public Date getDia_agendado() {
        return dia_agendado;
    }

    public Time getHora_agendada() {
        return hora_agendada;
    }

    public Cliente getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Cliente cliente_id) {
        this.cliente_id = cliente_id;
    }
    public Equipamento getEquipamento() {
        return equipamento;
    }

}
