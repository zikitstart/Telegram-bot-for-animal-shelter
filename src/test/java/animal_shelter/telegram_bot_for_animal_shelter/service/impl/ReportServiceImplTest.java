package animal_shelter.telegram_bot_for_animal_shelter.service.impl;

import animal_shelter.telegram_bot_for_animal_shelter.model.Pet;
import animal_shelter.telegram_bot_for_animal_shelter.model.Report;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReportServiceImplTest {

    private Report report1;
    private List<Report> reportList;

    @BeforeEach
    void setUp() {
        byte[] photo = {1, 2, 3};
        Pet pet = new Pet(1L, 10, null, null, null);
        report1 = new Report(1L, "Отчёт1", photo, LocalDate.now(), pet);
        Report report2 = new Report(2L, "Отчёт2", photo, LocalDate.now(), pet);
        Report report3 = new Report(3L, "Отчёт3", photo, LocalDate.now(), pet);

        reportList = List.of(report1, report2, report3);
    }

    @Test
    @DisplayName("When an object is created with parameters passed to it, then true")
    void createReport() {
        byte[] photo = {1, 2, 3};
        Pet pet = new Pet(1L, 10, null, null, null);
        Report reportExpected = new Report(1L, "Отчёт1", photo, LocalDate.now(), pet);
        assertEquals(reportExpected, report1);
    }

    @Test
    @DisplayName("When an object is update with parameters passed to it, then true")
    void updateReport() {
        byte[] photo = {2, 2, 2};
        Pet pet = new Pet(1L, 10, null, null, null);
        Report reportExpected = new Report(5L, "Отчёт111", photo, LocalDate.now(), pet);
        report1.setReportId(5L);
        report1.setPetReport("Отчёт111");
        report1.setPhoto(photo);
        assertEquals(reportExpected, report1);
    }

    @Test
    @DisplayName("When we find an object by reportId, then true")
    void getReportByReportId() {
        Report reportExpected = null;
        for (Report report : reportList) {
            if (report.getReportId().equals(1L)) {
                reportExpected = report;
            }
        }
        assertEquals(reportExpected, report1);
    }
}