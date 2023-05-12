package animal_shelter.telegram_bot_for_animal_shelter.service.impl;

import animal_shelter.telegram_bot_for_animal_shelter.model.ClientDetails;
import animal_shelter.telegram_bot_for_animal_shelter.model.enums.Status;
import animal_shelter.telegram_bot_for_animal_shelter.repository.ClientDetailsRepository;
import animal_shelter.telegram_bot_for_animal_shelter.repository.ReportRepository;
import animal_shelter.telegram_bot_for_animal_shelter.service.ClientDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {
    private final ClientDetailsRepository clientDetailsRepository;

    private final ReportRepository reportRepository;

    public ClientDetailsServiceImpl(ClientDetailsRepository clientDetailsRepository, ReportRepository reportRepository) {
        this.clientDetailsRepository = clientDetailsRepository;
        this.reportRepository = reportRepository;
    }

    // Получаем список клиентов, которые проходят добавочный срок
    @Override
    public List<ClientDetails> getClientsWithExtraPeriod() {
        List<ClientDetails> clients = new ArrayList<>();

        clients.addAll(clientDetailsRepository.findClientDetailsByStatus(Status.EXTRA_14));
        clients.addAll(clientDetailsRepository.findClientDetailsByStatus(Status.EXTRA_30));

        return clients;
    }

    // Получаем список клиентов, которые должны каждый день присылать отчеты
    @Override
    public List<ClientDetails> getActualClients() {
        List<ClientDetails> clients = new ArrayList<>();
        clients.addAll(getClientsWithExtraPeriod());
        clients.addAll(clientDetailsRepository.findClientDetailsByStatus(Status.TRIAL));

        return clients;
    }

    // Получаем список клиентов, которые не прислали отчет за сегодняшний день (метод будет выполняться в 00:00 следующего дня)
    @Override
    public List<ClientDetails> getClientsWithoutTodayReport() {
        return getActualClients().stream()
                .filter(client ->
                        reportRepository.getReportByPetIdAndDateOfReport(
                                client.getPetId().getPetId(), LocalDate.now().minusDays(1)) != null
                )
                .toList();
    }

    // Получаем список клиентов, которые не присылали отчеты последние два дня (метод будет выполняться в 00:00 следующего дня)
    @Override
    public List<ClientDetails> getClientsWithoutReportsForLastTwoDays() {
        return getClientsWithoutTodayReport().stream()
                .filter(client ->
                        reportRepository.getReportByPetIdAndDateOfReport(
                                client.getPetId().getPetId(), LocalDate.now().minusDays(2)) != null
                )
                .toList();
    }

    // Получаем список клиентов, которые сегодня закончили свой TRIAL период
    @Override
    public List<ClientDetails> getClientsWhoEndTheirTrialPeriod() {
        List<ClientDetails> clients = clientDetailsRepository.findClientDetailsByStatus(Status.TRIAL).stream()
                .filter(client -> client.getStartDate().equals(LocalDate.now().minusDays(30)))
                .toList();

        clients.forEach(client -> {
            client.setWasNotifiedOfStatusChange(false);
            client.setPrevStatus(Status.TRIAL);
            client.setStatus(Status.WAIT_FOR_DECISION);

            clientDetailsRepository.save(client);
        });

        return clients;
    }

    // Получаем список клиентов, которые сегодня закончили свой EXTRA_14 период
    @Override
    public List<ClientDetails> getClientsWhoEndTheirExtra14Period() {
        List<ClientDetails> clients = clientDetailsRepository.findClientDetailsByStatus(Status.EXTRA_14).stream()
                .filter(client -> client.getStartDate().equals(LocalDate.now().minusDays(44)))
                .toList();

        clients.forEach(client -> {
            client.setWasNotifiedOfStatusChange(false);
            client.setPrevStatus(Status.EXTRA_14);
            client.setStatus(Status.WAIT_FOR_DECISION);

            clientDetailsRepository.save(client);
        });

        return clients;
    }

    // Получаем список клиентов, которые сегодня закончили свой EXTRA_30 период
    @Override
    public List<ClientDetails> getClientsWhoEndTheirExtra30Period() {
        List<ClientDetails> clients = clientDetailsRepository.findClientDetailsByStatus(Status.EXTRA_30).stream()
                .filter(client -> client.getStartDate().equals(LocalDate.now().minusDays(60)))
                .toList();

        clients.forEach(client -> {
            client.setWasNotifiedOfStatusChange(false);
            client.setPrevStatus(Status.EXTRA_30);
            client.setStatus(Status.WAIT_FOR_DECISION);

            clientDetailsRepository.save(client);
        });

        return clients;
    }

    @Override
    public List<ClientDetails> getClientsInStatusWaitFoForDecision(){
        return clientDetailsRepository.findClientDetailsByWasNotifiedOfStatusChangeAndStatus(false,Status.WAIT_FOR_DECISION);
    }

    // Получение списка клиентов, у которых волонтер сменил статус, для последующей отправки соответствующих сообщений
    @Override
    public List<ClientDetails> getClientsWhoMustGetDecision() {
        return clientDetailsRepository.findClientDetailsByWasNotifiedOfStatusChangeAndStatusNotLike(false, Status.WAIT_FOR_DECISION);
    }
}
