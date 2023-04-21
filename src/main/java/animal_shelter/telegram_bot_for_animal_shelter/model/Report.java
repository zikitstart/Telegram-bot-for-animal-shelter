package animal_shelter.telegram_bot_for_animal_shelter.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Report {

    private Long id; // уникальный id
    private String petReport; // отчет текстовый: рацион, самочувствие, поведение питомца

    private LocalDateTime date; // дата сдачи отчета

    private Pet pet; // id питомца (из таблицы Pet) (one-to-one)
}
