package animal_shelter.telegram_bot_for_animal_shelter.service;

import animal_shelter.telegram_bot_for_animal_shelter.model.Client;
import animal_shelter.telegram_bot_for_animal_shelter.model.ClientDetails;

import java.util.List;

public interface ClientDetailsService {
    ClientDetails getClientByClientId(Client clientId);

    List<ClientDetails> getClientsWithExtraPeriod();

    List<ClientDetails> getActualClients();

    List<ClientDetails> getClientsWithoutTodayReport();

    List<ClientDetails> getClientsWithoutReportsForLastTwoDays();

    List<ClientDetails> getClientsWhoEndTheirTrialPeriod();

    // Получаем список клиентов, которые сегодня закончили свой EXTRA_14 период
    List<ClientDetails> getClientsWhoEndTheirExtra14Period();

    List<ClientDetails> getClientsWhoEndTheirExtra30Period();

    List<ClientDetails> getClientsInStatusWaitFoForDecision();

    // Получение списка клиентов, у которых волонтер сменил статус, для последующей отправки соответствующих сообщений
    List<ClientDetails> getClientsWhoMustGetDecision();
}
