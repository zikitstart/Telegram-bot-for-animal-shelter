package animal_shelter.telegram_bot_for_animal_shelter.service.keyboard;

import animal_shelter.telegram_bot_for_animal_shelter.service.ClientService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
//Клавиатуры/Меню для приюта кошек
public class KeyboardCat {

    private final TelegramBot telegramBot;

    public KeyboardCat(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    //Кнопки меню после "Выбор приюта"
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
        this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Вас приветствует приют для кошек 'Островок надежды'\n\nВыберите пункт меню:").replyMarkup(inlineKeyboardMarkup));
    }

    //Кнопки меню "Информация о приюте"
    public void menuButtonsInfoCatShelter(Update update) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Детальная информация о приюте").callbackData("/detailedInfoCat"),
                        new InlineKeyboardButton("Информация для посещения").callbackData("/visitingCat")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Оформление пропуска").callbackData("/registrationPassCat"),
                        new InlineKeyboardButton("Техника безопасности").callbackData("/safetyPrecautionsCat"),
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Позвать волонтера").callbackData("/volunteerCat")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Возврат к предыдущему меню").callbackData("/cat")
                });
        this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Для детальной информации выберите пункт меню:").replyMarkup(inlineKeyboardMarkup));
    }

    //Кнопки меню "Как взять кота из приюта"
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
        this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Инструкция по усыновлению кота.\n\nВыберите пункт меню:").replyMarkup(inlineKeyboardMarkup));
    }

    //Кнопки меню "Отчёт о питомце"
    public void menuButtonsReportCatShelter(Update update) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Фото животного").callbackData("/photoCat"),
                        new InlineKeyboardButton("Рацион животного").callbackData("/dietCat")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Самочувствие и привыкание к новому месту").callbackData("/addictiveCat"),
                        new InlineKeyboardButton("Изменение в поведении").callbackData("/changesCat")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Возврат в предыдущее меню").callbackData("/cat")
                });
        this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Заполните отчёт о питомце:").replyMarkup(inlineKeyboardMarkup));
    }

    //Причины отказа для взятия животного из приюта
    public void handleReasonsRefusalCatShelter(Update update) {
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "1. Нет возможности содержать животное.\n2. Нет документов подтверждающих личность.\n3. Зафиксированные случаи плохого обращения с животными."));
    }

    //Рекомендации для усыновления кота с ограниченными возможностями
    public void handleWithDisabilitiesCatShelter(Update update) {
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "1. Обустроить свой дом для комфортного проживания кота с ограниченными возможностями.\n2. Купить специальный корм.\n3. Сделать для него личное место."));
    }

    //Рекомендации для усыновления кота
    public void handleAdultCatShelter(Update update) {
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "1. Купите всё, что вам будет необходимо для кота (Миску,Корм,Клетку-переноску)\n2. Найдите ветеринара.\n3. Проверьте, безопасен ли ваш дом для животного."));
    }

    //Рекомендации для усыновления котёнка
    public void handleRecommendationsKittenShelter(Update update) {
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "1. Купите всё, что вам будет необходимо для котёнка (Миску,Корм,Адресник,Клетку-переноску)\n2. Найдите ветеринара.\n3. Проверьте, безопасен ли ваш дом для животного."));
    }

    //Рекомендации по транспортировке кота
    public void handleTransportingCatShelter(Update update) {
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "1. Взять клетку-переноску.\n2. Взять одеяло.\n3. Приехать на автомобиле или вызвать такси/спецтранспорт для транспортировки."));
    }

    //Документы которые нужно иметь при себе,необходимые для усыновления кота
    public void handleListDocumentsCatShelter(Update update) {
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "Для усыновления котёнка/кота вам нужно иметь при себе:\n1. Паспорт"));
    }

    //Правила знакомства с животным(котом)
    public void handleDatingRulesCatShelter(Update update) {
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "1.Изучите породы котов.\n2. Подберите животное, подходящее вашему уровню активности.\n3. Учитывайте свои условия проживания.\n4. Определитесь со своими требованиями.\n5. Подумайте, стоит ли брать кота с особыми потребностями."));
    }

    //Техника безопасности на територии приюта
    public void handleSafetyPrecautionsCatShelter(Update update) {
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "При посещении данного заведения,запрещено кормить животных,общаться с ними без контроля волонтёра,курить,употреблять алкоголь и пищу."));
    }

    //Контактные данные пункта охраны для оформления пропуска на автомобиль
    public void handleRegistrationPassCatShelter(Update update) {
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "Для того чтобы выписать пропуск на автомобиль,свяжитесь с пунктом охраны по данному телефону:  '344-17-43'"));
    }

    //Информация для посещения(Время работы,Адрес,Телефон)
    public void handleVisitingCatShelter(Update update) {
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "Часы работы: 9:00-20:00\nАдрес: просп. Большой Смоленский, д. 9.\nТелефон: 374-15-15"));
    }

    //Детальная информация о приюте
    public void handleDetailedInfoCatShelter(Update update) {
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "Дома, где вам всегда рады, и где так сильно требуется ваша помощь. Если вы хотите взять или отдать животное, обращайтесь по номерам телефонов приютов."));
    }

    //Сообщение при нажатии на кнопку "Позвать волонтёра" (команда "/volunteer")
    public void handleVolunteerCatShelter(Update update) {
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "Ожидайте первый освободившийся волонтёр свяжется с вами."));
        //Здесь будет метод с волонтёром.
    }

    public void handleContactDetailsCat(Update update) {
        KeyboardButton keyboardButton = new KeyboardButton("Отправить номер телефона").requestContact(true);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(keyboardButton);
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(), "Добавил вам кнопку рядом с клавиатурой. Нажмите для того, чтобы поделиться номером телефона.").replyMarkup(replyKeyboardMarkup));
    }
}
