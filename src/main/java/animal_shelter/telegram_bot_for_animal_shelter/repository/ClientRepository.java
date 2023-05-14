package animal_shelter.telegram_bot_for_animal_shelter.repository;

import animal_shelter.telegram_bot_for_animal_shelter.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    //Получение пользователя по chatId
    List<Client> findClientByChatId(Long chatId);
    Client findClientByUserId(Long userId);
}
