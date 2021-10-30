package agenda.service;

import agenda.entity.Cliente;
import agenda.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServices {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> criarListaCliente(List<Cliente> list) {
        return clienteRepository.saveAll(list);
    }

    public List<Cliente> getClienteList() {
        return clienteRepository.findAll();
    }

    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente updateClienteById(Cliente cliente) {
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(cliente.getId());

        if (clienteEncontrado.isPresent()) {
            Cliente clienteUpdate = clienteEncontrado.get();
            clienteUpdate.setNome(cliente.getNome());
            clienteUpdate.setCpf(cliente.getCpf());

            return clienteRepository.save(cliente);
        } else {
            return null;
        }
    }

    public String deletarClienteById(Long id) {
        clienteRepository.deleteById(id);
        return "Cliente "+ id +" deletado.";
    }
}