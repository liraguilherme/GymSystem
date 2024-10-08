package services;

import exceptions.ResourceNotFoundException;
import models.ClientModel;
import models.DTOS.PaymentDTO;
import models.PaymentsModel;
import models.PlanModel;
import org.springframework.stereotype.Service;
import repositories.ClientRepository;
import repositories.PaymentsRepository;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class PaymentService {

    private PaymentsRepository paymentsRepository;

    private ClientRepository clientRepository;

    public PaymentService(PaymentsRepository paymentsRepository, ClientRepository clientRepository) {
        this.paymentsRepository = paymentsRepository;
        this.clientRepository = clientRepository;
    }

    /* O método a baixo calcula o valor a pagar da mensalidade
    caso tenha juros, caso não tenha e caso seja o primeiro pagamento */
    public PaymentsModel realizarPagamento(PaymentDTO paymentDTO) {
        ClientModel clientModel = clientRepository.findById(paymentDTO.idClient())
                .orElseThrow(() -> new ResourceNotFoundException("Não existe cliente com o ID informado!"));
        PaymentsModel lastPayment = paymentsRepository.findLastPaymentByClient(clientModel);
        LocalDate currentDate = LocalDate.now();
        double juros = 0;

        if(lastPayment != null) {
            LocalDate dateLastPayment = lastPayment.getPaymentDate();
            long periodoPagamento = ChronoUnit.DAYS.between(dateLastPayment, currentDate);

            juros = (periodoPagamento > 31) ? (periodoPagamento - 31) * 2 : 0;
        }

        double valorPagar = juros + clientModel.getPlanModel().getValue();
        PaymentsModel paymentsModel = new PaymentsModel(clientModel, clientModel.getPlanModel(), valorPagar, juros, paymentDTO.paymentMethod());
        paymentsRepository.save(paymentsModel);
        return paymentsModel;
    }

    public List<PaymentsModel> listarPagamentos(){
        List<PaymentsModel> pagamentos = paymentsRepository.findAll();
        return pagamentos;
    }

    public void estornarPagamento(Integer idPagamento){
        PaymentsModel paymentsModel = paymentsRepository
                .findById(idPagamento)
                .orElseThrow(() -> new ResourceNotFoundException("Não existe pagamento com este ID"));
        LocalDate currentDate = LocalDate.now();
        LocalDate paymentDate= paymentsModel.getPaymentDate();
        long periodoPagamento = ChronoUnit.DAYS.between(paymentDate, currentDate);
        if (periodoPagamento > 3){
            throw new ResourceNotFoundException("O prazo máximo é de 3 dias para o estorno!");
        }
        paymentsModel.setReversed(true);
        paymentsRepository.save(paymentsModel);
    }



}
