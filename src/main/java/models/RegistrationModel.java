package models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "matriculas")
public class RegistrationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_matricula")
    private LocalDate registrationDate;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private ClientModel client;

    @ManyToOne
    @JoinColumn(name = "idPlano")
    private PlanModel plan;

    @Column(name = "valorPago")
    private Double amountPaid;

    @Column(name = "matriculaAtiva")
    private Boolean activeRegistration;

    public RegistrationModel() {
    }

    public RegistrationModel(LocalDate registrationDate, ClientModel client, PlanModel plan, Double amountPaid, Boolean activeRegistration) {
        this.registrationDate = registrationDate;
        this.client = client;
        this.plan = plan;
        this.amountPaid = amountPaid;
        this.activeRegistration = activeRegistration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public ClientModel getClient() {
        return client;
    }

    public void setClient(ClientModel client) {
        this.client = client;
    }

    public PlanModel getPlan() {
        return plan;
    }

    public void setPlan(PlanModel plan) {
        this.plan = plan;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Boolean getActiveRegistration() {
        return activeRegistration;
    }

    public void setActiveRegistration(Boolean activeRegistration) {
        this.activeRegistration = activeRegistration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationModel that = (RegistrationModel) o;
        return Objects.equals(id, that.id) && Objects.equals(registrationDate, that.registrationDate) && Objects.equals(client, that.client) && Objects.equals(plan, that.plan) && Objects.equals(amountPaid, that.amountPaid) && Objects.equals(activeRegistration, that.activeRegistration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, registrationDate, client, plan, amountPaid, activeRegistration);
    }
}

