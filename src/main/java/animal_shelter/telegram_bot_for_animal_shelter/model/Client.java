package animal_shelter.telegram_bot_for_animal_shelter.model;

import animal_shelter.telegram_bot_for_animal_shelter.model.enums.PetType;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

// Класс для описания пользователей, зарегистрировавшихся в системе и оставивших контактные данные
@Entity
@Table(name = "client")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long userId;

    @Column(name = "chat_id", nullable = false)
    private Long chatId;

    @Column(name = "surname")
    private String surname;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "pet_type")
    @Type(type = "pgsql_enum")
    private PetType petType;

    public Client(Long chatId, String surname, String firstName, String lastName, String phoneNumber, PetType petType) {
        this.chatId = chatId;
        this.surname = surname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.petType = petType;
    }

    public Client(Long chatId, String surname, String firstName, String lastName) {
        this.chatId = chatId;
        this.surname = surname;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Client(Long chatId, String surname, String firstName, String lastName, PetType petType) {
        this.chatId = chatId;
        this.surname = surname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.petType = petType;
    }
}
