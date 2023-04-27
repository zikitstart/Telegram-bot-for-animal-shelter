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
                case "/selectShelter":
                    if (update.callbackQuery().data().startsWith("/selectShelter")) {
                        selectShelterMenu(update);
                    }
                case "/cat":
                    if (update.callbackQuery().data().startsWith("/cat")) {
                        keyboardCat.menuButtonsCatShelter(update);
                    }
                case "/infoCat":
                    if (update.callbackQuery().data().startsWith("/infoCat")) {
                        keyboardCat.menuButtonsInfoCatShelter(update);
                    }
                case "/takeCat":
                    if (update.callbackQuery().data().startsWith("/takeCat")) {
                        keyboardCat.menuButtonsTakeCatShelter(update);
                    }
                case "/reportCat":
                    if (update.callbackQuery().data().startsWith("/reportCat")) {
                        keyboardCat.menuButtonsReportCatShelter(update);
                    }
                case "/detailedInfoCat":
                    if (update.callbackQuery().data().startsWith("/detailedInfoCat")) {
                        keyboardCat.handleDetailedInfoCatShelter(update);
                    }
                case "/visitingCat":
                    if (update.callbackQuery().data().startsWith("/visitingCat")) {
                        keyboardCat.handleVisitingCatShelter(update);
                    }
                case "/registrationPassCat":
                    if (update.callbackQuery().data().startsWith("/registrationPassCat")) {
                        keyboardCat.handleRegistrationPassCatShelter(update);
                    }
                case "/safetyPrecautionsCat":
                    if (update.callbackQuery().data().startsWith("/safetyPrecautionsCat")) {
                        keyboardCat.handleSafetyPrecautionsCatShelter(update);
                    }
                case "/datingRulesCat":
                    if (update.callbackQuery().data().startsWith("/datingRulesCat")) {
                        keyboardCat.handleDatingRulesCatShelter(update);
                    }
                case "/listDocumentsCat":
                    if (update.callbackQuery().data().startsWith("/listDocumentsCat")) {
                        keyboardCat.handleListDocumentsCatShelter(update);
                    }
                case "/transportingCat":
                    if (update.callbackQuery().data().startsWith("/transportingCat")) {
                        keyboardCat.handleTransportingCatShelter(update);
                    }
                case "/recommendationsKitten":
                    if (update.callbackQuery().data().startsWith("/recommendationsKitten")) {
                        keyboardCat.handleRecommendationsKittenShelter(update);
                    }
                case "/adultCat":
                    if (update.callbackQuery().data().startsWith("/adultCat")) {
                        keyboardCat.handleAdultCatShelter(update);
                    }
                case "/withDisabilitiesCat":
                    if (update.callbackQuery().data().startsWith("/withDisabilitiesCat")) {
                        keyboardCat.handleWithDisabilitiesCatShelter(update);
                    }
                case "/reasonsRefusalCat":
                    if (update.callbackQuery().data().startsWith("/reasonsRefusalCat")) {
                        keyboardCat.handleReasonsRefusalCatShelter(update);
                    }
                case "/volunteerCat":
                    if (update.callbackQuery().data().startsWith("/volunteerCat")) {
                        keyboardCat.handleVolunteerCatShelter(update);
                    }
                case "/dog":
                    if (update.callbackQuery().data().startsWith("/dog")) {
                        keyboardDog.menuButtonsDogShelter(update);
                    }
                case "/infoDog":
                    if (update.callbackQuery().data().startsWith("/infoDog")) {
                        keyboardDog.menuButtonsInfoDogShelter(update);
                    }
                case "/takeDog":
                    if (update.callbackQuery().data().startsWith("/takeDog")) {
                        keyboardDog.menuButtonsTakeDogShelter(update);
                    }
                case "/reportDog":
                    if (update.callbackQuery().data().startsWith("/reportDog")) {
                        keyboardDog.menuButtonsReportDogShelter(update);
                    }
                case "/detailedInfoDog":
                    if (update.callbackQuery().data().startsWith("/detailedInfoDog")) {
                        keyboardDog.handleDetailedInfoDogShelter(update);
                    }
                case "/visitingDog":
                    if (update.callbackQuery().data().startsWith("/visitingDog")) {
                        keyboardDog.handleVisitingDogShelter(update);
                    }
                case "/registrationPassDog":
                    if (update.callbackQuery().data().startsWith("/registrationPassDog")) {
                        keyboardDog.handleRegistrationPassDogShelter(update);
                    }
                case "/safetyPrecautionsDog":
                    if (update.callbackQuery().data().startsWith("/safetyPrecautionsDog")) {
                        keyboardDog.handleSafetyPrecautionsDogShelter(update);
                    }
                case "/datingRulesDog":
                    if (update.callbackQuery().data().startsWith("/datingRulesDog")) {
                        keyboardDog.handleDatingRulesDogShelter(update);
                    }
                case "/listDocumentsDog":
                    if (update.callbackQuery().data().startsWith("/listDocumentsDog")) {
                        keyboardDog.handleListDocumentsDogShelter(update);
                    }
                case "/transportingDog":
                    if (update.callbackQuery().data().startsWith("/transportingDog")) {
                        keyboardDog.handleTransportingDogShelter(update);
                    }
                case "/recommendationsPuppy":
                    if (update.callbackQuery().data().startsWith("/recommendationsPuppy")) {
                        keyboardDog.handleRecommendationsPuppyShelter(update);
                    }
                case "/adultDog":
                    if (update.callbackQuery().data().startsWith("/adultDog")) {
                        keyboardDog.handleAdultDogShelter(update);
                    }
                case "/withDisabilitiesDog":
                    if (update.callbackQuery().data().startsWith("/withDisabilitiesDog")) {
                        keyboardDog.handleWithDisabilitiesDogShelter(update);
                    }
                case "/reasonsRefusalDog":
                    if (update.callbackQuery().data().startsWith("/reasonsRefusalDog")) {
                        keyboardDog.handleReasonsRefusalDogShelter(update);
                    }
                case "/tipsHandlerDog":
                    if (update.callbackQuery().data().startsWith("/tipsHandlerDog")) {
                        keyboardDog.handleTipsHandlerDogShelter(update);
                    }
                case "/recommendationsHandlerDog":
                    if (update.callbackQuery().data().startsWith("/recommendationsHandlerDog")) {
                        keyboardDog.handleRecommendationsHandlerDogShelter(update);
                    }
                case "/volunteerDog":
                    if (update.callbackQuery().data().startsWith("/volunteerDog")) {
                        keyboardDog.handleVolunteerDogShelter(update);
                    }
            }
        } catch (NullPointerException e) {
            log.error("Была ошибка NullPointerException" + e.getMessage());
        }
    }
}