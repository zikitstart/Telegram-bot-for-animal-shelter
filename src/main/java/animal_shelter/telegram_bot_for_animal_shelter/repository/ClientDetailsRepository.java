package animal_shelter.telegram_bot_for_animal_shelter.repository;

import animal_shelter.telegram_bot_for_animal_shelter.model.Client;
import animal_shelter.telegram_bot_for_animal_shelter.model.ClientDetails;
import animal_shelter.telegram_bot_for_animal_shelter.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDetailsRepository extends JpaRepository<ClientDetails, Long> {
    ClientDetails findClientDetailsByClientId(Client clientId);

    List<ClientDetails> findClientDetailsByStatus(Status status);

    List<ClientDetails> findClientDetailsByWasNotifiedOfStatusChangeAndStatusNotLike(boolean wasNotifiedOfStatusChange, Status status);

    List<ClientDetails> findClientDetailsByWasNotifiedOfStatusChangeAndStatus(boolean wasNotifiedOfStatusChange, Status status);
}
