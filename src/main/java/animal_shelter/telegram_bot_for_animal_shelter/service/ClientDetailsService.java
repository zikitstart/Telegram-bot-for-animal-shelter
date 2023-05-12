package animal_shelter.telegram_bot_for_animal_shelter.service;

import animal_shelter.telegram_bot_for_animal_shelter.model.ClientDetails;

import java.util.List;

public interface ClientDetailsService {
    void updateClient(ClientDetails clientDetails);

    List<ClientDetails> getClientsWithExtraPeriod();

    List<ClientDetails> getActualClients();

    List<ClientDetails> getClientsWithoutTodayReport();

    List<ClientDetails> getClientsWithoutReportsForLastTwoDays();

    List<ClientDetails> getClientsWhoEndTheirTrialPeriod();

    void setClientsStatusesWhoEndTheirTrialPeriodToWaitForDecision();

    // Получаем список клиентов, которые сегодня закончили свой EXTRA_14 период
    List<ClientDetails> getClientsWhoEndTheirExtra14Period();

    void setClientsStatusesWhoEndTheirExtra14PeriodToWaitForDecision();

    List<ClientDetails> getClientsWhoEndTheirExtra30Period();

    void setClientsStatusesWhoEndTheirExtra30PeriodToWaitForDecision();

    List<ClientDetails> getClientsInStatusWaitForDecision();

    // Получение списка клиентов, у которых волонтер сменил статус, для последующей отправки соответствующих сообщений
    List<ClientDetails> getClientsWhoMustGetNotificationAboutStatusChange();
}
