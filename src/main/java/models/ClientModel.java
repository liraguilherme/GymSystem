package models;


import jakarta.persistence.*;

import java.util.Date;

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




}
