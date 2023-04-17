package animal_shelter.telegram_bot_for_animal_shelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class TelegramBotUpdatesListener implements UpdatesListener {

    //Логирование
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final TelegramBot telegramBot;

    public TelegramBotUpdatesListener(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @PostConstruct
    public void init(){
        telegramBot.setUpdatesListener(this);
    }

    //Метод для приветственного сообщения.
    @Override
    public int process(List<Update> list) {
        try {
           list.forEach(l->{
               logger.info("Handles update: {}",l);
               Message message = l.message();
               Long chatId = message.chat().id();
               String text = message.text();

               if ("/start".equals(text)){
                   SendMessage sendMessage = new SendMessage(chatId,"Приветствую пользователь, это телеграмм-бот для приюта домашних животных. Пожалуйста выбери приют.");
                   SendResponse sendResponse = telegramBot.execute(sendMessage);
                   if (!sendResponse.isOk()){
                       logger.error("Error during sending message: {}",sendResponse.description());
                   }
               }
           });

        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
