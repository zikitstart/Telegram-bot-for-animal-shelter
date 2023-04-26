package animal_shelter.telegram_bot_for_animal_shelter.service.keyboard;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
public class KeyboardDog {

    private final TelegramBot telegramBot;

    public KeyboardDog(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void handleDogShelter(Update update) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Узнать информацию о приюте").callbackData("/infoDog"),
                        new InlineKeyboardButton("Как взять животное из приюта").callbackData("/takeDog")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Прислать отчет о питомце").callbackData("/reportDog"),
                        new InlineKeyboardButton("Позвать волонтера ").callbackData("/volunteerDog")
                });
        this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),"Вы выбрали приют для собак 'Ржевка'.\n\nВыберите пункт меню:").replyMarkup(inlineKeyboardMarkup));

    }
    //Кнопки информации о приюте
    //Необходимо сделать вертикальное меню. В горизонт больше 8 кнопок нельзя.
    public void userInfoDogButtons(Update update){

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Инфо для посещения").callbackData("/visitingDog"),
                        new InlineKeyboardButton("Оформление пропуска").callbackData("/registrationPassDog")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Техника безопасности").callbackData("/safetyPrecautionsDog"),
                        new InlineKeyboardButton("Позвать волонтера ").callbackData("/volunteerDog")
                });
        this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),"Часы работы: 9:00-20:00\nАдрес: просп. Большой Смоленский, д. 9.\nТелефон: 374-15-15\n\nДля детальной информации выберите пункт меню:").replyMarkup(inlineKeyboardMarkup));
    }

    //Кнопки как взять собаку из приюта.
    //Необходимо сделать вертикальное меню. В горизонт больше 8 кнопок нельзя.
    public void userTakeDogButtons(Update update){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Правила знакомства с животным").callbackData("/datingRulesDog"),
                        new InlineKeyboardButton("Список документов").callbackData("/listDocumentsDog")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Рекомендации по транспортировке собаки").callbackData("/transportingDog"),
                        new InlineKeyboardButton("Рекомендации для щенка").callbackData("/recommendationsPuppy")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Рекомендации для взрослой собаки").callbackData("/adultDog"),
                        new InlineKeyboardButton("Рекомендации для собаки с ограниченными возможностями").callbackData("/withDisabilitiesDog")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Советы кинолога").callbackData("/tipsHandlerDog"),
                        new InlineKeyboardButton("Рекомендации по проверенным кинологам").callbackData("/recommendationsHandlerDog")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Причины отказа").callbackData("/reasonsRefusalDog"),
                        new InlineKeyboardButton("Контактные данные").callbackData("/contactDetailsDog")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Позвать волонтера ").callbackData("/volunteerDog")
                });
        this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),"Инструкция по усыновлению собаки.\n\nВыберите пункт меню:").replyMarkup(inlineKeyboardMarkup));
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
                        "Ожидайте первый освободившийся волонтёр свяжется с вами."));
        //Здесь будет метод с волонтёром.
    }
}