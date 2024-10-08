package services;

import exceptions.ResourceNotFoundException;
import models.ClientModel;
import models.DTOS.ClientChangePlanDTO;
import models.DTOS.ClientDTO;
import models.DTOS.PlanDTO;
import models.PlanModel;
import org.springframework.stereotype.Service;
import repositories.ClientRepository;
import repositories.PlanRepository;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    private PlanRepository planRepository;

    public ClientService(ClientRepository clientRepository, PlanRepository planRepository) {
        this.clientRepository = clientRepository;
        this.planRepository = planRepository;
    }

    public List<ClientModel> listarClientes(){
        List<ClientModel> clientes = clientRepository.findAll();
        System.out.println(clientes);
        return clientes;
    }

    public ClientModel cadastrarCliente(ClientDTO clientDTO) throws ParseException {
        PlanModel plano = planRepository.findById(clientDTO.idPlan())
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum plano com este ID"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date dataNascimento = dateFormat.parse(clientDTO.dateOfBirth());

        ClientModel clientModel = new ClientModel(clientDTO, plano, dataNascimento);
        System.out.println(clientModel);
        clientRepository.save(clientModel);
        return clientModel;
    }

    public ClientModel buscarCLientePorID(Integer id){
        ClientModel clientModel = clientRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException
                        ("Não foi encontrado nenhum cliente com este ID!"));
       return clientModel;
    }

    public ClientModel atualizarCliente(Integer clientId,
                                        ClientDTO clientDTO) throws ParseException{
        var entity = clientRepository
                .findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum cliente com este ID!"));
    entity.setName(clientDTO.name());
    entity.setEmail(clientDTO.email());
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            try{
                Date dataNascimento = dateFormat
                        .parse(clientDTO.dateOfBirth());
                entity.setDataNascimento(dataNascimento);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            entity.setPhoneNumber(clientDTO.phoneNumber());
            clientRepository.save(entity);

            return entity;
    }

    public void deletarCliente(Integer clientId){
        var entity = clientRepository
                .findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum cliente com este ID!"));
        clientRepository.delete(entity);
    }
    public void alterarPlano(ClientChangePlanDTO clientChangePlanDTO){
         var entity = clientRepository.findById(clientChangePlanDTO
                 .idClient()).orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum cliente com este ID!"));
         PlanModel planModel = planRepository.findById(clientChangePlanDTO
                 .idPlan()).orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum plano com este ID!"));
       entity.setPlanModel(planModel);
       clientRepository.save(entity);
    }
}
