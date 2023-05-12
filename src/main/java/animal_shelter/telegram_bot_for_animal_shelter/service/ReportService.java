package animal_shelter.telegram_bot_for_animal_shelter.service;

import animal_shelter.telegram_bot_for_animal_shelter.model.Report;

public interface ReportService {
    Report createReport(Report report);

    Report readReport(Long id);

    Report updateReport(Report report);

    void deleteReport(Long id);
}
