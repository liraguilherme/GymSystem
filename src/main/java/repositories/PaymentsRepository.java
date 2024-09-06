package repositories;

import models.ClientModel;
import models.PaymentsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentsRepository extends JpaRepository<PaymentsModel, Integer> {

    @Query("SELECT p FROM PagamentosModel p " +
            "WHERE p.client = :cliente " +
            "AND p.dataPagamento = (SELECT MAX(pp.dataPagamento) FROM PagamentosModel pp WHERE pp.client = :cliente)")
    PaymentsModel findLastPaymentByClient(@Param("cliente") ClientModel cliente);
}
