package models;


import jakarta.persistence.*;
import models.DTOS.ClientDTO;
import models.DTOS.PlanDTO;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "clientes")
public class ClientModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "dataNascimento")
    private Date dataNascimento;

    @ManyToOne
    @JoinColumn(name = "plano_id")
    private PlanModel planModel;

    @Column(name = "numero_telefone")
    private String phoneNumber;

    @Column(name = "situacao_cliente_id")
    private ClientSituation clientSituation;

    @ManyToOne
    @JoinColumn(name = "matricula_id")
    private RegistrationModel registrationModel;

    public ClientModel() {
    }

    public ClientModel(ClientDTO clientDTO, PlanModel planModel, Date dataNascimento) {
        this.name = name;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.planModel = planModel;
        this.phoneNumber = phoneNumber;
        this.registrationModel = registrationModel;
        this.clientSituation = clientSituation;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public PlanModel getPlanModel() {
        return planModel;
    }

    public void setPlanModel(PlanModel planModel) {
        this.planModel = planModel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public RegistrationModel getRegistrationModel() {
        return registrationModel;
    }

    public void setRegistrationModel(RegistrationModel registrationModel) {
        this.registrationModel = registrationModel;
    }

    public ClientSituation getClientSituation() {
        return clientSituation;
    }

    public void setClientSituation(ClientSituation clientSituation) {
        this.clientSituation = clientSituation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientModel that = (ClientModel) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(cpf, that.cpf) && Objects.equals(dataNascimento, that.dataNascimento) && Objects.equals(planModel, that.planModel) && Objects.equals(phoneNumber, that.phoneNumber) && clientSituation == that.clientSituation && Objects.equals(registrationModel, that.registrationModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, cpf, dataNascimento, planModel, phoneNumber, clientSituation, registrationModel);
    }
}
