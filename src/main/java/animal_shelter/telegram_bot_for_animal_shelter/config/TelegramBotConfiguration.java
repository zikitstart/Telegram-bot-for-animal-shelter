package animal_shelter.telegram_bot_for_animal_shelter.config;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Класс для конфигураций бота.
@Configuration
public class TelegramBotConfiguration {

    //Метод для передачи токена для бота.
    @Bean
    public TelegramBot telegramBot(@Value("${telegram.bot.token}") String token){
        return new TelegramBot(token);
    }
}
