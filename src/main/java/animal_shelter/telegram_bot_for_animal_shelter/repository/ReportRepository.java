package animal_shelter.telegram_bot_for_animal_shelter.repository;

import animal_shelter.telegram_bot_for_animal_shelter.model.Pet;
import animal_shelter.telegram_bot_for_animal_shelter.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    // Поиск отчёта по petId
    List<Report> findReportByPetId(Pet petId);

    // Поиск отчёта по reportId
    Report findReportByReportId(Long reportId);

    // Получение отчёта по petId и дате отчёта
    Report getReportByPetIdAndDateOfReport(long petId, LocalDate dateOfReport);
}
