package controllers;

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
    public RegistrationModel matricularCliente(@RequestBody RealizarMatriculaDTO realizarMatriculaDTO) {
        System.out.println("Realizar Matricula");
        RegistrationModel registrationModel = registrationService
                .matricularCliente(realizarMatriculaDTO);
        return registrationModel;
    }

    @PostMapping("/desmatricular")
    public void desmatricularCliente(Integer idCliente){
        registrationService.desmatricularCliente(idCliente);
    }

    @GetMapping
    public List<RegistrationModel> listarMatriculas(){
        return registrationService.listarMatriculas();
    }

    @GetMapping("/{id}")
    public RegistrationModel buscarMatriculaPorId(Integer id){
        return registrationService.buscarMatriculaPorId(id);
    }
}