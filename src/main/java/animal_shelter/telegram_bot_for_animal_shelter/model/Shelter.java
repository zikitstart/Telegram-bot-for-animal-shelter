package animal_shelter.telegram_bot_for_animal_shelter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

// Класс для описания приюта
@Entity
@Table(name = "shelter")
@Data
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
}
