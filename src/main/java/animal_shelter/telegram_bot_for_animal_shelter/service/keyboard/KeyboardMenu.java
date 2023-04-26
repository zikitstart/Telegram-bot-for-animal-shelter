package animal_shelter.telegram_bot_for_animal_shelter.service.keyboard;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;


@Service
public class KeyboardMenu {

    private final TelegramBot telegramBot;

    public KeyboardMenu(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    //Кнопки для выбора приюта
    private void buttonsCatAndDog(Update update) {
        InlineKeyboardButton cat = new InlineKeyboardButton("Приют для кошек").callbackData("/cat");
        InlineKeyboardButton dog = new InlineKeyboardButton("Приют для собак").callbackData("/dog");
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup(cat,dog);
        this.telegramBot.execute(new SendMessage(update.message().chat().id(),"Пожалуйста выбери приют:").replyMarkup(keyboard));
    }

    //Кнопки стартового меню приюта.
    //Необходимо сделать вертикальное меню. В горизонт больше 8 кнопок нельзя.
    //Возможно разделение метода на кошек и собак.
    private void userRequestProcessingButtons(Update update) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Узнать информацию о приюте").callbackData("/info"),
                        new InlineKeyboardButton("Как взять животное из приюта").callbackData("/take")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Прислать отчет о питомце").callbackData("/report"),
                        new InlineKeyboardButton("Позвать волонтера ").callbackData("/volunteer")
                });
        this.telegramBot.execute(new SendMessage(update.message().chat().id(),"Выберите пункт меню:").replyMarkup(inlineKeyboardMarkup));

    }
    //Кнопки информации о приюте
    //Необходимо сделать вертикальное меню. В горизонт больше 8 кнопок нельзя.
    private void userInfoButtons(Update update){

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Инфо для посещения").callbackData("/info_visiting"),
                        new InlineKeyboardButton("Оформление пропуска").callbackData("/registration_pass")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Техника безопасности").callbackData("/safety_precautions"),
                        new InlineKeyboardButton("Позвать волонтера ").callbackData("/volunteer")
                });
        this.telegramBot.execute(new SendMessage(update.message().chat().id(),"Для детальной информации выберите пункт меню:").replyMarkup(inlineKeyboardMarkup));
    }

    //Кнопки как взять кота из приюта.
    //Необходимо сделать вертикальное меню. В горизонт больше 8 кнопок нельзя.
    private void userTakeCatButtons(Update update){

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Правила знакомства с животным").callbackData("/dating_rules"),
                        new InlineKeyboardButton("Список документов").callbackData("/list_documents")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Рекомендации по транспортировке кота").callbackData("/transporting_cat"),
                        new InlineKeyboardButton("Рекомендации для котенка").callbackData("/recommendations_kitten")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Рекомендации для взрослого кота").callbackData("/adult_cat"),
                        new InlineKeyboardButton("Рекомендации для кота с ограниченными возможностями").callbackData("/cat_with_disabilities")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Причины отказа").callbackData("/reasons_refusal"),
                        new InlineKeyboardButton("Контактные данные").callbackData("/contact_details")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Позвать волонтера ").callbackData("/volunteer")
                });
        this.telegramBot.execute(new SendMessage(update.message().chat().id(),"Выберите пункт меню:").replyMarkup(inlineKeyboardMarkup));
    }

    //Кнопки как взять собаку из приюта.
    //Необходимо сделать вертикальное меню. В горизонт больше 8 кнопок нельзя.
    private void userTakeDogButtons(Update update){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Правила знакомства с животным").callbackData("/dating_rules"),
                        new InlineKeyboardButton("Список документов").callbackData("/list_documents")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Рекомендации по транспортировке собаки").callbackData("/transporting_dog"),
                        new InlineKeyboardButton("Рекомендации для щенка").callbackData("/recommendations_puppy")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Рекомендации для взрослой собаки").callbackData("/adult_dog"),
                        new InlineKeyboardButton("Рекомендации для собаки с ограниченными возможностями").callbackData("/dog_with_disabilities")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Советы кинолога").callbackData("/tips_dog_handler"),
                        new InlineKeyboardButton("Рекомендации по проверенным кинологам").callbackData("/recommendations_dog_handler")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Причины отказа").callbackData("/reasons_refusal"),
                        new InlineKeyboardButton("Контактные данные").callbackData("/contact_details")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Позвать волонтера ").callbackData("/volunteer")
                });
        this.telegramBot.execute(new SendMessage(update.message().chat().id(),"Выберите пункт меню:").replyMarkup(inlineKeyboardMarkup));
    }

    //Приветственное сообщение. Обработка /start.
    //Вызов метода кнопок выбора приюта.
    public void handleHello(Update update){
        if (update.message()!=null && update.message().text().startsWith("/start")){
            this.telegramBot.execute(
                    new SendMessage(update.message().chat().id(),
                            "Приветствую пользователь, это телеграмм-бот для приюта домашних животных."));
            buttonsCatAndDog(update);
        }
    }

    //Ответное сообщение на кнопку "Приют для кошек".
    //Вызов метода кнопок стартового меню приюта.
    public void handleCatShelter(Update update){
        this.telegramBot.execute(
                new SendMessage(update.message().chat().id(),
                        "Вы выбрали приют для кошек."));
        userRequestProcessingButtons(update);
    }
    //Ответное сообщение на кнопку "Приют для собак".
    //Вызов метода кнопок стартового меню приюта.
    public void handleDogShelter(Update update){
        this.telegramBot.execute(
                new SendMessage(update.message().chat().id(),
                        "Вы выбрали приют для собак."));
        userRequestProcessingButtons(update);
    }
    //Ответное сообщение на кнопку "Инфо о приюте".
    //Вызов метода кнопок информации о приюте.
    public void handleInfoShelter(Update update){
        this.telegramBot.execute(
                new SendMessage(update.message().chat().id(),
                        "Доделать вызов общей инфы о приюте"));
        userInfoButtons(update);
    }
    //Ответное сообщение на кнопку "/take_cat".
    //Вызов метода кнопок как взять кота из приюта.
    public void handleTakeCatShelter(Update update){
        this.telegramBot.execute(
                new SendMessage(update.message().chat().id(),
                        "Инструкция по усыновлению кота."));
        userTakeCatButtons(update);
    }
    //Ответное сообщение на кнопку "/take_dog".
    //Вызов метода кнопок как взять собаку из приюта.
    public void handleTakeDogShelter(Update update){
        this.telegramBot.execute(
                new SendMessage(update.message().chat().id(),
                        "Инструкция по усыновлению собаки."));
        userTakeDogButtons(update);
    }
    //Ответное сообщение на кнопку "/report".
    public void handleReportShelter(Update update){
        this.telegramBot.execute(
                new SendMessage(update.message().chat().id(),
                        "Вы выбрали отчёт."));
        //Здесь будет метод с кнопками по отчёту.
    }
    //Ответное сообщение на кнопку "/volunteer".
    public void handleVolunteerShelter(Update update){
        this.telegramBot.execute(
                new SendMessage(update.message().chat().id(),
                        "Волонтёр."));
        //Здесь будет метод с волонтёром.
    }
}