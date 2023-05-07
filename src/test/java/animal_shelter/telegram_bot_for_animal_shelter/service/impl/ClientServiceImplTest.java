package animal_shelter.telegram_bot_for_animal_shelter.service.impl;

import animal_shelter.telegram_bot_for_animal_shelter.model.Client;
import animal_shelter.telegram_bot_for_animal_shelter.model.enums.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceImplTest {

    private Client client1;
    private List<Client> clients = new ArrayList<>();

    @BeforeEach
    void setUp() {
        client1 = new Client(1L,"Ковалёв","Сергей","Петрович","234-56-78", PetType.CAT);
        Client client2 = new Client(2L, "Ковалёв", "Сергей", "Петрович", "234-56-78", PetType.DOG);
        Client client3 = new Client(3L, "Ковалёв", "Сергей", "Петрович", "234-56-78", PetType.DOG);

        clients = Arrays.asList(client1, client2, client3);
    }

    @Test
    @DisplayName("When an object is created with parameters passed to it, then true")
    void createClient() {
        assertEquals(client1.getChatId(),1L);
        assertEquals(client1.getSurname(),"Ковалёв");
        assertEquals(client1.getFirstName(),"Сергей");
        assertEquals(client1.getLastName(),"Петрович");
        assertEquals(client1.getPhoneNumber(),"234-56-78");
        assertEquals(client1.getPetType(),PetType.CAT);
    }

    @Test
    @DisplayName("When an object is update with parameters passed to it, then true")
    void updateClient() {
        Client clientExpected = new Client(2L,"Ковалёв","Пётр","Петрович","234-56-78", PetType.DOG);
        client1.setChatId(2L);
        client1.setFirstName("Пётр");
        client1.setPetType(PetType.DOG);
        assertEquals(clientExpected,client1);
    }

    @Test
    @DisplayName("When we find an object by ChatId and PetType, then true")
    void getClientByChatIdAndPetType() {
        Client clientExpected = null;
        for (Client user : clients) {
            if (user.getChatId().equals(1L) && user.getPetType().equals(PetType.CAT)){
                clientExpected = user;
            }
        }
        assertEquals(clientExpected,client1);
    }

    @Test
    @DisplayName("When we find an object by ChatId, then true")
    void getClientByChatId() {
        Client clientExpected = null;
        for (Client user : clients) {
            if (user.getChatId().equals(1L)){
                clientExpected = user;
            }
        }
        assertEquals(clientExpected,client1);
    }

    @Test
    @DisplayName("When we find an object by ChatId and update PhoneNumber, then true")
    void fillClientPhoneNumberByChatId() {
        Client clientExpected = new Client(1L,"Ковалёв","Сергей","Петрович","222-22-22", PetType.CAT);
        for (Client user : clients) {
            if (user.getChatId().equals(1L)){
                user.setPhoneNumber("222-22-22");
            }
        }
        assertEquals(clientExpected,client1);
    }
}