package animal_shelter.telegram_bot_for_animal_shelter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// Класс для различной информации о взаимодействии с приютами и животными
@Entity
@Table(name = "info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "recommendations")
    private String recommendations; // Правила знакомства с животными, список документов, причины отказа, советы кинологов и список проверенных кинологов

    @Column(name = "instructions")
    private String instructions; // разные рекомендации по транспортировке и обустройстве жилища
}
