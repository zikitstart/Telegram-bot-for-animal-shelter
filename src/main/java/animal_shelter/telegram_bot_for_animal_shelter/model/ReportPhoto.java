package animal_shelter.telegram_bot_for_animal_shelter.model;

import lombok.Data;

@Data
public class ReportPhoto {

    private Long id;

    private String mediaType;
    private byte[] photo;
    private Report report;
}
