package animal_shelter.telegram_bot_for_animal_shelter.listener;

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

    private final TelegramBot telegramBot;

    public TelegramBotUpdatesListener(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
        this.telegramBot.setUpdatesListener(this);
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
            handleHello(update);
            handleCatShelter(update);
            handleDogShelter(update);
        }catch (NullPointerException e){
            e.getMessage();
        }
    }
    //Приветственное сообщение. Обработка /start.
    private void handleHello(Update update){
        if (update.message()!=null && update.message().text().startsWith("/start")){
            this.telegramBot.execute(
                    new SendMessage(update.message().chat().id(),
                            "Приветствую пользователь, это телеграмм-бот для приюта домашних животных."));
            buttonsCatAndDog(update);
        }
    }
    //Кнопки выбора приюта.
    private void buttonsCatAndDog(Update update) {
        InlineKeyboardButton cat = new InlineKeyboardButton("Приют для кошек").callbackData("/cat");
        InlineKeyboardButton dog = new InlineKeyboardButton("Приют для собак").callbackData("/dog");
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup(cat,dog);
        this.telegramBot.execute(new SendMessage(update.message().chat().id(),"Пожалуйста выбери приют:").replyMarkup(keyboard));
    }
    //Ответное сообщение на кнопку "Приют для кошек".
    private void handleCatShelter(Update update){
        if (update.callbackQuery().data().startsWith("/cat")){
            this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),"Вы выбрали приют для кошек."));
        }
    }
    //Ответное сообщение на кнопку "Приют для собак".
    private void handleDogShelter(Update update){
        if (update.callbackQuery().data().startsWith("/dog")){
            this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),"Вы выбрали приют для собак."));
        }
    }
    /*private void userRequestProcessingButtons(Update update) {
        InlineKeyboardButton informationShelter = new InlineKeyboardButton("Узнать информацию о приюте").callbackData("/info");
        InlineKeyboardButton takeAnimal = new InlineKeyboardButton("Как взять животное из приюта").callbackData("/take");
        InlineKeyboardButton petReport = new InlineKeyboardButton("Прислать отчет о питомце").callbackData("/report");
        InlineKeyboardButton callVolunteer = new InlineKeyboardButton("Позвать волонтера ").callbackData("/volunteer");
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup(informationShelter,takeAnimal,petReport,callVolunteer);
        this.telegramBot.execute(new SendMessage(update.message().chat().id(),"Выберите пункт меню:").replyMarkup(keyboard));
    }*/
}
