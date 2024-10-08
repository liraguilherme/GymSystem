package controllers;


import models.ClientModel;
import models.DTOS.ClientChangePlanDTO;
import models.DTOS.ClientDTO;
import models.DTOS.PlanDTO;
import org.springframework.web.bind.annotation.*;
import services.ClientService;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClientService clientService;

    public ClienteController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    ClientModel cadastrarCliente(@RequestBody ClientDTO clientDTO) throws ParseException{
        System.out.println("Cadastrar Cliente");
        ClientModel clientModel = clientService.cadastrarCliente(clientDTO);
        return clientModel;
    }

    @GetMapping
    public List<ClientModel> buscarClientes(){
        List<ClientModel> clientes = clientService.listarClientes();
        return clientes;
    }

    @GetMapping("/{id}")
    public ClientModel buscarClientePorId(@PathVariable(value = "id") Integer clienteId){
        ClientModel cliente = clientService.buscarCLientePorID(clienteId);
        return cliente;
    }

    @PutMapping("/{id}")
    public ClientModel atualizarCliente(@PathVariable(value = "id") Integer clienteId, ClientDTO clientDTO) throws ParseException{
        ClientModel clientModel = clientService.atualizarCliente(clienteId,  clientDTO);
        return clientModel;

    }

    @DeleteMapping("/{id}")
    public void deletarPlano(@PathVariable(value = "id") Integer clienteId){
        clientService.deletarCliente(clienteId);
    }

    @PutMapping("/alterarPlano")
    public void alterarPlano(ClientChangePlanDTO clientChangePlanDTO){
        clientService.alterarPlano(clientChangePlanDTO);
    }
}
