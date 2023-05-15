package animal_shelter.telegram_bot_for_animal_shelter.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

// Класс для описания структуры ежедневного отчета
@Entity
@Table(name = "report")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long reportId;

    @Column(name = "pet_report")
    private String petReport;

    @Column(name = "photo")
    private byte[] photo;

    @Column(name = "date_of_report", nullable = false)
    private LocalDate dateOfReport;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet petId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(reportId, report.reportId) && Objects.equals(petReport, report.petReport) && Arrays.equals(photo, report.photo) && Objects.equals(dateOfReport, report.dateOfReport) && Objects.equals(petId, report.petId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(reportId, petReport, dateOfReport, petId);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }
}
