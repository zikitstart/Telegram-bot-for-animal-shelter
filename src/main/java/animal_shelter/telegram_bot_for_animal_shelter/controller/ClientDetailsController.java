package animal_shelter.telegram_bot_for_animal_shelter.controller;

import animal_shelter.telegram_bot_for_animal_shelter.model.Client;
import animal_shelter.telegram_bot_for_animal_shelter.model.ClientDetails;
import animal_shelter.telegram_bot_for_animal_shelter.model.enums.Status;
import animal_shelter.telegram_bot_for_animal_shelter.service.impl.ClientDetailsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/client-details")
@RestController
// Контроллер для работы с клиентами (Усыновители)
public class ClientDetailsController {

    private final ClientDetailsServiceImpl clientDetailsService;

    public ClientDetailsController(ClientDetailsServiceImpl clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    @PostMapping
    @Operation(
            summary = "Создание усыновителя",
            description = "Метод для создания данных усыновителя"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Усыновитель создан.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            }
    )
    public ResponseEntity<ClientDetails> createClientDetails(@RequestParam Long clientId, @RequestParam Long petId, @RequestParam Status status, @RequestParam String startDate, @RequestParam boolean wasNotifiedOfStatusChange) {
        if (clientDetailsService.getClientDetailsByClientIdAndPetId(clientId, petId) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(clientDetailsService.createClientDetails(clientId, petId, status, startDate, wasNotifiedOfStatusChange));
    }

    @PutMapping
    @Operation(
            summary = "Изменение усыновителя.",
            description = "Метод для изменения данных усыновителя."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Клиент изменён.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            }
    )
    public ResponseEntity<ClientDetails> updateClientDetails(@RequestParam Long clientId, @RequestParam Long petId, @RequestParam Status status, @RequestParam String startDate, @RequestParam boolean wasNotifiedOfStatusChange) {
        if (clientDetailsService.getClientDetailsByClientIdAndPetId(clientId, petId) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(clientDetailsService.updateClientDetails(clientId, petId, status, startDate, wasNotifiedOfStatusChange));
    }

    @GetMapping("/extra")
    @Operation(
            summary = "Получение клиентов с extra периодом.",
            description = "Метод для получения данных клиентов с добавленым испытательным сроком"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Клиенты получены.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<ClientDetails>> getClientsWithExtraPeriod() {
        List<ClientDetails> clientDetails = clientDetailsService.getClientsWithExtraPeriod();
        if (clientDetails == null || clientDetails.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(clientDetails);
    }

    @GetMapping("/actual")
    @Operation(
            summary = "Получение актуальных клиентов",
            description = "Метод для получения клиентов, которые должны каждый день присылать отчеты"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Клиенты получены.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<ClientDetails>> getActualClients() {
        List<ClientDetails> clientDetails = clientDetailsService.getActualClients();
        if (clientDetails == null || clientDetails.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(clientDetails);
    }

    @GetMapping("/without-today-report")
    @Operation(
            summary = "Получение клиентов, которые не прислали отчет",
            description = "Получение списка клиентов, которые не прислали отчет за сегодняшний день"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Клиенты получены.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<ClientDetails>> getClientsWithoutTodayReport() {
        List<ClientDetails> clientDetails = clientDetailsService.getClientsWithoutTodayReport();
        if (clientDetails == null || clientDetails.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(clientDetails);
    }

    @GetMapping("/without-report")
    @Operation(
            summary = "Получение клиентов, которые не прислали отчет два дня",
            description = "Получение списка клиентов, которые не присылали отчеты последние два дня"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Клиенты получены.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<ClientDetails>> getClientsWithoutReportsForLastTwoDays() {
        List<ClientDetails> clientDetails = clientDetailsService.getClientsWithoutReportsForLastTwoDays();
        if (clientDetails == null || clientDetails.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(clientDetails);
    }

    @GetMapping("/end-trial-period")
    @Operation(
            summary = "Получение клиентов, которые закончили TRIAL период",
            description = "Получение списка клиентов, которые сегодня закончили свой испытательный период"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Клиенты получены.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<ClientDetails>> getClientsWhoEndTheirTrialPeriod() {
        List<ClientDetails> clientDetails = clientDetailsService.getClientsWhoEndTheirTrialPeriod();
        if (clientDetails == null || clientDetails.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(clientDetails);
    }

    @GetMapping("/end-14-period")
    @Operation(
            summary = "Получение клиентов, которые закончили EXTRA-14 период",
            description = "Получение списка клиентов, которые сегодня закончили свой добавочный испытательный период сроком 14 дней"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Клиенты получены.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<ClientDetails>> getClientsWhoEndTheirExtra14Period() {
        List<ClientDetails> clientDetails = clientDetailsService.getClientsWhoEndTheirExtra14Period();
        if (clientDetails == null || clientDetails.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(clientDetails);
    }

    @GetMapping("/end-30-period")
    @Operation(
            summary = "Получение клиентов, которые закончили EXTRA-30 период",
            description = "Получение списка клиентов, которые сегодня закончили свой добавочный испытательный период сроком 30 дней"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Клиенты получены.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<ClientDetails>> getClientsWhoEndTheirExtra30Period() {
        List<ClientDetails> clientDetails = clientDetailsService.getClientsWhoEndTheirExtra30Period();
        if (clientDetails == null || clientDetails.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(clientDetails);
    }

    @GetMapping("/in-status-decision")
    @Operation(
            summary = "Получение клиентов, которые в ожидании",
            description = "Получение списка клиентов, которые находятся в ожидании решения волонтёра по испытательному периоду"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Клиенты получены.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<ClientDetails>> getClientsInStatusWaitFoForDecision() {
        List<ClientDetails> clientDetails = clientDetailsService.getClientsInStatusWaitForDecision();
        if (clientDetails == null || clientDetails.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(clientDetails);
    }

    @GetMapping("/decision")
    @Operation(
            summary = "Получение клиентов, у которых волонтер сменил статус",
            description = "Получение списка клиентов, у которых волонтер сменил статус, для последующей отправки соответствующих сообщений"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Клиенты получены.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<ClientDetails>> getClientsWhoMustGetDecision() {
        List<ClientDetails> clientDetails = clientDetailsService.getClientsWhoMustGetNotificationAboutStatusChange();
        if (clientDetails == null || clientDetails.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(clientDetails);
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Удаление усыновителя.",
            description = "Метод для удаления данных усыновителя по clientDetailsId."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Усыновитель удалён.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            }
    )
    public ResponseEntity<ClientDetails> deleteClientDetails(@RequestParam("id") Long clientDetailsId) {
        if (clientDetailsService.getClientDetailsByClientDetailsId(clientDetailsId) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(clientDetailsService.deleteClientDetails(clientDetailsId));
    }
}