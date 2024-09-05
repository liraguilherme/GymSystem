package models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "titles")
public class PaymentsModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private ClientModel client;

    @ManyToOne
    @JoinColumn(name = "idPlano")
    private PlanModel plan;

    @Column(name = "valorPago")
    private Double amountPaid;

    @Column(name = "juros")
    private Double Fees;

    @Column(name = "dataPagamento")
    private LocalDate paymentDate;

    @Column(name = "formaDePagamento")
    private String paymentMethod;

    @Column(name = "estornado")
    private boolean reversed;

    public PaymentsModel() {
    }

    public PaymentsModel(ClientModel client, PlanModel plan, Double amountPaid, Double fees, LocalDate paymentDate, String paymentMethod, boolean reversed) {
        this.client = client;
        this.plan = plan;
        this.amountPaid = amountPaid;
        Fees = fees;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.reversed = reversed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Double getFees() {
        return Fees;
    }

    public void setFees(Double fees) {
        Fees = fees;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isReversed() {
        return reversed;
    }

    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentsModel that = (PaymentsModel) o;
        return reversed == that.reversed && Objects.equals(id, that.id) && Objects.equals(client, that.client) && Objects.equals(plan, that.plan) && Objects.equals(amountPaid, that.amountPaid) && Objects.equals(Fees, that.Fees) && Objects.equals(paymentDate, that.paymentDate) && Objects.equals(paymentMethod, that.paymentMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, plan, amountPaid, Fees, paymentDate, paymentMethod, reversed);
    }
}
