package animal_shelter.telegram_bot_for_animal_shelter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

// Класс для описания структуры ежедневного отчета
@Entity
@Table(name = "report")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pet_report")
    private String petReport;

    @Column(name = "photo")
    private byte[] photo;

    @Column(name = "date_of_report")
    private LocalDateTime dateOfReport;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
}
