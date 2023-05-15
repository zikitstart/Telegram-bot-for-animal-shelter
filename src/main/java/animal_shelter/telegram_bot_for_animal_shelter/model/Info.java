package animal_shelter.telegram_bot_for_animal_shelter.model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

// Класс для различной информации о взаимодействии с приютами и животными
@Component
@Entity
@Table(name = "info")
@Getter
@Setter
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Info info = (Info) o;
        return Objects.equals(infoId, info.infoId) && Objects.equals(datingRulesPet, info.datingRulesPet) && Objects.equals(listDocumentsPet, info.listDocumentsPet) && Objects.equals(transportingPet, info.transportingPet) && Objects.equals(recommendationsLittlePet, info.recommendationsLittlePet) && Objects.equals(recommendationsAdultPet, info.recommendationsAdultPet) && Objects.equals(recommendationsWithDisabilitiesPet, info.recommendationsWithDisabilitiesPet) && Objects.equals(reasonsRefusalPet, info.reasonsRefusalPet) && Objects.equals(tipsHandlerDog, info.tipsHandlerDog) && Objects.equals(recommendationsHandlerDog, info.recommendationsHandlerDog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(infoId, datingRulesPet, listDocumentsPet, transportingPet, recommendationsLittlePet, recommendationsAdultPet, recommendationsWithDisabilitiesPet, reasonsRefusalPet, tipsHandlerDog, recommendationsHandlerDog);
    }
}
