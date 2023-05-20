package animal_shelter.telegram_bot_for_animal_shelter.service;

import animal_shelter.telegram_bot_for_animal_shelter.model.Client;
import animal_shelter.telegram_bot_for_animal_shelter.model.ClientDetails;
import animal_shelter.telegram_bot_for_animal_shelter.model.enums.Status;

import java.util.List;

public interface ClientDetailsService {
    ClientDetails getClientByClientId(Client clientId);

    ClientDetails createClientDetails(Long clientId, Long petId, Status status, String startDate, boolean wasNotifiedOfStatusChange);

    void updateClientDetails(ClientDetails clientDetails);

    ClientDetails updateClientDetails(Long clientId, Long petId, Status status, String startDate, boolean wasNotifiedOfStatusChange);

    ClientDetails getClientDetailsByClientDetailsId(Long clientDetailsId);

    List<ClientDetails> getClientsWithExtraPeriod();

    List<ClientDetails> getActualClients();

    List<ClientDetails> getClientsWithoutTodayReport();

    List<ClientDetails> getClientsWithoutReportsForLastTwoDays();

    List<ClientDetails> getClientsWhoEndTheirTrialPeriod();

    void setClientsStatusesWhoEndTheirTrialPeriodToWaitForDecision();

    List<ClientDetails> getClientsWhoEndTheirExtra14Period();

    void setClientsStatusesWhoEndTheirExtra14PeriodToWaitForDecision();

    List<ClientDetails> getClientsWhoEndTheirExtra30Period();

    void setClientsStatusesWhoEndTheirExtra30PeriodToWaitForDecision();

    List<ClientDetails> getClientsInStatusWaitForDecision();

    List<ClientDetails> getClientsWhoMustGetNotificationAboutStatusChange();

    ClientDetails getClientDetailsByClientIdAndPetId(Long clientId, Long petId);

    ClientDetails deleteClientDetails(Long clientDetailsId);
}
