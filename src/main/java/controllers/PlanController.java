package controllers;


import io.swagger.v3.oas.annotations.Operation;
import models.DTOS.PlanDTO;
import models.PlanModel;
import org.springframework.web.bind.annotation.*;
import services.PlanService;

import java.util.List;

@RestController
@RequestMapping("/planos")
public class PlanController {

    private PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping
    public PlanModel CriarPlano(@RequestBody PlanDTO planDTO){
        System.out.println(planDTO);
        PlanModel plan = planService.cadastrarPlano(planDTO);
        System.out.println(plan);
        return plan;
    }

    @GetMapping
    public List<PlanModel> buscarPlano(){
        List<PlanModel> planos = planService.buscarPlanos();
        return planos;

    }

    @GetMapping("/{id}")
    public PlanModel buscarPlanoPorId(@PathVariable(value = "id") Integer planoID){
        PlanModel planModel = planService.buscarPorId(planoID);
        return planModel;
    }

    @PutMapping("/{id}")
    public PlanModel AtualizarPlano(@PathVariable(value = "id") Integer planoID, PlanDTO planDTO){
        PlanModel planModel = planService.atualizarPlano(planDTO, planoID);
        return planModel;
    }

    @DeleteMapping("/{id}")
    public void deletarPlano(@PathVariable(value = "id") Integer planoID){
        planService.deletarPlano(planoID);
    }
}
