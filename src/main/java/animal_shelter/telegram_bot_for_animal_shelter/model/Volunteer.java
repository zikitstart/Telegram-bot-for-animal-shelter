package animal_shelter.telegram_bot_for_animal_shelter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// Класс для описания волонтера
@Entity
@Table(name = "volunteer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Volunteer {
    @Id
    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "surname")
    private String surname;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;
}
