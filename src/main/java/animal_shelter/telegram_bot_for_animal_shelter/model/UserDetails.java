package animal_shelter.telegram_bot_for_animal_shelter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

// Класс для пользователей, которые взяли животное из приюта
@Entity
@Table(name = "user_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
    @Id
    @Column(name = "chat_id")
    private Long chatId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "petId")
    private List<Pet> petIds;
}
