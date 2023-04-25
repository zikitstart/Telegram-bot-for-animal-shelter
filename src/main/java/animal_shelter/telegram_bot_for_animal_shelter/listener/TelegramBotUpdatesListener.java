package animal_shelter.telegram_bot_for_animal_shelter.listener;

import animal_shelter.telegram_bot_for_animal_shelter.service.keyboard.KeyboardMenu;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final KeyboardMenu keyboardMenu;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, KeyboardMenu keyboardMenu) {
        this.keyboardMenu = keyboardMenu;
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
            if (update.message().text().equals("/start")){
                keyboardMenu.handleHello(update);
            }
            if (update.message().text().equals("/cat")){
                keyboardMenu.handleCatShelter(update);
            }
            if (update.message().text().equals("/dog")){
                keyboardMenu.handleDogShelter(update);
            }
            if (update.message().text().equals("/info")){
                keyboardMenu.handleInfoShelter(update);
            }
            if (update.message().text().equals("/take_cat")){
                keyboardMenu.handleTakeCatShelter(update);
            }
            if (update.message().text().equals("/take_dog")){
                keyboardMenu.handleTakeDogShelter(update);
            }
            if (update.message().text().equals("/report")){
                keyboardMenu.handleReportShelter(update);
            }
            if (update.message().text().equals("/volunteer")){
                keyboardMenu.handleVolunteerShelter(update);
            }
        }catch (NullPointerException e){
            e.getMessage();
        }
    }
}