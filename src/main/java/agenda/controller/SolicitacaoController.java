package agenda.controller;

import agenda.entity.Solicitacao;
import agenda.exception.*;
import agenda.exception.CpfInvalidoException;
import agenda.exception.EquipamentoInvalidoException;
import agenda.exception.ForaHorarioComercialException;
import agenda.service.SolicitacaoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/solicitacao")
public class SolicitacaoController {

    @Autowired
    private SolicitacaoServices solicitacaoServices;

    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map errorHandler(DataIntegrityViolationException ex) {
        Map map = new HashMap();
        map.put("Erro número: ", 422);
        map.put("Causa: ","A assistência com este ID não existe!");
        return map;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map errorHandler(HttpMessageNotReadableException ex) {
        Map map = new HashMap();
        map.put("Erro número: ", 400);
        map.put("Causa: ","Data inválida.");
        return map;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ForaHorarioComercialException.class)
    public Map errorHandler(ForaHorarioComercialException ex) {
        Map map = new HashMap();
        map.put("Erro número: ", 500);
        map.put("Causa: ","Horário inválido ou fora do horário comercial.");
        return map;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EquipamentoInvalidoException.class)
    public Map errorHandler(EquipamentoInvalidoException ex) {
        Map map = new HashMap();
        map.put("Erro número: ", 500);
        map.put("Causa: ","Nossas assistências não consertam esse tipo de equipamento.");
        return map;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HorarioOcupadoException.class)
    public Map errorHandler(HorarioOcupadoException ex) {
        Map map = new HashMap();
        map.put("Erro número: ", 500);
        map.put("Causa: ","Este horário não está disponível.");
        return map;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EntityNotFoundException.class)
    public Map errorHandler(EntityNotFoundException ex) {
        Map map = new HashMap();
        map.put("Erro número: ", 500);
        map.put("Causa: ","Id de cliente não encontrado.");
        return map;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CpfInvalidoException.class)
    public Map errorHandler(CpfInvalidoException ex) {
        Map map = new HashMap();
        map.put("Erro número: ", 500);
        map.put("Causa: ","CPF inválido.");
        return map;
    }

    @PostMapping({"","/"})
    @ResponseStatus(HttpStatus.CREATED)

    public ResponseEntity<Solicitacao> salvarSolicitacao(@RequestBody Solicitacao solicitacao) throws EquipamentoInvalidoException, CpfInvalidoException, ForaHorarioComercialException, ParseException, HorarioOcupadoException, IdNaoEncontradoException {

        return ResponseEntity.ok(this.solicitacaoServices.salvarSolicitacao(solicitacao));
    }

    @DeleteMapping({"/{id}","/{id}/"})
    public HttpStatus deleteSolicitacao(@PathVariable Long id) throws IdNaoEncontradoException {
        this.solicitacaoServices.deleteSolicitacaoById(id);
        return HttpStatus.OK;
    }
}
