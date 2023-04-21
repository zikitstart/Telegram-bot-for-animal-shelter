package animal_shelter.telegram_bot_for_animal_shelter.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Pet {

    private Long id;
    private Integer ageInMonths;
    private String name;
    private Customer customer;

    // Описание файла с фото питомца
    private String filePath;
    private Long fileSize;
    private String mediaType;
    private byte[] photo; // фото

    private LocalDateTime decisionDate; // дата принятия решения по усыновлению
    private Shelter shelter; // ссылка на приют питомца
}
