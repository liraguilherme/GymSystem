package controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jdk.jfr.ContentType;
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
    @Operation(summary = "Adds a new client",
    description = "Adds a new Client by passing in a JSON, XML or YML representation of the client!",
    tags = {"Client"},
    responses = {
            @ApiResponse(description = "Success", responseCode = "200",
            content = @Content(schema = @Schema(implementation = ClientModel.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
        }
    )

    ClientModel cadastrarCliente(@RequestBody ClientDTO clientDTO) throws ParseException{
        System.out.println("Cadastrar Cliente");
        ClientModel clientModel = clientService.cadastrarCliente(clientDTO);
        return clientModel;
    }

    @GetMapping
    @Operation(summary = "Finds all clients", description = "Finds all clients",
    tags = {"Client"},
    responses = {
            @ApiResponse(description = "Success", responseCode = "200",
            content = {
                    @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ClientModel.class))

                    )
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
           }

    )
    public List<ClientModel> buscarClientes(){
        List<ClientModel> clientes = clientService.listarClientes();
        return clientes;
    }

    @GetMapping("/{id}")
    @Operation(summary = "  Finds a client", description = "Finds a client",
    tags = {"Client"},
    responses = {
            @ApiResponse(description = "Success", responseCode = "200",
            content = @Content(schema = @Schema(implementation = ClientModel.class))
            ),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "500", content = @Content),

        }

    )
    public ClientModel buscarClientePorId(@PathVariable(value = "id") Integer clienteId){
        ClientModel cliente = clientService.buscarCLientePorID(clienteId);
        return cliente;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates a Client",
    description = "Updates a Client by passing in a JSON, XML or YML representation of the client!",
    tags = {"Client"},
    responses = {
            @ApiResponse(description = "Updated", responseCode = "200",
            content = @Content(schema = @Schema(implementation = ClientModel.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

           }
    )
    public ClientModel atualizarCliente(@PathVariable(value = "id") Integer clienteId, ClientDTO clientDTO) throws ParseException{
        ClientModel clientModel = clientService.atualizarCliente(clienteId,  clientDTO);
        return clientModel;

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a Client",
    description = "Deletes a Client by passing in a JSON, XML or YML representation of the Client!",
    tags = {"Client"},
    responses = {
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

          }
    )
    public void deletarPlano(@PathVariable(value = "id") Integer clienteId){
        clientService.deletarCliente(clienteId);
    }

    @PutMapping("/alterarPlano")
    @Operation(summary = "Changes a Plan",
    description = "Changes a Plan by passing in a JSON, XML, or YML representation of the Client",
    tags = {"Client"},
    responses = {
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
    })
    public void alterarPlano(ClientChangePlanDTO clientChangePlanDTO){
        clientService.alterarPlano(clientChangePlanDTO);
    }
}
