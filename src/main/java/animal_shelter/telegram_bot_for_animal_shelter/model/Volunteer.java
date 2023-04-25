package animal_shelter.telegram_bot_for_animal_shelter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @ManyToMany(mappedBy = "volunteerIds")
    private Set<Shelter> shelterIds = new HashSet<>();
}
