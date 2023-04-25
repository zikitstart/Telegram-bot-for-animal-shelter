package animal_shelter.telegram_bot_for_animal_shelter.service.keyboard;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        InlineKeyboardButton informationShelter = new InlineKeyboardButton("Узнать информацию о приюте").callbackData("/info");
        InlineKeyboardButton takeAnimal = new InlineKeyboardButton("Как взять животное из приюта").callbackData("/take");
        InlineKeyboardButton petReport = new InlineKeyboardButton("Прислать отчет о питомце").callbackData("/report");
        InlineKeyboardButton callVolunteer = new InlineKeyboardButton("Позвать волонтера ").callbackData("/volunteer");
        keyboard.addRow(informationShelter,takeAnimal,petReport,callVolunteer);
        //Попытка реализации вертикального меню.
//        List<InlineKeyboardButton> inlineKeyboardButtons1 = new ArrayList<>();
//        List<InlineKeyboardButton> inlineKeyboardButtons2 = new ArrayList<>();
//        inlineKeyboardButtons1.add(informationShelter);
//        inlineKeyboardButtons1.add(takeAnimal);
//        inlineKeyboardButtons2.add(petReport);
//        inlineKeyboardButtons2.add(callVolunteer);
//        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
//        rowList.add(inlineKeyboardButtons1);
//        rowList.add(inlineKeyboardButtons2);
//        keyboard.inlineKeyboard();
        this.telegramBot.execute(new SendMessage(update.message().chat().id(),"Выберите пункт меню:").replyMarkup(keyboard));
    }

    //Кнопки информации о приюте
    //Необходимо сделать вертикальное меню. В горизонт больше 8 кнопок нельзя.
    private void userInfoButtons(Update update){
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        InlineKeyboardButton infoVisiting = new InlineKeyboardButton("Инфо для посещения").callbackData("/info_visiting");
        InlineKeyboardButton registrationPass = new InlineKeyboardButton("Оформление пропуска").callbackData("/registration_pass");
        InlineKeyboardButton safetyPrecautions = new InlineKeyboardButton("Техника безопасности").callbackData("/safety_precautions");
        InlineKeyboardButton callVolunteer = new InlineKeyboardButton("Позвать волонтера ").callbackData("/volunteer");
        keyboard.addRow(infoVisiting,registrationPass,safetyPrecautions,callVolunteer);
        this.telegramBot.execute(new SendMessage(update.message().chat().id(),"Для детальной информации выберите пункт меню:").replyMarkup(keyboard));
    }

    //Кнопки как взять кота из приюта.
    //Необходимо сделать вертикальное меню. В горизонт больше 8 кнопок нельзя.
    private void userTakeCatButtons(Update update){
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        InlineKeyboardButton datingRules = new InlineKeyboardButton("Правила знакомства с животным").callbackData("/dating_rules");
        InlineKeyboardButton listDocuments = new InlineKeyboardButton("Список документов").callbackData("/list_documents");
        InlineKeyboardButton transportingCat = new InlineKeyboardButton("Рекомендации по транспортировке кота").callbackData("/transporting_cat");
        InlineKeyboardButton recommendationsKitten = new InlineKeyboardButton("Рекомендации для котенка").callbackData("/recommendations_kitten");
        InlineKeyboardButton adultCat = new InlineKeyboardButton("Рекомендации для взрослого кота").callbackData("/adult_cat");
        InlineKeyboardButton catWithDisabilities = new InlineKeyboardButton("Рекомендации для кота с ограниченными возможностями").callbackData("/cat_with_disabilities");
        InlineKeyboardButton reasonsRefusal = new InlineKeyboardButton("Причины отказа").callbackData("/reasons_refusal");
        InlineKeyboardButton contactDetails = new InlineKeyboardButton("Контактные данные").callbackData("/contact_details");
        InlineKeyboardButton callVolunteer = new InlineKeyboardButton("Позвать волонтера ").callbackData("/volunteer");
        keyboard.addRow(datingRules,listDocuments,transportingCat,recommendationsKitten,adultCat,catWithDisabilities,reasonsRefusal,contactDetails,callVolunteer);
        this.telegramBot.execute(new SendMessage(update.message().chat().id(),"Выберите пункт меню:").replyMarkup(keyboard));
    }

    //Кнопки как взять собаку из приюта.
    //Необходимо сделать вертикальное меню. В горизонт больше 8 кнопок нельзя.
    private void userTakeDogButtons(Update update){
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        InlineKeyboardButton datingRules = new InlineKeyboardButton("Правила знакомства с животным").callbackData("/dating_rules");
        InlineKeyboardButton listDocuments = new InlineKeyboardButton("Список документов").callbackData("/list_documents");
        InlineKeyboardButton transportingDog = new InlineKeyboardButton("Рекомендации по транспортировке собаки").callbackData("/transporting_dog");
        InlineKeyboardButton recommendationsPuppy = new InlineKeyboardButton("Рекомендации для щенка").callbackData("/recommendations_puppy");
        InlineKeyboardButton adultDog = new InlineKeyboardButton("Рекомендации для взрослой собаки").callbackData("/adult_dog");
        InlineKeyboardButton dogWithDisabilities = new InlineKeyboardButton("Рекомендации для собаки с ограниченными возможностями").callbackData("/dog_with_disabilities");
        InlineKeyboardButton tipsDogHandler = new InlineKeyboardButton("Советы кинолога").callbackData("/tips_dog_handler");
        InlineKeyboardButton recommendationsDogHandler = new InlineKeyboardButton("Рекомендации по проверенным кинологам").callbackData("/recommendations_dog_handler");
        InlineKeyboardButton reasonsRefusal = new InlineKeyboardButton("Причины отказа").callbackData("/reasons_refusal");
        InlineKeyboardButton contactDetails = new InlineKeyboardButton("Контактные данные").callbackData("/contact_details");
        InlineKeyboardButton callVolunteer = new InlineKeyboardButton("Позвать волонтера ").callbackData("/volunteer");
        keyboard.addRow(datingRules,listDocuments,transportingDog,recommendationsPuppy,adultDog,dogWithDisabilities,tipsDogHandler,recommendationsDogHandler,reasonsRefusal,contactDetails,callVolunteer);
        this.telegramBot.execute(new SendMessage(update.message().chat().id(),"Выберите пункт меню:").replyMarkup(keyboard));
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