package animal_shelter.telegram_bot_for_animal_shelter.model;

import animal_shelter.telegram_bot_for_animal_shelter.model.enums.PetType;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

// Класс для описания домашнего питомца
@Entity
@Table(name = "pet")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
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

    @Enumerated(EnumType.STRING)
    @Column(name = "pet_type", nullable = false)
    @Type(type = "pgsql_enum")
    private PetType petType;

    @OneToOne(mappedBy = "petId")
    private ClientDetails clientDetailsId;

    @ManyToOne
    @JoinColumn(name = "shelter_id", nullable = false)
    private Shelter shelterId;
}
