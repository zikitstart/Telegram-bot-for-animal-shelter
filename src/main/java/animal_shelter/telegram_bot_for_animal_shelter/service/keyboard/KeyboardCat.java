package animal_shelter.telegram_bot_for_animal_shelter.service.keyboard;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
public class KeyboardCat {

    private final TelegramBot telegramBot;

    public KeyboardCat(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    //Кнопки стартового меню приюта.
    //Необходимо сделать вертикальное меню. В горизонт больше 8 кнопок нельзя.
    //Возможно разделение метода на кошек и собак.
    public void handleCatShelter(Update update) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Узнать информацию о приюте").callbackData("/infoCat"),
                        new InlineKeyboardButton("Как взять животное из приюта").callbackData("/takeCat")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Прислать отчет о питомце").callbackData("/reportCat"),
                        new InlineKeyboardButton("Позвать волонтера ").callbackData("/volunteerCat")
                });
        this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),"Вы выбрали приют для кошек 'Островок надежды'\n\nВыберите пункт меню:").replyMarkup(inlineKeyboardMarkup));
    }

    //Кнопки информации о приюте
    //Необходимо сделать вертикальное меню. В горизонт больше 8 кнопок нельзя.
    public void userInfoCatButtons(Update update){

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Инфо для посещения").callbackData("/visitingCat"),
                        new InlineKeyboardButton("Оформление пропуска").callbackData("/registrationPassCat")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Техника безопасности").callbackData("/safetyPrecautionsCat"),
                        new InlineKeyboardButton("Позвать волонтера ").callbackData("/volunteerCat")
                });
        this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),"Для детальной информации выберите пункт меню:").replyMarkup(inlineKeyboardMarkup));
    }

    //Кнопки как взять кота из приюта.
    //Необходимо сделать вертикальное меню. В горизонт больше 8 кнопок нельзя.
    public void userTakeCatButtons(Update update){

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
                });
        this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),"Инструкция по усыновлению кота.\n\nВыберите пункт меню:").replyMarkup(inlineKeyboardMarkup));
    }
    public void handleReasonsRefusalCatShelter(Update update){
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "1. Нет возможности содержать животное.\n2. Нет документов подтверждающих личность.\n3. Зафиксированные случаи плохого обращения с животными."));
    }
    public void handleWithDisabilitiesCatShelter(Update update){
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "1. Обустроить свой дом для комфортного проживания кота с ограниченными возможностями.\n2. Купить специальный корм.\n3. Сделать для него личное место."));
    }
    public void handleAdultCatShelter(Update update){
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "1. Купите всё, что вам будет необходимо для кота (Миску,Корм,Клетку-переноску)\n2. Найдите ветеринара.\n3. Проверьте, безопасен ли ваш дом для животного."));
    }
    public void handleRecommendationsKittenShelter(Update update){
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "1. Купите всё, что вам будет необходимо для котёнка (Миску,Корм,Адресник,Клетку-переноску)\n2. Найдите ветеринара.\n3. Проверьте, безопасен ли ваш дом для животного."));
    }
    public void handleTransportingCatShelter(Update update){
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "1. Взять клетку-переноску.\n2. Взять одеяло.\n3. Приехать на автомобиле или вызвать такси/спецтранспорт для транспортировки."));
    }
    public void handleListDocumentsCatShelter(Update update){
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "Для усыновления котёнка/кота вам нужно иметь при себе:\n1. Паспорт"));
    }
    public void handleDatingRulesCatShelter(Update update){
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "1.Изучите породы котов.\n2. Подберите животное, подходящее вашему уровню активности.\n3. Учитывайте свои условия проживания.\n4. Определитесь со своими требованиями.\n5. Подумайте, стоит ли брать кота с особыми потребностями."));
    }
    public void handleSafetyPrecautionsCatShelter(Update update){
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "При посещении данного заведения,запрещено кормить животных,общаться с ними без контроля волонтёра,курить,употреблять алкоголь и пищу."));
    }

    public void handleRegistrationPassCatShelter(Update update){
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "Для того чтобы выписать пропуск на автомобиль,свяжитесь с пунктом охраны по данному телефону:  '344-17-43'"));
    }
    public void handleVisitingCatShelter(Update update){
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "Часы работы: 9:00-20:00\nАдрес: просп. Большой Смоленский, д. 9.\nТелефон: 374-15-15"));
    }

    //Ответное сообщение на кнопку "/report".
    public void handleReportCatShelter(Update update){
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "Вы выбрали отчёт."));
        //Здесь будет метод с кнопками по отчёту.
    }
    //Ответное сообщение на кнопку "/volunteer".
    public void handleVolunteerCatShelter(Update update){
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "Ожидайте первый освободившийся волонтёр свяжется с вами."));
        //Здесь будет метод с волонтёром.
    }
}
