package animal_shelter.telegram_bot_for_animal_shelter.controller;

import animal_shelter.telegram_bot_for_animal_shelter.model.Report;
import animal_shelter.telegram_bot_for_animal_shelter.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/report")
@RestController
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    @Operation(
            summary = "Создание отчёта.",
            description = "Метод для создания отчёта."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Отчёт создан."
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Параметры запроса отсутствуют или имеют некорректный формат."
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Произошла ошибка, не зависящая от вызывающей стороны."
                    )
            }
    )
    public ResponseEntity<Report> createReport(@RequestBody Report report){
        return ResponseEntity.ok(reportService.createReport(report));
    }
    @GetMapping("{id}")
    @Operation(
            summary = "Получение отчёта.",
            description = "Метод для получения отчёта."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Отчёт получен."
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "id отсутствует или имеет некорректный формат."
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Произошла ошибка, не зависящая от вызывающей стороны."
                    )
            }
    )
    public ResponseEntity<Report> readReport(@PathVariable Long id){
        return ResponseEntity.ok(reportService.readReport(id));
    }
    @PutMapping
    @Operation(
            summary = "Обновление отчёта.",
            description = "Метод для обновления отчёта."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Отчёт обновлён."
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Параметры запроса отсутствуют или имеют некорректный формат."
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Произошла ошибка, не зависящая от вызывающей стороны."
                    )
            }
    )
    public ResponseEntity<Report> updateReport(@RequestBody Report report){
        if (reportService.readReport(report.getPetId().getPetId()) ==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(reportService.updateReport(report));
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
                            description = "Отчёт удалён."
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "id отсутствует или имеет некорректный формат."
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Произошла ошибка, не зависящая от вызывающей стороны."
                    )
            }
    )
    public ResponseEntity<Long> deleteReport(@PathVariable Long id){
        if (reportService.readReport(id) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        reportService.deleteReport(id);
        return ResponseEntity.ok(id);
    }
}
