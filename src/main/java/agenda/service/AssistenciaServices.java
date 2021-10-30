package agenda.service;


import agenda.entity.Assistencia;
import agenda.entity.Solicitacao;
import agenda.repository.AssistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Service
public class AssistenciaServices {

    @Autowired
    private AssistenciaRepository assistenciaRepository;

    @Transactional
    public List<Assistencia> criarListaAssistencia(List<Assistencia> list) {
        return assistenciaRepository.saveAll(list);

    }

    @Transactional
    public List<Assistencia> getAssistenciaList() {
        return assistenciaRepository.findAll();
    }



    public List<Solicitacao> horariosOcupadosNoDia(Long assistencia_id, Date dia_agendado) {
        return assistenciaRepository.mostrarTodosOsAgendamentos(assistencia_id, dia_agendado);
    }


    public List<Time> horariosLivresNoDia(Long assistencia_id, Date dia_agendado) {

        Optional<Assistencia> assistencia = assistenciaRepository.findById(assistencia_id);

        return assistenciaRepository.mostrarTodosOsHorariosLivres(assistencia_id, dia_agendado);
    }
}
