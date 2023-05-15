package animal_shelter.telegram_bot_for_animal_shelter.repository;

import animal_shelter.telegram_bot_for_animal_shelter.model.Client;
import animal_shelter.telegram_bot_for_animal_shelter.model.ClientDetails;
import animal_shelter.telegram_bot_for_animal_shelter.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDetailsRepository extends JpaRepository<ClientDetails, Long> {
    // Поиск усыновителя по clientId
    ClientDetails findClientDetailsByClientId(Client clientId);

    // Поиск усыновителя по clientDetailsId
    ClientDetails findClientDetailsByClientDetailsId(Long clientDetailsId);

    // Поиск усыновителей по статусу
    List<ClientDetails> findClientDetailsByStatus(Status status);

    // Поиск усыновителей у которых волонтер сменил статус
    List<ClientDetails> findClientDetailsByWasNotifiedOfStatusChangeAndStatusNotLike(boolean wasNotifiedOfStatusChange, Status status);

    // Поиск усыновителей, находящихся в ожидании
    List<ClientDetails> findClientDetailsByWasNotifiedOfStatusChangeAndStatus(boolean wasNotifiedOfStatusChange, Status status);
}
