package animal_shelter.telegram_bot_for_animal_shelter.service.keyboard;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
//Клавиатуры/Меню для приюта собак
public class KeyboardDog {

    private final TelegramBot telegramBot;

    public KeyboardDog(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    //Кнопки меню после "Выбор приюта"
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
        this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Вас приветствует приют для собак 'Теремок'.\n\nВыберите пункт меню:").replyMarkup(inlineKeyboardMarkup));

    }

    //Кнопки меню "Узнать информацию о приюте".
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
        this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Часы работы: 9:00-20:00\nАдрес: просп. Большой Смоленский, д. 9.\nТелефон: 374-15-15\n\nДля детальной информации выберите пункт меню:").replyMarkup(inlineKeyboardMarkup));
    }

    //Кнопки меню "Как взять животное из приюта".
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
        this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Инструкция по усыновлению собаки.\n\nВыберите пункт меню:").replyMarkup(inlineKeyboardMarkup));
    }

    //Кнопки меню "Отчёт о питомце"
    public void menuButtonsReportDogShelter(Update update) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Фото животного").callbackData("/photoDog"),
                        new InlineKeyboardButton("Рацион животного").callbackData("/dietDog")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Самочувствие и привыкание к новому месту").callbackData("/addictiveDog"),
                        new InlineKeyboardButton("Изменение в поведении").callbackData("/changesDog")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Возврат в предыдущее меню").callbackData("/dog")
                });
        this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Заполните отчёт о питомце:").replyMarkup(inlineKeyboardMarkup));
    }

    //Советы кинолога
    public void handleTipsHandlerDogShelter(Update update) {
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "Многие владельцы кормят своего любимца перед прогулкой. Однако так делать неправильно и даже вредит вашему питомцу. Собака — хищник. С точки зрения физиологии, хищник должен сначала получить нагрузку, поймать свою добычу и только потом её съесть. После еды хищники отдыхают, и собака так же должна отдыхать после прогулки и кормления."));
    }

    //Рекомендации по проверенным кинологам
    public void handleRecommendationsHandlerDogShelter(Update update) {
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "Сергей Петров\nСтаж 3год.\nНомер телефона: 678-54-89.\n\nГеоргий Иванов\nСтаж 7лет.\nНомер телефона: 679-76-99."));
    }

    //Причины отказа для взятия животного из приюта
    public void handleReasonsRefusalDogShelter(Update update) {
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "1. Нет возможности содержать животное.\n2. Нет документов подтверждающих личность.\n3. Зафиксированные случаи плохого обращения с животными."));
    }

    //Рекомендации для усыновления собаки с ограниченными возможностями
    public void handleWithDisabilitiesDogShelter(Update update) {
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "1. Обустроить свой дом для комфортного проживания собаки с ограниченными возможностями.\n2. Купить специальный корм.\n3. Сделать для него личное место."));
    }

    //Рекомендации для усыновления собаки
    public void handleAdultDogShelter(Update update) {
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "1. Купите всё, что вам будет необходимо для собаки (Миску,Корм)\n2. Найдите ветеринара.\n3. Проверьте, безопасен ли ваш дом для животного."));
    }

    //Рекомендации для усыновления щенка
    public void handleRecommendationsPuppyShelter(Update update) {
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "1. Купите всё, что вам будет необходимо для щенка (Миску,Корм,Адресник,ошейник)\n2. Найдите ветеринара.\n3. Проверьте, безопасен ли ваш дом для животного."));
    }

    //Рекомендации по транспортировке собаки
    public void handleTransportingDogShelter(Update update) {
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "1. Взять ошейник, намордник.\n2. Взять одеяло.\n3. Приехать на автомобиле или вызвать такси/спецтранспорт для транспортировки."));
    }

    //Документы которые нужно иметь при себе,необходимые для усыновления собаки
    public void handleListDocumentsDogShelter(Update update) {
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "Для усыновления щенка/собаки вам нужно иметь при себе:\n1. Паспорт"));
    }

    //Правила знакомства с животным(собака)
    public void handleDatingRulesDogShelter(Update update) {
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "1.Изучите породы собак.\n2. Подберите животное, подходящее вашему уровню активности.\n3. Учитывайте свои условия проживания.\n4. Определитесь со своими требованиями.\n5. Подумайте, стоит ли брать собаку с особыми потребностями."));
    }

    //Техника безопасности на територии приюта
    public void handleSafetyPrecautionsDogShelter(Update update) {
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "При посещении данного заведения,запрещено кормить животных,общаться с ними без контроля волонтёра,курить,употреблять алкоголь и пищу."));
    }

    //Контактные данные пункта охраны для оформления пропуска на автомобиль
    public void handleRegistrationPassDogShelter(Update update) {
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "Для того чтобы выписать пропуск на автомобиль,свяжитесь с пунктом охраны по данному телефону:  '344-17-43'"));
    }

    //Информация для посещения(Время работы,Адрес,Телефон)
    public void handleVisitingDogShelter(Update update) {
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "Часы работы: 9:00-20:00\nАдрес: просп. Большой Смоленский, д. 9.\nТелефон: 651-27-01"));
    }

    //Детальная информация о приюте
    public void handleDetailedInfoDogShelter(Update update) {
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "Крупнейшая в Петербурге сеть помощи бездомным животным «Потеряшка» построила собственный приют «Теремок». Здесь принимают в первую очередь больных и травмированных животных, при этом вид бывших домашних питомцев не имеет значения, будь то собака, кролик или попугай. На территории приюта живёт порядка 300 собак."));
    }

    //Сообщение при нажатии на кнопку "Позвать волонтёра" (команда "/volunteer")
    public void handleVolunteerDogShelter(Update update) {
        this.telegramBot.execute(
                new SendMessage(update.callbackQuery().from().id(),
                        "Ожидайте первый освободившийся волонтёр свяжется с вами."));
        //Здесь будет метод с волонтёром.
    }
}