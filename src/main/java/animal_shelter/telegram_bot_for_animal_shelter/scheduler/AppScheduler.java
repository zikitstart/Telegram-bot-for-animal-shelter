package animal_shelter.telegram_bot_for_animal_shelter.scheduler;

import animal_shelter.telegram_bot_for_animal_shelter.model.Shelter;
import animal_shelter.telegram_bot_for_animal_shelter.model.Volunteer;
import animal_shelter.telegram_bot_for_animal_shelter.service.ClientDetailsService;
import animal_shelter.telegram_bot_for_animal_shelter.service.ShelterService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

@EnableScheduling
public class AppScheduler {
    private final ClientDetailsService clientDetailsService;

    private final ShelterService shelterService;

    private final TelegramBot telegramBot;

    private final Random random = new Random();

    public AppScheduler(ClientDetailsService clientDetailsService, ShelterService shelterService, TelegramBot telegramBot) {
        this.clientDetailsService = clientDetailsService;
        this.shelterService = shelterService;
        this.telegramBot = telegramBot;
    }

    @Scheduled(cron = "${schedule.time}")
    public void doScheduleTasks() {
        // Отправляем напоминания тем пользователям, которые не прислали отчет за последний день
        clientDetailsService.getClientsWithoutTodayReport()
                .forEach(client ->
                        this.telegramBot.execute(
                                new SendMessage(client.getClientId().getChatId(),
                                        ConstantAnswers.REMAINDER_ANSWER)
                        ));

        // Просим волонтеров связаться с пользователями, которые не присылали отчеты за последние два дня
        Map<Shelter, Set<Volunteer>> shelterVolunteers = shelterService.getAllSheltersAndTheirVolunteers();
        clientDetailsService.getClientsWithoutReportsForLastTwoDays()
                .forEach(client -> {
                    List<Volunteer> volunteers = shelterVolunteers.get(client.getPetId().getShelterId()).stream().toList();
                    this.telegramBot.execute(
                            new SendMessage(volunteers.get(random.nextInt(volunteers.size())).getChatId(),
                                    ConstantAnswers.ASK_VOLUNTEER_CONTACT_WITH_CLIENT + client.getClientId().getSurname())
                    );
                });

        // Меняем статусы пользователей, которые успешно завершили пробные периоды
        clientDetailsService.setClientsStatusesWhoEndTheirTrialPeriodToWaitForDecision();
        clientDetailsService.setClientsStatusesWhoEndTheirExtra14PeriodToWaitForDecision();
        clientDetailsService.setClientsStatusesWhoEndTheirExtra30PeriodToWaitForDecision();

        // Просим волонтеров поставить новые статусы клиентам, закончившим свои пробные периоды
        clientDetailsService.getClientsInStatusWaitForDecision()
                .forEach(client -> {
                    List<Volunteer> volunteers = shelterVolunteers.get(client.getPetId().getShelterId()).stream().toList();
                    this.telegramBot.execute(
                            new SendMessage(volunteers.get(random.nextInt(volunteers.size())).getChatId(),
                                    ConstantAnswers.ASK_VOLUNTEER_SET_NEW_STATUS_TO_CLIENT + client.getClientId().getSurname())
                    );
                });

        // Рассылаем необходимые сообщения пользователям
        clientDetailsService.getClientsWhoMustGetNotificationAboutStatusChange()
                .forEach(client -> {
                    String message = "";
                    switch (client.getPrevStatus()) {
                        case ACCEPTED -> message = ConstantAnswers.ACCEPTED_CLIENT_MESSAGE;
                        case DENIED -> message = ConstantAnswers.DENIED_CLIENT_MESSAGE;
                        case EXTRA_14 -> message = ConstantAnswers.EXTRA_14_CLIENT_MESSAGE;
                        case EXTRA_30 -> message = ConstantAnswers.EXTRA_30_CLIENT_MESSAGE;
                    }
                    client.setWasNotifiedOfStatusChange(true);
                    clientDetailsService.updateClient(client);
                    this.telegramBot.execute(
                            new SendMessage(client.getClientId().getChatId(), message)
                    );
                });
    }
}
