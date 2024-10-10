package controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import models.DTOS.RealizarMatriculaDTO;
import models.RegistrationModel;
import org.springframework.web.bind.annotation.*;
import services.RegistrationService;

import java.util.List;

@RestController
@RequestMapping("/matricula")
public class RegisterController {

    private RegistrationService registrationService;

    public RegisterController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    @Operation(summary = "Matricular cliente",
            description = "Adds a new matricula by passing in a JSON, XML or YML representation of the client!",
            tags = {"Matricula"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = RegistrationModel.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public RegistrationModel matricularCliente(@RequestBody RealizarMatriculaDTO realizarMatriculaDTO) {
        System.out.println("Realizar Matricula");
        RegistrationModel registrationModel = registrationService
                .matricularCliente(realizarMatriculaDTO);
        return registrationModel;
    }

    @PostMapping("/desmatricular")
    @Operation(summary = "Desmatricular cliente",
            description = "Desmatricula by passing in a JSON, XML or YML representation of the client!",
            tags = {"Matricula"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = RegistrationModel.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public void desmatricularCliente(Integer idCliente){
        registrationService.desmatricularCliente(idCliente);
    }

    @GetMapping
    @Operation(summary = "Finds all matriculas", description = "Finds all matriculas",
            tags = {"Matricula"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = RegistrationModel.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public List<RegistrationModel> listarMatriculas(){
        return registrationService.listarMatriculas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Finds a matricula", description = "Finds a matricula",
            tags = {"Matricula"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = RegistrationModel.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public RegistrationModel buscarMatriculaPorId(Integer id){
        return registrationService.buscarMatriculaPorId(id);
    }
}