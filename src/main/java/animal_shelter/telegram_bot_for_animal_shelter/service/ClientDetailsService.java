package animal_shelter.telegram_bot_for_animal_shelter.service;

import animal_shelter.telegram_bot_for_animal_shelter.model.Client;
import animal_shelter.telegram_bot_for_animal_shelter.model.ClientDetails;

import java.util.List;

public interface ClientDetailsService {
    ClientDetails getClientByClientId(Client clientId);

    void createClientDetails(ClientDetails clientDetails);

    void updateClientDetails(ClientDetails clientDetails);

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
}
