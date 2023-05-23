package animal_shelter.telegram_bot_for_animal_shelter.service;

import animal_shelter.telegram_bot_for_animal_shelter.model.Report;

import java.util.List;

public interface ReportService {
    Report createReport(Report report);

    List<Report> getReportByPetId(Long id);

    Report getReportByReportId(Long id);

    Report updateReport(Report report);

    Report deleteReport(Long id);
}
