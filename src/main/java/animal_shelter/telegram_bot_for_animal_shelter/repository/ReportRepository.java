package animal_shelter.telegram_bot_for_animal_shelter.repository;

import animal_shelter.telegram_bot_for_animal_shelter.model.Pet;
import animal_shelter.telegram_bot_for_animal_shelter.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    //Найти отчёт по petId от начала до окончания испытательного срока
    Report findReportByPetId(Long petId);

    Report findReportByReportId(Long reportId);

    Report getReportByPetIdAndDateOfReport(long petId, LocalDate dateOfReport);
}
