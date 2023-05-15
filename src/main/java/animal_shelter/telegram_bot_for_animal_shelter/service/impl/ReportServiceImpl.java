package animal_shelter.telegram_bot_for_animal_shelter.service.impl;

import animal_shelter.telegram_bot_for_animal_shelter.model.Report;
import animal_shelter.telegram_bot_for_animal_shelter.repository.ReportRepository;
import animal_shelter.telegram_bot_for_animal_shelter.service.ReportService;
import org.springframework.stereotype.Service;

@Service
// Класс для работы с отчётами
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    // Создание отчёта
    @Override
    public Report createReport(Report report) {
        return reportRepository.save(report);
    }

    // Получение отчёта по id
    @Override
    public Report getReport(Long id) {
        return reportRepository.findReportByPetId(id);
    }

    // Получение отчёта по reportId
    @Override
    public Report getReportByReportId(Long id) {
        return reportRepository.findReportByReportId(id);
    }

    // Изменение отчёта
    @Override
    public Report updateReport(Report report) {
        return reportRepository.save(report);
    }

    // Удаление отчёта по id
    @Override
    public Report deleteReport(Long id) {
        Report report = reportRepository.findReportByReportId(id);
        if (report != null){
            reportRepository.delete(report);
        }
        return report;
    }
}
