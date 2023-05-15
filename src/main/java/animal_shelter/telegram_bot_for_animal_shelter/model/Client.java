package animal_shelter.telegram_bot_for_animal_shelter.model;

import animal_shelter.telegram_bot_for_animal_shelter.model.enums.PetType;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Objects;

// Класс для описания пользователей, зарегистрировавшихся в системе и оставивших контактные данные
@Entity
@Table(name = "client")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
@Getter
@Setter
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(userId, client.userId) && Objects.equals(chatId, client.chatId) && Objects.equals(surname, client.surname) && Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName) && Objects.equals(phoneNumber, client.phoneNumber) && petType == client.petType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, chatId, surname, firstName, lastName, phoneNumber, petType);
    }
}
