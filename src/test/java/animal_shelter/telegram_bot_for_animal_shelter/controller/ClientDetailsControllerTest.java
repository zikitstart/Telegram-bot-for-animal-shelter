package animal_shelter.telegram_bot_for_animal_shelter.controller;

import animal_shelter.telegram_bot_for_animal_shelter.model.*;
import animal_shelter.telegram_bot_for_animal_shelter.model.enums.PetType;
import animal_shelter.telegram_bot_for_animal_shelter.repository.ClientDetailsRepository;
import animal_shelter.telegram_bot_for_animal_shelter.service.impl.ClientDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static animal_shelter.telegram_bot_for_animal_shelter.model.enums.Status.*;
import static animal_shelter.telegram_bot_for_animal_shelter.model.enums.Status.WAIT_FOR_DECISION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientDetailsControllerTest {
    private Client client1;
    private Client client2;
    private Pet pet1;
    private Pet pet2;
    private ClientDetails clientDetails1;
    private ClientDetails clientDetails2;
    private ClientDetails clientDetails3;
    private ClientDetails clientDetails4;
    private ClientDetails clientDetails5;
    private ClientDetails clientDetails6;
    @InjectMocks
    private ClientDetailsServiceImpl clientDetailsService;
    @Mock
    private ClientDetailsRepository clientDetailsRepository;

    @BeforeEach
    void setUp() {
        client1 = new Client(1L, 1L, "Ковалёв", "Сергей", "Петрович", "234-56-78", PetType.CAT);
        client2 = new Client(2L, 2L, "Ковалёв", "Сергей", "Петрович", "234-56-78", PetType.DOG);

        Shelter shelter = new Shelter(1L, null, null, null, null, null, null, null, null);
        pet1 = new Pet(1L, 31, "Кот", PetType.CAT, shelter);
        pet2 = new Pet(2L, 32, "Собака", PetType.DOG, shelter);

        clientDetails1 = new ClientDetails(1L, client1, pet1, TRIAL, TRIAL, LocalDate.of(2023, 5, 20), true);
        clientDetails2 = new ClientDetails(2L, client2, pet2, EXTRA_14, EXTRA_14, LocalDate.of(2023, 5, 20), true);
        clientDetails3 = new ClientDetails(3L, client1, pet1, EXTRA_30, EXTRA_30, LocalDate.of(2023, 5, 20), true);
        clientDetails4 = new ClientDetails(4L, client1, pet1, DENIED, DENIED, LocalDate.of(2023, 5, 20), true);
        clientDetails5 = new ClientDetails(5L, client1, pet1, ACCEPTED, ACCEPTED, LocalDate.of(2023, 5, 20), true);
        clientDetails6 = new ClientDetails(6L, client1, pet1, WAIT_FOR_DECISION, WAIT_FOR_DECISION, LocalDate.of(2023, 5, 20), false);
    }

    @Test
    @DisplayName("When an object is created with parameters passed to it, then true")
    void createClientDetails() {
        ClientDetails clientDetailsExpected = new ClientDetails(1L, client1, pet1, TRIAL, TRIAL, LocalDate.of(2023, 5, 20), true);
        assertEquals(clientDetailsExpected, clientDetails1);
    }

    @Test
    @DisplayName("When an object is update with parameters passed to it, then true")
    void updateClientDetails() {
        ClientDetails clientDetailsExpected = new ClientDetails(1L, client2, pet2, EXTRA_14, EXTRA_14, LocalDate.of(2023, 5, 20), true);
        clientDetails1.setClientId(client2);
        clientDetails1.setPetId(pet2);
        clientDetails1.setPrevStatus(EXTRA_14);
        clientDetails1.setStatus(EXTRA_14);
        assertEquals(clientDetailsExpected, clientDetails1);
    }

    @Test
    @DisplayName("When we find an object by status(EXTRA_14,EXTRA_30), then true")
    void getClientsWithExtraPeriod() {
        List<ClientDetails> expected = new ArrayList<>(List.of(clientDetails2, clientDetails3));
        when(clientDetailsRepository.findClientDetailsByStatus(EXTRA_14)).thenReturn(Collections.singletonList(clientDetails2));
        when(clientDetailsRepository.findClientDetailsByStatus(EXTRA_30)).thenReturn(Collections.singletonList(clientDetails3));
        List<ClientDetails> actual = clientDetailsService.getClientsWithExtraPeriod();
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    @DisplayName("When we find an object by status(TRIAL,EXTRA_14,EXTRA_30), then true")
    void getActualClients() {
        List<ClientDetails> expected = new ArrayList<>(List.of(clientDetails1, clientDetails2, clientDetails3));
        when(clientDetailsRepository.findClientDetailsByStatus(TRIAL)).thenReturn(Collections.singletonList(clientDetails1));
        when(clientDetailsRepository.findClientDetailsByStatus(EXTRA_14)).thenReturn(Collections.singletonList(clientDetails2));
        when(clientDetailsRepository.findClientDetailsByStatus(EXTRA_30)).thenReturn(Collections.singletonList(clientDetails3));
        List<ClientDetails> actual = clientDetailsService.getActualClients();
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    @DisplayName("When we find an object by userId, then delete object")
    void deleteClientDetails() {
        List<ClientDetails> clientDetailsExpected = List.of(clientDetails1, clientDetails2, clientDetails3, clientDetails4, clientDetails5);
        List<ClientDetails> clientDetailsActual = new ArrayList<>(List.of(clientDetails1, clientDetails2, clientDetails3, clientDetails4, clientDetails5, clientDetails6));
        clientDetailsActual.removeIf(clientDetails -> clientDetails.getClientDetailsId().equals(6L));
        assertEquals(clientDetailsExpected, clientDetailsActual);
    }
}