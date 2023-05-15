package animal_shelter.telegram_bot_for_animal_shelter.model;

import animal_shelter.telegram_bot_for_animal_shelter.model.enums.PetType;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Objects;

// Класс для описания домашнего питомца
@Entity
@Table(name = "pet")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
@Getter
@Setter
@ToString
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

    @Enumerated(EnumType.STRING)
    @Column(name = "pet_type", nullable = false)
    @Type(type = "pgsql_enum")
    private PetType petType;

    @OneToOne(mappedBy = "petId")
    private ClientDetails clientDetailsId;

    @ManyToOne
    @JoinColumn(name = "shelter_id", nullable = false)
    private Shelter shelterId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(petId, pet.petId) && Objects.equals(ageInMonths, pet.ageInMonths) && Objects.equals(name, pet.name) && petType == pet.petType && Objects.equals(clientDetailsId, pet.clientDetailsId) && Objects.equals(shelterId, pet.shelterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(petId, ageInMonths, name, petType, clientDetailsId, shelterId);
    }
}
