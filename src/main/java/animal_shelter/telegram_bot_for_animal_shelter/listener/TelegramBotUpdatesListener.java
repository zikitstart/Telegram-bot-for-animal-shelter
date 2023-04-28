package animal_shelter.telegram_bot_for_animal_shelter.listener;

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

    public TelegramBotUpdatesListener(TelegramBot telegramBot, KeyboardCat keyboardCat, KeyboardDog keyboardDog) {
        this.telegramBot = telegramBot;
        this.keyboardCat = keyboardCat;
        this.keyboardDog = keyboardDog;
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> list) {
        list.stream().filter(l -> l.message() != null || l.callbackQuery() != null).forEach(this::handleUpdate);
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
        try {
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
                case "/detailedInfoCat" -> keyboardCat.handleDetailedInfoCatShelter(update);
                case "/visitingCat" -> keyboardCat.handleVisitingCatShelter(update);
                case "/registrationPassCat" -> keyboardCat.handleRegistrationPassCatShelter(update);
                case "/safetyPrecautionsCat" -> keyboardCat.handleSafetyPrecautionsCatShelter(update);
                case "/datingRulesCat" -> keyboardCat.handleDatingRulesCatShelter(update);
                case "/listDocumentsCat" -> keyboardCat.handleListDocumentsCatShelter(update);
                case "/transportingCat" -> keyboardCat.handleTransportingCatShelter(update);
                case "/recommendationsKitten" -> keyboardCat.handleRecommendationsKittenShelter(update);
                case "/adultCat" -> keyboardCat.handleAdultCatShelter(update);
                case "/withDisabilitiesCat" -> keyboardCat.handleWithDisabilitiesCatShelter(update);
                case "/reasonsRefusalCat" -> keyboardCat.handleReasonsRefusalCatShelter(update);
                case "/volunteerCat" -> keyboardCat.handleVolunteerCatShelter(update);
                case "/dog" -> keyboardDog.menuButtonsDogShelter(update);
                case "/infoDog" -> keyboardDog.menuButtonsInfoDogShelter(update);
                case "/takeDog" -> keyboardDog.menuButtonsTakeDogShelter(update);
                case "/reportDog" -> keyboardDog.menuButtonsReportDogShelter(update);
                case "/detailedInfoDog" -> keyboardDog.handleDetailedInfoDogShelter(update);
                case "/visitingDog" -> keyboardDog.handleVisitingDogShelter(update);
                case "/registrationPassDog" -> keyboardDog.handleRegistrationPassDogShelter(update);
                case "/safetyPrecautionsDog" -> keyboardDog.handleSafetyPrecautionsDogShelter(update);
                case "/datingRulesDog" -> keyboardDog.handleDatingRulesDogShelter(update);
                case "/listDocumentsDog" -> keyboardDog.handleListDocumentsDogShelter(update);
                case "/transportingDog" -> keyboardDog.handleTransportingDogShelter(update);
                case "/recommendationsPuppy" -> keyboardDog.handleRecommendationsPuppyShelter(update);
                case "/adultDog" -> keyboardDog.handleAdultDogShelter(update);
                case "/withDisabilitiesDog" -> keyboardDog.handleWithDisabilitiesDogShelter(update);
                case "/reasonsRefusalDog" -> keyboardDog.handleReasonsRefusalDogShelter(update);
                case "/tipsHandlerDog" -> keyboardDog.handleTipsHandlerDogShelter(update);
                case "/recommendationsHandlerDog" -> keyboardDog.handleRecommendationsHandlerDogShelter(update);
                case "/volunteerDog" -> keyboardDog.handleVolunteerDogShelter(update);
            }
        } catch (NullPointerException e) {
            log.error("Была ошибка NullPointerException" + e.getMessage());
        }
    }
}