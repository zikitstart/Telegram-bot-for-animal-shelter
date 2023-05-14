package animal_shelter.telegram_bot_for_animal_shelter.service.impl;

import animal_shelter.telegram_bot_for_animal_shelter.model.Client;
import animal_shelter.telegram_bot_for_animal_shelter.model.enums.PetType;
import animal_shelter.telegram_bot_for_animal_shelter.repository.ClientRepository;
import animal_shelter.telegram_bot_for_animal_shelter.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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

    @Override
    public Client getClientByChatIdAndPetType(long chatId, PetType petType) {
        return clientRepository.findClientByChatId(chatId).stream()
                .filter(client -> client.getChatId().equals(chatId) && client.getPetType().equals(petType))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Client> getClientByChatId(long chatId) {
        return new ArrayList<>(clientRepository.findClientByChatId(chatId));
    }

    @Override
    public void fillClientPhoneNumberByChatId(long chatId, String phoneNumber) {
        getClientByChatId(chatId)
                .forEach(e -> {
                    e.setPhoneNumber(phoneNumber);
                    updateClient(e);
                });
    }

    @Override
    public Client getClientByUserId(long userId) {
        return clientRepository.findClientByUserId(userId);
    }

    @Override
    public Client deleteClient(Long userId){
        Client client = getClientByUserId(userId);
        clientRepository.delete(client);
        return client;
    }
}
