package animal_shelter.telegram_bot_for_animal_shelter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

// Класс для описания домашнего питомца
@Entity
@Table(name = "pet")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long petId;

    @Column(name = "age_in_months", nullable = false)
    private Integer ageInMonths;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "pet_type", nullable = false)
    private String petType;

    @ManyToOne
    @JoinColumn(name = "user_details_id")
    private UserDetails user;

    @ManyToOne
    @JoinColumn(name = "shelter_id", nullable = false)
    private Shelter shelterId;

    @Column(name = "pet_status")
    private String petStatus;
}
