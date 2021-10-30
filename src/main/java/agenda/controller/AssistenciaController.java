package agenda.controller;
import agenda.entity.Assistencia;
import agenda.entity.Solicitacao;
import agenda.service.AssistenciaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping ("/assistencia")
public class AssistenciaController {

    @Autowired
    private AssistenciaServices assistenciaServices;

    @GetMapping({"/",""})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Assistencia>> getAllAssistencia(){
        return ResponseEntity.ok(assistenciaServices.getAssistenciaList());
    }

    @GetMapping({"/horarios","/horarios/"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Time>> mostrarTodosOsHorarios(@PathVariable Long assistencia_id, @PathVariable Date dia_agendamento) {
        return ResponseEntity.ok(assistenciaServices.horariosLivresNoDia(assistencia_id, dia_agendamento));
    }

    @GetMapping("/horariosOcupados/{assistencia_id}/{dia_agendado}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Solicitacao>> horariosOcupadosNoDia(@PathVariable Long assistencia_id, @PathVariable Date dia_agendado){
    
        return ResponseEntity.ok(assistenciaServices.horariosOcupadosNoDia(assistencia_id, dia_agendado));
    }

    @GetMapping("/horariosLivres/{assistencia_id}/{dia_agendado}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Time>> horariosLivresNoDia(@PathVariable Long assistencia_id, @PathVariable Date dia_agendado) {
        return ResponseEntity.ok(assistenciaServices.horariosLivresNoDia(assistencia_id, dia_agendado));
    }



}

