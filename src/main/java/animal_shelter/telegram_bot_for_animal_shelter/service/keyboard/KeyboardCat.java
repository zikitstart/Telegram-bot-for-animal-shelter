package animal_shelter.telegram_bot_for_animal_shelter.service.keyboard;

import animal_shelter.telegram_bot_for_animal_shelter.repository.ShelterRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
// Класс с Клавиатурами/Меню для приюта кошек
public class KeyboardCat {

    private final ShelterRepository shelterRepository;

    private final TelegramBot telegramBot;

    public KeyboardCat(ShelterRepository shelterRepository, TelegramBot telegramBot) {
        this.shelterRepository = shelterRepository;
        this.telegramBot = telegramBot;
    }

    // Кнопки меню после "Выбор приюта"
    public void menuButtonsCatShelter(Update update) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Узнать информацию о приюте").callbackData("/infoCat"),
                        new InlineKeyboardButton("Как взять животное из приюта").callbackData("/takeCat")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Прислать отчет о питомце").callbackData("/reportCat"),
                        new InlineKeyboardButton("Позвать волонтера ").callbackData("/volunteerCat")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Возврат к выбору приюта").callbackData("/selectShelter")
                });
        telegramBot.execute(new DeleteMessage(update.callbackQuery().from().id(),update.callbackQuery().message().messageId()));
        this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Вас приветствует приют для кошек: "+ (shelterRepository.getSheltersByShelterId(1L).getName()) +"\n\nВыберите пункт меню:").replyMarkup(inlineKeyboardMarkup));
    }

    // Кнопки меню "Информация о приюте"
    public void menuButtonsInfoCatShelter(Update update) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Детальная информация о приюте").callbackData("/detailedInfoCat"),
                        new InlineKeyboardButton("Информация для посещения").callbackData("/visitingCat")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Оформление пропуска").callbackData("/registrationPassCat"),
                        new InlineKeyboardButton("Техника безопасности").callbackData("/safetyPrecautionsCat")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Позвать волонтера").callbackData("/volunteerCat")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Возврат к предыдущему меню").callbackData("/cat")
                });
        telegramBot.execute(new DeleteMessage(update.callbackQuery().from().id(),update.callbackQuery().message().messageId()));
        this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Для детальной информации выберите пункт меню:").replyMarkup(inlineKeyboardMarkup));
    }

    // Кнопки меню "Как взять кота из приюта"
    public void menuButtonsTakeCatShelter(Update update) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Правила знакомства с животным").callbackData("/datingRulesCat"),
                        new InlineKeyboardButton("Список документов").callbackData("/listDocumentsCat")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Рекомендации по транспортировке кота").callbackData("/transportingCat"),
                        new InlineKeyboardButton("Рекомендации для котенка").callbackData("/recommendationsKitten")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Рекомендации для взрослого кота").callbackData("/adultCat"),
                        new InlineKeyboardButton("Рекомендации для кота с ограниченными возможностями").callbackData("/withDisabilitiesCat")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Причины отказа").callbackData("/reasonsRefusalCat"),
                        new InlineKeyboardButton("Контактные данные").callbackData("/contactDetailsCat")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Позвать волонтера ").callbackData("/volunteerCat")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Возврат к предыдущему меню").callbackData("/cat")
                });
        telegramBot.execute(new DeleteMessage(update.callbackQuery().from().id(),update.callbackQuery().message().messageId()));
        this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Инструкция по усыновлению кота.\n\nВыберите пункт меню:").replyMarkup(inlineKeyboardMarkup));
    }
}
