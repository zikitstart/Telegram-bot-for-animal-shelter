package animal_shelter.telegram_bot_for_animal_shelter.controller;

import animal_shelter.telegram_bot_for_animal_shelter.model.Client;
import animal_shelter.telegram_bot_for_animal_shelter.model.enums.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {
    private Client client1;
    private Client client2;
    private Client client3;
    private List<Client> clients = new ArrayList<>();

    @BeforeEach
    void setUp() {
        client1 = new Client(1L,"Ковалёв","Сергей","Петрович","234-56-78", PetType.CAT);
        client2 = new Client(2L, "Ковалёв", "Сергей", "Петрович", "234-56-78", PetType.DOG);
        client3 = new Client(3L, "Ковалёв", "Сергей", "Петрович", "234-56-78", PetType.DOG);

        clients = Arrays.asList(client1, client2, client3);
    }

    @Test
    @DisplayName("When an object is created with parameters passed to it, then true")
    void createClient() {
        Client clientExpected = new Client(1L,"Ковалёв","Сергей","Петрович","234-56-78",PetType.CAT);
        assertEquals(clientExpected,client1);
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
}