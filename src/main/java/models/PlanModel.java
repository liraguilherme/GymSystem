package models;


import jakarta.persistence.*;
import models.DTOS.PlanDTO;

import java.util.Objects;

@Entity
@Table(name = "planos")
public class PlanModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double value;
    private String description;

    public PlanModel() {
    }

    public PlanModel(PlanDTO planDTO) {
        this.name = planDTO.name();
        this.value = planDTO.price();
        this.description = planDTO.description();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanModel planModel = (PlanModel) o;
        return Objects.equals(id, planModel.id) && Objects.equals(name, planModel.name) && Objects.equals(value, planModel.value) && Objects.equals(description, planModel.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, value, description);
    }
}
