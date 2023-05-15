package animal_shelter.telegram_bot_for_animal_shelter.controller;

import animal_shelter.telegram_bot_for_animal_shelter.model.Client;
import animal_shelter.telegram_bot_for_animal_shelter.model.Pet;
import animal_shelter.telegram_bot_for_animal_shelter.model.Report;
import animal_shelter.telegram_bot_for_animal_shelter.service.PetService;
import animal_shelter.telegram_bot_for_animal_shelter.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/report")
@RestController
// Контроллер для работы с отчётами
public class ReportController {

    private final PetService petService;
    private final ReportService reportService;

    public ReportController(PetService petService, ReportService reportService) {
        this.petService = petService;
        this.reportService = reportService;
    }
    @GetMapping("{id}")
    @Operation(
            summary = "Получение отчёта",
            description = "Метод для получения отчёта по id"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Отчёт получен",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            }
    )
    public ResponseEntity<Report> getReport(@PathVariable Long id){
        Pet pet = petService.getPet(id);
        Report report = reportService.getReport(pet.getPetId());
        return ResponseEntity.ok(report);
    }
    @DeleteMapping("{id}")
    @Operation(
            summary = "Удаление отчёта.",
            description = "Метод для удаления отчёта по id."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Отчёт удалён.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            }
    )
    public ResponseEntity<Long> deleteReport(@PathVariable Long id){
        if (reportService.getReportByReportId(id) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        reportService.deleteReport(id);
        return ResponseEntity.ok(id);
    }
}
