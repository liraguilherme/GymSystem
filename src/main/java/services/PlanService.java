package services;

import exceptions.ResourceNotFoundException;
import models.DTOS.PlanDTO;
import models.PlanModel;
import org.springframework.stereotype.Service;
import repositories.PlanRepository;

import java.util.List;

@Service
public class PlanService {

    private PlanRepository repository;

    public PlanService(PlanRepository repository) {
        this.repository = repository;
    }

    public PlanModel cadastrarPlano(PlanDTO planoDTO){
        PlanModel plano = new PlanModel();
        repository.save(plano);
        return plano;
    }

    public List<PlanModel> buscarPlanos(){
        List<PlanModel> planos = repository.findAll();
        return planos;
    }

    public PlanModel buscarPorId(Integer id){
        PlanModel plano = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum plano com este ID!"));
        return plano;
    }

    public PlanModel atualizarPlano(PlanDTO planDTO, Integer planoId){

        var entity = repository.findById(planoId).orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum plano com este ID!"));

        entity.setName(planDTO.name());
        entity.setDescription(planDTO.description());
        entity.setValue(planDTO.price());
        repository.save(entity);

        return entity;
    }

    public void deletarPlano(Integer planoId){
        var entity = repository.findById(planoId).orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum plano com este ID!"));
        repository.delete(entity);
    }





    
}
