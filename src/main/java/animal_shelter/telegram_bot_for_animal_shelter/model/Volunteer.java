package animal_shelter.telegram_bot_for_animal_shelter.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// Класс для описания волонтера
@Entity
@Table(name = "volunteer")
@Getter
@Setter
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volunteer volunteer = (Volunteer) o;
        return Objects.equals(chatId, volunteer.chatId) && Objects.equals(surname, volunteer.surname) && Objects.equals(firstName, volunteer.firstName) && Objects.equals(lastName, volunteer.lastName) && Objects.equals(phoneNumber, volunteer.phoneNumber) && Objects.equals(shelterIds, volunteer.shelterIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, surname, firstName, lastName, phoneNumber, shelterIds);
    }
}
