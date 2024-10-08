package services;

import exceptions.ResourceNotFoundException;
import models.ClientModel;
import models.ClientSituation;
import models.DTOS.PaymentDTO;
import models.DTOS.RealizarMatriculaDTO;
import models.RegistrationModel;
import org.springframework.stereotype.Service;
import repositories.ClientRepository;
import repositories.PlanRepository;
import repositories.RegistrationRepository;

import java.util.List;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;
    private PlanRepository planRepository;
    private ClientRepository clientRepository;
    private PaymentService paymentService;

    public RegistrationService(RegistrationRepository registrationRepository, PlanRepository planRepository, ClientRepository clientRepository, PaymentService paymentService) {
        this.registrationRepository = registrationRepository;
        this.planRepository = planRepository;
        this.clientRepository = clientRepository;
        this.paymentService = paymentService;
    }

    public RegistrationModel matricularCliente(RealizarMatriculaDTO realizarMatriculaDTO){
        var cliente = clientRepository.findById(realizarMatriculaDTO.idClient())
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum cliente com este ID!"));
        var plan = planRepository.findById(realizarMatriculaDTO.idPlan())
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum plano com este ID!"));

        if(cliente.getClientSituation() == ClientSituation.Regular){
            throw new ResourceNotFoundException("Este cliente já está matriculado!");
        }
        if (cliente.getClientSituation() == ClientSituation.Atrasado){
            throw new ResourceNotFoundException("Este cliente está com pendencias!");
        }

        cliente.setClientSituation(ClientSituation.Regular);
        cliente.setPlanModel(plan);
        RegistrationModel registration = new RegistrationModel(cliente, plan);
        cliente.setRegistrationModel(registration);
        PaymentDTO paymentDTO = new PaymentDTO(cliente.getId(), realizarMatriculaDTO.paymentMethod());
        paymentService.realizarPagamento(paymentDTO);
        clientRepository.save(cliente);
        registrationRepository.save(registration);

        return registration;
    }

    public void desmatricularCliente(Integer idCliente){
        ClientModel cliente = clientRepository.findById(idCliente)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum cliente com este ID"));
        if (cliente.getClientSituation() == ClientSituation.Desmatriculado) {
            throw new ResourceNotFoundException("Este cliente já está desmatriculado");

        }
        cliente.setPlanModel(null);
        cliente.setRegistrationModel(null);
        cliente.setClientSituation(ClientSituation.Desmatriculado);
        clientRepository.save(cliente);
        RegistrationModel registrationModel = clientRepository
                .findResgistrationModelById(idCliente);
        registrationModel.setActiveRegistration(false);
        registrationRepository.save(registrationModel);
    }

    public List<RegistrationModel> listarMatriculas(){
        List<RegistrationModel> matriculas = registrationRepository.findAll();
        return matriculas;
    }

    public RegistrationModel buscarMatriculaPorId(Integer id){
        RegistrationModel matricula = registrationRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi encontrado nenhuma matricula com este ID!"));
        return matricula;
    }
}

