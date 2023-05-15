package animal_shelter.telegram_bot_for_animal_shelter.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// Класс для описания приюта
@Entity
@Table(name = "shelter")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shelter_id")
    private Long shelterId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "driving_directions")
    private String drivingDirections; // схема проезда

    @Column(name = "description")
    private String description;

    @Column(name = "schedule")
    private String schedule; // расписание работы приюта

    @Column(name = "rules")
    private String rules;

    @Column(name = "contacts", nullable = false)
    private String contacts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "shelter_volunteers",
            joinColumns = @JoinColumn(name = "shelter_id"),
            inverseJoinColumns = @JoinColumn(name = "volunteer_id")
    )
    private Set<Volunteer> volunteerIds = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shelter shelter = (Shelter) o;
        return Objects.equals(shelterId, shelter.shelterId) && Objects.equals(name, shelter.name) && Objects.equals(address, shelter.address) && Objects.equals(drivingDirections, shelter.drivingDirections) && Objects.equals(description, shelter.description) && Objects.equals(schedule, shelter.schedule) && Objects.equals(rules, shelter.rules) && Objects.equals(contacts, shelter.contacts) && Objects.equals(volunteerIds, shelter.volunteerIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shelterId, name, address, drivingDirections, description, schedule, rules, contacts, volunteerIds);
    }
}
