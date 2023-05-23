package animal_shelter.telegram_bot_for_animal_shelter.controller;

import animal_shelter.telegram_bot_for_animal_shelter.model.Pet;
import animal_shelter.telegram_bot_for_animal_shelter.model.Report;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReportControllerTest {

    private Pet pet1;
    private Report report1;
    private Report report2;
    private Report report3;
    private List<Report> reportList;

    @BeforeEach
    void setUp() {
        byte[] photo = {1, 2, 3};
        pet1 = new Pet(1L, 10, null, null, null);
        Pet pet2 = new Pet(2L, 11, null, null, null);
        report1 = new Report(1L, "Отчёт1", photo, LocalDate.now(), pet1);
        report2 = new Report(2L, "Отчёт2", photo, LocalDate.now(), pet2);
        report3 = new Report(3L, "Отчёт3", photo, LocalDate.now(), pet2);

        reportList = List.of(report1, report2, report3);
    }

    @Test
    @DisplayName("When we find an object by petId, then true")
    void getReportByPetId() {
        Report reportExpected = null;
        for (Report report : reportList) {
            if (report.getPetId().getPetId().equals(pet1.getPetId())) {
                reportExpected = report;
            }
        }
        assertEquals(reportExpected, report1);
    }

    @Test
    @DisplayName("When we find an object by reportId, then delete object")
    void deleteReport() {
        List<Report> reportExpected = List.of(report1, report2);
        List<Report> reportActual = new ArrayList<>(List.of(report1, report2, report3));
        reportActual.removeIf(report -> report.getReportId().equals(3L));
        assertEquals(reportExpected, reportActual);
    }
}