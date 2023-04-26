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
        list.stream().filter(l -> l.message() !=null || l.callbackQuery()!=null).forEach(this::handleUpdate);
        return CONFIRMED_UPDATES_ALL;
    }

    //Обработчик.
    private void handleUpdate(Update update){
        //Без обёртки периодически выбрасывает NPE.
        try {
            if (update.message() != null && update.message().text().startsWith("/start")){
                InlineKeyboardButton cat = new InlineKeyboardButton("Приют для кошек").callbackData("/cat");
                InlineKeyboardButton dog = new InlineKeyboardButton("Приют для собак").callbackData("/dog");
                InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup(cat,dog);
                this.telegramBot.execute(new SendMessage(update.message().chat().id(),
                        "Приветствую пользователь, это телеграмм-бот для приюта домашних животных.\n\n Пожалуйста выбери приют:").replyMarkup(keyboard));
                return;
            }
            if (update.callbackQuery().data().startsWith("/cat")){
                keyboardCat.handleCatShelter(update);
                return;
            }
            if (update.callbackQuery().data().startsWith("/infoCat")){
                keyboardCat.userInfoCatButtons(update);
                return;
            }
            if (update.callbackQuery().data().startsWith("/takeCat")){
                keyboardCat.userTakeCatButtons(update);
                return;
            }
            if (update.callbackQuery().data().startsWith("/visitingCat")){
                keyboardCat.handleVisitingCatShelter(update);
                return;
            }
            if (update.callbackQuery().data().startsWith("/registrationPassCat")){
                keyboardCat.handleRegistrationPassCatShelter(update);
                return;
            }
            if (update.callbackQuery().data().startsWith("/safetyPrecautionsCat")){
                keyboardCat.handleSafetyPrecautionsCatShelter(update);
                return;
            }
            if (update.callbackQuery().data().startsWith("/datingRulesCat")){
                keyboardCat.handleDatingRulesCatShelter(update);
                return;
            }
            if (update.callbackQuery().data().startsWith("/listDocumentsCat")){
                keyboardCat.handleListDocumentsCatShelter(update);
                return;
            }
            if (update.callbackQuery().data().startsWith("/transportingCat")){
                keyboardCat.handleTransportingCatShelter(update);
                return;
            }
            if (update.callbackQuery().data().startsWith("/recommendationsKitten")){
                keyboardCat.handleRecommendationsKittenShelter(update);
                return;
            }
            if (update.callbackQuery().data().startsWith("/adultCat")){
                keyboardCat.handleAdultCatShelter(update);
                return;
            }
            if (update.callbackQuery().data().startsWith("/withDisabilitiesCat")){
                keyboardCat.handleWithDisabilitiesCatShelter(update);
                return;
            }
            if (update.callbackQuery().data().startsWith("/reasonsRefusalCat")){
                keyboardCat.handleReasonsRefusalCatShelter(update);
                return;
            }
            if (update.callbackQuery().data().startsWith("/reportCat")){
                keyboardCat.handleReportCatShelter(update);
                return;
            }
            if (update.callbackQuery().data().startsWith("/volunteerCat")){
                keyboardCat.handleVolunteerCatShelter(update);
            }



            if (update.callbackQuery().data().startsWith("/dog")){
                keyboardDog.handleDogShelter(update);
                return;
            }
            if (update.callbackQuery().data().startsWith("/infoDog")){
                keyboardDog.userInfoDogButtons(update);
                return;
            }
            if (update.callbackQuery().data().startsWith("/take_dog")){
                keyboardDog.userTakeDogButtons(update);
                return;
            }
            if (update.callbackQuery().data().startsWith("/reportDog")){
                keyboardDog.handleReportShelter(update);
                return;
            }
            if (update.callbackQuery().data().startsWith("/volunteerDog")){
                keyboardDog.handleVolunteerShelter(update);
            }
        }catch (NullPointerException e){
            log.error("Была ошибка NullPointerException" + e.getMessage());
        }
    }
}