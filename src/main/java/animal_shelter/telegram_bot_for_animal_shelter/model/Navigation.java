package animal_shelter.telegram_bot_for_animal_shelter.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Navigation {

    private Long id;
    private Long levelView;
    private Long levelReference;
    private String endpoint;
    private String rules;
    private String text;
}
