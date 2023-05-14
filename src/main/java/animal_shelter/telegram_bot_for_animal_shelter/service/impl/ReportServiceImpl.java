package animal_shelter.telegram_bot_for_animal_shelter.service.impl;

import animal_shelter.telegram_bot_for_animal_shelter.model.Report;
import animal_shelter.telegram_bot_for_animal_shelter.repository.ReportRepository;
import animal_shelter.telegram_bot_for_animal_shelter.service.ReportService;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Report createReport(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public Report getReport(Long id) {
        return reportRepository.findReportByPetId(id);
    }

    //Возможно не нужен
    @Override
    public Report getReportByReportId(Long id) {
        return reportRepository.findReportByReportId(id);
    }

    @Override
    public Report updateReport(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public Report deleteReport(Long id) {
        Report report = reportRepository.findReportByReportId(id);
        if (report != null){
            reportRepository.delete(report);
        }
        return report;
    }
}
