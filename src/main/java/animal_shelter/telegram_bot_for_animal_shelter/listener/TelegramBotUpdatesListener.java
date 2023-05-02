package animal_shelter.telegram_bot_for_animal_shelter.listener;

import animal_shelter.telegram_bot_for_animal_shelter.repository.InfoRepository;
import animal_shelter.telegram_bot_for_animal_shelter.repository.ShelterRepository;
import animal_shelter.telegram_bot_for_animal_shelter.service.keyboard.KeyboardDog;
import animal_shelter.telegram_bot_for_animal_shelter.service.keyboard.KeyboardCat;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
//Обработка сообщений
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final TelegramBot telegramBot;
    private final KeyboardCat keyboardCat;
    private final KeyboardDog keyboardDog;
    private final ShelterRepository shelterRepository;
    private final InfoRepository infoRepository;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, KeyboardCat keyboardCat, KeyboardDog keyboardDog, ShelterRepository shelterRepository, InfoRepository infoRepository) {
        this.telegramBot = telegramBot;
        this.keyboardCat = keyboardCat;
        this.keyboardDog = keyboardDog;
        this.shelterRepository = shelterRepository;
        this.infoRepository = infoRepository;
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> list) {
        try {
            list.stream().filter(l -> l.message() != null || l.callbackQuery() != null).forEach(this::handleUpdate);
        }catch (Exception e){
            e.printStackTrace();
        }
        return CONFIRMED_UPDATES_ALL;
    }

    //Стартовое приветствие (Общий метод на два приюта)
    public void startMenu(Update update) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(
                new InlineKeyboardButton("Усыновить питомца").callbackData("/selectShelter"));
        this.telegramBot.execute(new SendMessage(update.message().chat().id(), "Привет я бот-помошник!!!\n\nЕсли у тебя есть какие-то вопросы или желание усыновить питомца\n\nЖми кнопку!!!").replyMarkup(inlineKeyboardMarkup));
    }

    //Выбор приюта (Общий метод на два приюта)
    public void selectShelterMenu(Update update) {
        InlineKeyboardButton cat = new InlineKeyboardButton("Приют для кошек").callbackData("/cat");
        InlineKeyboardButton dog = new InlineKeyboardButton("Приют для собак").callbackData("/dog");
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup(cat, dog);
        this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                "Приветствую пользователь, это телеграмм-бот для приюта домашних животных.\n\n Пожалуйста выбери приют:").replyMarkup(keyboard));
    }

    //Обработчик
    private void handleUpdate(Update update) {
        if (update.message() != null && update.message().text().startsWith("/start")) {
            startMenu(update);
            return;
        }
        switch (update.callbackQuery().data()) {
            case "/selectShelter" -> selectShelterMenu(update);
            case "/cat" -> keyboardCat.menuButtonsCatShelter(update);
            case "/infoCat" -> keyboardCat.menuButtonsInfoCatShelter(update);
            case "/takeCat" -> keyboardCat.menuButtonsTakeCatShelter(update);
            case "/reportCat" -> keyboardCat.menuButtonsReportCatShelter(update);
            case "/detailedInfoCat" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (shelterRepository.getSheltersByShelterId(1L).getDescription())));
            case "/visitingCat" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (shelterRepository.getSheltersByShelterId(1L).getAddress()+"\n"+shelterRepository.getSheltersByShelterId(1L).getSchedule()+"\n"+shelterRepository.getSheltersByShelterId(1L).getDrivingDirections())));
            case "/registrationPassCat" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (shelterRepository.getSheltersByShelterId(1L).getContacts())));
            case "/safetyPrecautionsCat" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (shelterRepository.getSheltersByShelterId(1L).getRules())));
            case "/datingRulesCat" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (infoRepository.getInfoByInfoId(1L).getDatingRulesPet())));
            case "/listDocumentsCat" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (infoRepository.getInfoByInfoId(1L).getListDocumentsPet())));
            case "/transportingCat" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (infoRepository.getInfoByInfoId(1L).getTransportingPet())));
            case "/recommendationsKitten" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (infoRepository.getInfoByInfoId(1L).getRecommendationsLittlePet())));
            case "/adultCat" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (infoRepository.getInfoByInfoId(1L).getRecommendationsAdultPet())));
            case "/withDisabilitiesCat" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (infoRepository.getInfoByInfoId(1L).getRecommendationsWithDisabilitiesPet())));
            case "/reasonsRefusalCat" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (infoRepository.getInfoByInfoId(1L).getReasonsRefusalPet())));
            case "/volunteerCat" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    "Ожидайте первый освободившийся волонтёр свяжется с вами."));

            case "/dog" -> keyboardDog.menuButtonsDogShelter(update);
            case "/infoDog" -> keyboardDog.menuButtonsInfoDogShelter(update);
            case "/takeDog" -> keyboardDog.menuButtonsTakeDogShelter(update);
            case "/reportDog" -> keyboardDog.menuButtonsReportDogShelter(update);
            case "/detailedInfoDog" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (shelterRepository.getSheltersByShelterId(2L).getDescription())));
            case "/visitingDog" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (shelterRepository.getSheltersByShelterId(2L).getAddress()+"\n"+shelterRepository.getSheltersByShelterId(2L).getSchedule()+"\n"+shelterRepository.getSheltersByShelterId(2L).getDrivingDirections())));
            case "/registrationPassDog" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (shelterRepository.getSheltersByShelterId(2L).getContacts())));
            case "/safetyPrecautionsDog" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (shelterRepository.getSheltersByShelterId(2L).getRules())));
            case "/datingRulesDog" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (infoRepository.getInfoByInfoId(2L).getDatingRulesPet())));
            case "/listDocumentsDog" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (infoRepository.getInfoByInfoId(2L).getListDocumentsPet())));
            case "/transportingDog" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (infoRepository.getInfoByInfoId(2L).getTransportingPet())));
            case "/recommendationsPuppy" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (infoRepository.getInfoByInfoId(2L).getRecommendationsLittlePet())));
            case "/adultDog" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (infoRepository.getInfoByInfoId(2L).getRecommendationsAdultPet())));
            case "/withDisabilitiesDog" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (infoRepository.getInfoByInfoId(2L).getRecommendationsWithDisabilitiesPet())));
            case "/reasonsRefusalDog" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (infoRepository.getInfoByInfoId(2L).getReasonsRefusalPet())));
            case "/tipsHandlerDog" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (infoRepository.getInfoByInfoId(2L).getTipsHandlerDog())));
            case "/recommendationsHandlerDog" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (infoRepository.getInfoByInfoId(2L).getRecommendationsHandlerDog())));
            case "/volunteerDog" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    "Ожидайте первый освободившийся волонтёр свяжется с вами."));
        }
    }
}