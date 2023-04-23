package animal_shelter.telegram_bot_for_animal_shelter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

// Класс для пользователей, которые взяли животное из приюта
@Entity
@Table(name = "user_context")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserContext {
    @Id
    @Column(name = "chat_id")
    private Long chatId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinTable(name = "pets",
            joinColumns = @JoinColumn(name = "user_context_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pet_id", referencedColumnName = "id"))
    private Set<Pet> pets;
}
