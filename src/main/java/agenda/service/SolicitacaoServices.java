package agenda.service;


import agenda.entity.Cliente;
import agenda.entity.Equipamento;
import agenda.entity.Solicitacao;
import agenda.exception.*;
import agenda.repository.AssistenciaRepository;
import agenda.repository.ClienteRepository;
import agenda.repository.EquipamentoRepository;
import agenda.repository.SolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoServices {

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;
    @Autowired
    private EquipamentoRepository equipamentoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private AssistenciaRepository assistenciaRepository;

    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Solicitacao salvarSolicitacao(Solicitacao solicitacao) throws EquipamentoInvalidoException, CpfInvalidoException, ForaHorarioComercialException, ParseException, HorarioOcupadoException, IdNaoEncontradoException {
        if (solicitacao.getCliente_id().getCpf() != null) {

            if (solicitacao.getCliente_id().getCpf().length() != 11) {
                throw new CpfInvalidoException(solicitacao.getCliente_id().getCpf());
            }

            String cpf = solicitacao.getCliente_id().getCpf();

            Long idByCpfTeste = solicitacaoRepository.getIdByCpf(cpf);

            if (idByCpfTeste == null) {
                Cliente salvarCpfCliente = new Cliente(cpf);
                criarCliente(salvarCpfCliente);

                Long idByCpf = solicitacaoRepository.getIdByCpf(cpf);
                Cliente clienteFinal = new Cliente(idByCpf);
                Solicitacao solicitacaoById = new Solicitacao(solicitacao.getDescricao(), solicitacao.getHora_agora(), solicitacao.getDia_agendado(), solicitacao.getHora_agendada(), solicitacao.getAssistencia_id(), clienteFinal, solicitacao.getEquipamento());
                return solicitacaoRepository.save(solicitacaoById);
            } else {

                Long idByCpf = solicitacaoRepository.getIdByCpf(cpf);
                Cliente clienteFinal = new Cliente(idByCpf);
                Solicitacao solicitacaoById = new Solicitacao(solicitacao.getDescricao(), solicitacao.getHora_agora(), solicitacao.getDia_agendado(), solicitacao.getHora_agendada(), solicitacao.getAssistencia_id(), clienteFinal, solicitacao.getEquipamento());
                return solicitacaoRepository.save(solicitacaoById);

            }

        } else {

                Optional<Equipamento> eq = equipamentoRepository.findById(solicitacao.getEquipamento().getId());
                Cliente clienteById = clienteRepository.getById(solicitacao.getCliente_id().getId());
                List<Time> ocupados = assistenciaRepository.mostrarTodosOsHorariosOcupados(solicitacao.getAssistencia_id().getId(), solicitacao.getDia_agendado());

                if (eq.isEmpty()) {
                    throw new EquipamentoInvalidoException();
                }

                if (clienteById.getCpf().length() != 11) {
                    throw new CpfInvalidoException(solicitacao.getCliente_id().getCpf());
                }

                DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                java.sql.Time startTime = new java.sql.Time(formatter.parse("09:00:00").getTime());
                java.sql.Time endTime = new java.sql.Time(formatter.parse("17:00:00").getTime());

                Time horaAnalisada = solicitacao.getHora_agendada();
                if (horaAnalisada.before(startTime) || horaAnalisada.after(endTime)) {
                    throw new ForaHorarioComercialException(solicitacao.getHora_agendada());
                }

                if (ocupados.contains(solicitacao.getHora_agendada())) {
                    throw new HorarioOcupadoException();
                }

                return solicitacaoRepository.save(solicitacao);
            }
        }

        public void deleteSolicitacaoById (Long id) throws IdNaoEncontradoException {
            Optional<Solicitacao> solicitacao = solicitacaoRepository.findById(id);

            if (solicitacao.isPresent()) {
                solicitacaoRepository.deleteById(id);
            } else {
                throw new IdNaoEncontradoException();
            }
        }
    }
