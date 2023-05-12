package animal_shelter.telegram_bot_for_animal_shelter.service;

import animal_shelter.telegram_bot_for_animal_shelter.model.Client;
import animal_shelter.telegram_bot_for_animal_shelter.model.enums.PetType;

import java.util.List;

public interface ClientService {
    void createClient(Client client);

    void updateClient(Client client);

    Client getClientByChatIdAndPetType(long chatId, PetType petType);

    List<Client> getClientByChatId(long chatId);

    void fillClientPhoneNumberByChatId(long chatId, String phoneNumber);

    void deleteClient(Long chatId);
}
