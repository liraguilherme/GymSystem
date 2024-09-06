package repositories;

import models.ClientModel;
import models.RegistrationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Integer> {

    RegistrationModel findResgistrationModelById(Integer clientId);
}
