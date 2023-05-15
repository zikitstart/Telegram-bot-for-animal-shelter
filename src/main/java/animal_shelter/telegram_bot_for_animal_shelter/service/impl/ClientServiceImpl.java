package animal_shelter.telegram_bot_for_animal_shelter.service.impl;

import animal_shelter.telegram_bot_for_animal_shelter.model.Client;
import animal_shelter.telegram_bot_for_animal_shelter.model.enums.PetType;
import animal_shelter.telegram_bot_for_animal_shelter.repository.ClientRepository;
import animal_shelter.telegram_bot_for_animal_shelter.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
// Класс по работе с клиентами (Потенциальными усыновителями)
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void createClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void updateClient(Client client) {
        clientRepository.save(client);
    }

    // Получение клиента по chatId и petType
    @Override
    public Client getClientByChatIdAndPetType(long chatId, PetType petType) {
        return clientRepository.findClientByChatId(chatId).stream()
                .filter(client -> client.getChatId().equals(chatId) && client.getPetType().equals(petType))
                .findAny()
                .orElse(null);
    }

    // Получение клиента по chatId
    @Override
    public List<Client> getClientByChatId(long chatId) {
        return new ArrayList<>(clientRepository.findClientByChatId(chatId));
    }

    // Добавление номера телефона клиенту
    @Override
    public void fillClientPhoneNumberByChatId(long chatId, String phoneNumber) {
        getClientByChatId(chatId)
                .forEach(e -> {
                    e.setPhoneNumber(phoneNumber);
                    updateClient(e);
                });
    }

    // Получение клиента по userId
    @Override
    public Client getClientByUserId(long userId) {
        return clientRepository.findClientByUserId(userId);
    }

    // Удаление клиента из базы данных
    @Override
    public Client deleteClient(Long userId){
        Client client = getClientByUserId(userId);
        clientRepository.delete(client);
        return client;
    }
}
