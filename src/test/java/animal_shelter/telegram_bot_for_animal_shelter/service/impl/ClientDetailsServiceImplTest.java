package animal_shelter.telegram_bot_for_animal_shelter.service.impl;

import animal_shelter.telegram_bot_for_animal_shelter.model.ClientDetails;
import animal_shelter.telegram_bot_for_animal_shelter.model.Pet;
import animal_shelter.telegram_bot_for_animal_shelter.model.enums.Status;
import animal_shelter.telegram_bot_for_animal_shelter.repository.ClientDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
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
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientDetailsServiceImplTest {
    @InjectMocks
    private ClientDetailsServiceImpl clientDetailsService;
    @Mock
    private ClientDetailsRepository clientDetailsRepository;

    @BeforeEach
    void setUp() {

    }

    private ClientDetails getTestCustomer(long id, Pet petId, LocalDate date, Status status) {
        ClientDetails clientDetails = new ClientDetails();
        clientDetails.setClientDetailsId(id);
        clientDetails.setPetId(petId);
        clientDetails.setStartDate(date);
        clientDetails.setStatus(status);
        return clientDetails;
    }

    @Test
    void getClientsWithExtraPeriod() {
        ClientDetails clientDetail2 = getTestCustomer(2L,new Pet(), LocalDate.of(2023,5,11), EXTRA_14);
        ClientDetails clientDetail3 = getTestCustomer(3L,new Pet(), LocalDate.now(), EXTRA_30);
        List<ClientDetails> expected = new ArrayList<>(List.of(clientDetail2,clientDetail3));
        when(clientDetailsRepository.findClientDetailsByStatus(EXTRA_14)).thenReturn(Collections.singletonList(clientDetail2));
        when(clientDetailsRepository.findClientDetailsByStatus(EXTRA_30)).thenReturn(Collections.singletonList(clientDetail3));
        List<ClientDetails> actual = clientDetailsService.getClientsWithExtraPeriod();
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    void getActualClients() {
        ClientDetails clientDetail1 = getTestCustomer(1L,new Pet(), LocalDate.now(), TRIAL);
        ClientDetails clientDetail2 = getTestCustomer(2L,new Pet(), LocalDate.now(), EXTRA_14);
        ClientDetails clientDetail3 = getTestCustomer(3L,new Pet(), LocalDate.now(), EXTRA_30);
        List<ClientDetails> expected = new ArrayList<>(List.of(clientDetail1,clientDetail2,clientDetail3));
        when(clientDetailsRepository.findClientDetailsByStatus(TRIAL)).thenReturn(Collections.singletonList(clientDetail1));
        when(clientDetailsRepository.findClientDetailsByStatus(EXTRA_14)).thenReturn(Collections.singletonList(clientDetail2));
        when(clientDetailsRepository.findClientDetailsByStatus(EXTRA_30)).thenReturn(Collections.singletonList(clientDetail3));
        List<ClientDetails> actual = clientDetailsService.getActualClients();
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

//    @Test
//    void getClientsWithoutTodayReport() {
//
//    }
//
//    @Test
//    void getClientsWithoutReportsForLastTwoDays() {
//    }
//
//    @Test
//    void getClientsWhoEndTheirTrialPeriod() {
//    }
//
//    @Test
//    void getClientsWhoEndTheirExtra14Period() {
//    }
//
//    @Test
//    void getClientsWhoEndTheirExtra30Period() {
//    }
//
//    @Test
//    void getClientsWhoMustGetDecision() {
//    }
}