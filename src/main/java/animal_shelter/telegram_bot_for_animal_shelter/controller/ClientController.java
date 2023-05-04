package animal_shelter.telegram_bot_for_animal_shelter.controller;

import animal_shelter.telegram_bot_for_animal_shelter.service.ClientService;
import org.springframework.stereotype.Controller;

@Controller
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    public void addPhoneNumber(Long chatId, String phoneNumber) {
        clientService.fillClientPhoneNumberByChatId(chatId, phoneNumber);
    }
}
