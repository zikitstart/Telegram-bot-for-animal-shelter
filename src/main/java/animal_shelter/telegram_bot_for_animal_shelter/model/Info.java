package animal_shelter.telegram_bot_for_animal_shelter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

// Класс для различной информации о взаимодействии с приютами и животными
@Component
@Entity
@Table(name = "info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_id")
    private Long infoId;

    @Column(name = "dating_rules_pet", nullable = false)
    private String datingRulesPet; //Правила знакомства с животными
    @Column(name = "list_documents_pet", nullable = false)
    private String listDocumentsPet; //Список документов
    @Column(name = "transporting_pet", nullable = false)
    private String transportingPet; //Рекомендации для транспортировки
    @Column(name = "recommendations_little_pet", nullable = false)
    private String recommendationsLittlePet; //Рекомендации для котёнка\щенка
    @Column(name = "recommendations_adult_pet", nullable = false)
    private String recommendationsAdultPet; //Рекомендации для кота\собаки
    @Column(name = "recommendations_with_disabilities_pet", nullable = false)
    private String recommendationsWithDisabilitiesPet; //Рекомендации для животного с ограниченными возможностями
    @Column(name = "reasons_refusal_pet", nullable = false)
    private String reasonsRefusalPet; //Причины отказа

    @Column(name = "tips_handler_dog")
    private String tipsHandlerDog; //Советы кинолога
    @Column(name = "recommendations_handler_dog")
    private String recommendationsHandlerDog; //Рекомендации по проверенным кинологам
}
