package animal_shelter.telegram_bot_for_animal_shelter.service.keyboard;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
// Класс с Клавиатурами/Меню для приюта собак
public class KeyboardDog {

    private final TelegramBot telegramBot;

    public KeyboardDog(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    // Кнопки меню после "Выбор приюта"
    public void menuButtonsDogShelter(Update update) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Узнать информацию о приюте").callbackData("/infoDog"),
                        new InlineKeyboardButton("Как взять животное из приюта").callbackData("/takeDog")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Прислать отчет о питомце").callbackData("/reportDog"),
                        new InlineKeyboardButton("Позвать волонтера ").callbackData("/volunteerDog")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Возврат к выбору приюта").callbackData("/selectShelter")
                });
        telegramBot.execute(new DeleteMessage(update.callbackQuery().from().id(),update.callbackQuery().message().messageId()));
        this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Вас приветствует приют для собак 'Теремок'.\n\nВыберите пункт меню:").replyMarkup(inlineKeyboardMarkup));

    }

    // Кнопки меню "Узнать информацию о приюте".
    public void menuButtonsInfoDogShelter(Update update) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Детальная информация о приюте").callbackData("/detailedInfoDog"),
                        new InlineKeyboardButton("Информация для посещения").callbackData("/visitingDog")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Оформление пропуска").callbackData("/registrationPassDog"),
                        new InlineKeyboardButton("Техника безопасности").callbackData("/safetyPrecautionsDog"),
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Позвать волонтера").callbackData("/volunteerDog")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Возврат к предыдущему меню").callbackData("/dog")
                });
        telegramBot.execute(new DeleteMessage(update.callbackQuery().from().id(),update.callbackQuery().message().messageId()));
        this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Часы работы: 9:00-20:00\nАдрес: просп. Большой Смоленский, д. 9.\nТелефон: 374-15-15\n\nДля детальной информации выберите пункт меню:").replyMarkup(inlineKeyboardMarkup));
    }

    // Кнопки меню "Как взять животное из приюта".
    public void menuButtonsTakeDogShelter(Update update) {
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
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Возврат к предыдущему меню").callbackData("/dog")
                });
        telegramBot.execute(new DeleteMessage(update.callbackQuery().from().id(),update.callbackQuery().message().messageId()));
        this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Инструкция по усыновлению собаки.\n\nВыберите пункт меню:").replyMarkup(inlineKeyboardMarkup));
    }
}