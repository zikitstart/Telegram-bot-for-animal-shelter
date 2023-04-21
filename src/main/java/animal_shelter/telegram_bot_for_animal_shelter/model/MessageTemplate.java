package animal_shelter.telegram_bot_for_animal_shelter.model;

import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import lombok.Data;

@Data
public class MessageTemplate {

    private InlineKeyboardMarkup keyboard;
    private String textStatus;
    private String textBody;
    private String textMenuCaption;
}
