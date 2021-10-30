package agenda.controller;

import agenda.entity.Cliente;
import agenda.service.ClienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteServices clienteServices;

    @GetMapping({"/",""})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Cliente>> getAllClientes(){
        return ResponseEntity.ok(clienteServices.getClienteList());
    }
    @GetMapping({"/{id}","/{id}/"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.clienteServices.getClienteById(id));
    }

    @PostMapping("/adicionarCliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cliente> adicionarCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(this.clienteServices.criarCliente(cliente));
    }

    @PostMapping("/adicionarClientes")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<Cliente>> adicionarListaClientes(@RequestBody List<Cliente> clientes) {
        return ResponseEntity.ok(this.clienteServices.criarListaCliente(clientes));
    }

    @PutMapping("/atualizarCliente/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Cliente> atualizarCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok().body(this.clienteServices.updateClienteById(cliente));
    }

    @DeleteMapping("/deletarCliente/{id}")
    public HttpStatus deleteUser(@PathVariable Long id) {
        this.clienteServices.deletarClienteById(id);
        return HttpStatus.OK;
    }

    
}
