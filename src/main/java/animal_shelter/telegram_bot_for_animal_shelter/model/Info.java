package animal_shelter.telegram_bot_for_animal_shelter.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

// Класс для различной информации о взаимодействии с приютами и животными
@Component
@Data
public class Info {
    @PostConstruct
    public void init() {

    }

    private String recommendations; // Правила знакомства с животными, список документов, причины отказа, советы кинологов и список проверенных кинологов

    private String instructions; // разные рекомендации по транспортировке и обустройстве жилища
}
