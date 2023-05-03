package animal_shelter.telegram_bot_for_animal_shelter.listener;

import animal_shelter.telegram_bot_for_animal_shelter.repository.ShelterRepository;
import animal_shelter.telegram_bot_for_animal_shelter.service.keyboard.KeyboardDog;
import animal_shelter.telegram_bot_for_animal_shelter.service.keyboard.KeyboardCat;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
//Обработка сообщений
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final TelegramBot telegramBot;
    private final KeyboardCat keyboardCat;
    private final KeyboardDog keyboardDog;
    private final ShelterRepository shelterRepository;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, KeyboardCat keyboardCat, KeyboardDog keyboardDog, ShelterRepository shelterRepository) {
        this.telegramBot = telegramBot;
        this.keyboardCat = keyboardCat;
        this.keyboardDog = keyboardDog;
        this.shelterRepository = shelterRepository;
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> list) {
        try {
            list.stream().filter(l -> l.message() != null || l.callbackQuery() != null).forEach(this::handleUpdate);
        }catch (Exception e){
            e.printStackTrace();
        }
        return CONFIRMED_UPDATES_ALL;
    }

    //Стартовое приветствие (Общий метод на два приюта)
    public void startMenu(Update update) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(
                new InlineKeyboardButton("Усыновить питомца").callbackData("/selectShelter"));
        this.telegramBot.execute(new SendMessage(update.message().chat().id(), "Привет я бот-помошник!!!\n\nЕсли у тебя есть какие-то вопросы или желание усыновить питомца\n\nЖми кнопку!!!").replyMarkup(inlineKeyboardMarkup));
    }

    //Выбор приюта (Общий метод на два приюта)
    public void selectShelterMenu(Update update) {
        InlineKeyboardButton cat = new InlineKeyboardButton("Приют для кошек").callbackData("/cat");
        InlineKeyboardButton dog = new InlineKeyboardButton("Приют для собак").callbackData("/dog");
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup(cat, dog);
        this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                "Приветствую пользователь, это телеграмм-бот для приюта домашних животных.\n\n Пожалуйста выбери приют:").replyMarkup(keyboard));
    }

    //Обработчик
    private void handleUpdate(Update update) {
        if (update.message() != null && update.message().text().startsWith("/start")) {
            startMenu(update);
            return;
        }
        switch (update.callbackQuery().data()) {
            case "/selectShelter" -> selectShelterMenu(update);
            case "/cat" -> keyboardCat.menuButtonsCatShelter(update);
            case "/infoCat" -> keyboardCat.menuButtonsInfoCatShelter(update);
            case "/takeCat" -> keyboardCat.menuButtonsTakeCatShelter(update);
            case "/reportCat" -> keyboardCat.menuButtonsReportCatShelter(update);
            case "/detailedInfoCat" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (shelterRepository.getSheltersByShelterId(1L).getDescription())));
            case "/visitingCat" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (shelterRepository.getSheltersByShelterId(1L).getAddress()+"\n"+shelterRepository.getSheltersByShelterId(1L).getSchedule()+"\n"+shelterRepository.getSheltersByShelterId(1L).getDrivingDirections())));
            case "/registrationPassCat" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    (shelterRepository.getSheltersByShelterId(1L).getContacts())));
            case "/safetyPrecautionsCat" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    "При посещении данного заведения,запрещено кормить животных,общаться с ними без контроля волонтёра,курить,употреблять алкоголь и пищу."));
            case "/datingRulesCat" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    "1.Изучите породы котов.\n2. Подберите животное, подходящее вашему уровню активности.\n3. Учитывайте свои условия проживания.\n4. Определитесь со своими требованиями.\n5. Подумайте, стоит ли брать кота с особыми потребностями."));
            case "/listDocumentsCat" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    "Для усыновления котёнка/кота вам нужно иметь при себе:\n1. Паспорт"));
            case "/transportingCat" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    "1. Взять клетку-переноску.\n2. Взять одеяло.\n3. Приехать на автомобиле или вызвать такси/спецтранспорт для транспортировки."));
            case "/recommendationsKitten" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    "1. Купите всё, что вам будет необходимо для котёнка (Миску,Корм,Адресник,Клетку-переноску)\n2. Найдите ветеринара.\n3. Проверьте, безопасен ли ваш дом для животного."));
            case "/adultCat" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    "1. Купите всё, что вам будет необходимо для кота (Миску,Корм,Клетку-переноску)\n2. Найдите ветеринара.\n3. Проверьте, безопасен ли ваш дом для животного."));
            case "/withDisabilitiesCat" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    "1. Обустроить свой дом для комфортного проживания кота с ограниченными возможностями.\n2. Купить специальный корм.\n3. Сделать для него личное место."));
            case "/reasonsRefusalCat" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    "1. Нет возможности содержать животное.\n2. Нет документов подтверждающих личность.\n3. Зафиксированные случаи плохого обращения с животными."));
            case "/volunteerCat" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    "Ожидайте первый освободившийся волонтёр свяжется с вами."));

            case "/dog" -> keyboardDog.menuButtonsDogShelter(update);
            case "/infoDog" -> keyboardDog.menuButtonsInfoDogShelter(update);
            case "/takeDog" -> keyboardDog.menuButtonsTakeDogShelter(update);
            case "/reportDog" -> keyboardDog.menuButtonsReportDogShelter(update);
            case "/detailedInfoDog" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    "Крупнейшая в Петербурге сеть помощи бездомным животным «Потеряшка» построила собственный приют «Теремок». Здесь принимают в первую очередь больных и травмированных животных, при этом вид бывших домашних питомцев не имеет значения, будь то собака, кролик или попугай. На территории приюта живёт порядка 300 собак."));
            case "/visitingDog" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    "Часы работы: 9:00-20:00\nАдрес: просп. Большой Смоленский, д. 9.\nТелефон: 651-27-01"));
            case "/registrationPassDog" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    "Для  того чтобы выписать пропуск на автомобиль,свяжитесь с пунктом охраны по данному телефону:  '344-17-43'"));
            case "/safetyPrecautionsDog" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    "При посещении  данного заведения,запрещено кормить животных,общаться с ними без контроля волонтёра,курить,употреблять алкоголь и пищу."));
            case "/datingRulesDog" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    "1.Изучите породы собак.\n2. Подберите животное, подходящее вашему уровню активности.\n3. Учитывайте свои условия проживания.\n4. Определитесь со своими требованиями.\n5. Подумайте, стоит ли брать собаку с особыми потребностями."));
            case "/listDocumentsDog" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    "Для усыновления щенка/собаки вам нужно иметь при себе:\n1. Паспорт"));
            case "/transportingDog" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    "1. Взять ошейник, намордник.\n2. Взять одеяло.\n3. Приехать на автомобиле или вызвать такси/спецтранспорт для транспортировки."));
            case "/recommendationsPuppy" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    "1. Купите всё, что вам будет необходимо для щенка (Миску,Корм,Адресник,ошейник)\n2. Найдите ветеринара.\n3. Проверьте, безопасен ли ваш дом для животного."));
            case "/adultDog" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    "1. Купите всё, что вам будет необходимо для собаки (Миску,Корм)\n2. Найдите ветеринара.\n3. Проверьте, безопасен ли ваш дом для животного."));
            case "/withDisabilitiesDog" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    "1. Обустроить свой дом для комфортного проживания собаки с ограниченными возможностями.\n2. Купить специальный корм.\n3. Сделать для него личное место."));
            case "/reasonsRefusalDog" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    "1. Нет  возможности содержать животное.\n2. Нет документов подтверждающих личность.\n3. Зафиксированные случаи плохого обращения с животными."));
            case "/tipsHandlerDog" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    "Многие владельцы кормят своего любимца перед прогулкой. Однако так делать неправильно и даже вредит вашему питомцу. Собака — хищник. С точки зрения физиологии, хищник должен сначала получить нагрузку, поймать свою добычу и только потом её съесть. После еды хищники отдыхают, и собака так же должна отдыхать после прогулки и кормления."));
            case "/recommendationsHandlerDog" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    "Сергей Петров\nСтаж 3год.\nНомер телефона: 678-54-89.\n\nГеоргий Иванов\nСтаж 7лет.\nНомер телефона: 679-76-99."));
            case "/volunteerDog" -> this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(),
                    "Ожидайте первый освободившийся волонтёр свяжется с вами."));
        }
    }
}