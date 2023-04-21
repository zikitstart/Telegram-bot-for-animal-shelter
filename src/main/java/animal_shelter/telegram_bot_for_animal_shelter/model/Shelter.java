package animal_shelter.telegram_bot_for_animal_shelter.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class Shelter {

    private long id; // уникальный id
    private String name; // название приюта питомцев
    private String address; // адрес
    private String locationMap; // ссылка на схему проезда
    private String description; // описание приюта
    private String rules; // правила приюта

    private Set<Volunteer> volunteers = new HashSet<>();
}
