package animal_shelter.telegram_bot_for_animal_shelter.controller;

import animal_shelter.telegram_bot_for_animal_shelter.model.Client;
import animal_shelter.telegram_bot_for_animal_shelter.model.enums.PetType;
import animal_shelter.telegram_bot_for_animal_shelter.service.ClientService;
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

@RequestMapping("/client")
@RestController
// Контроллер для работы с клиентом (Потенциальный усыновитель)
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/chatId-petType")
    @Operation(
            summary = "Получение клиента по chatId и petType.",
            description = "Метод для получения данных клиента по chatId и petType."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Клиент получен.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            }
    )
    public ResponseEntity<Client> getClientByChatIdAndPetType(@RequestParam("chatId") Long chatId, @RequestParam("petType") PetType petType) {
        if (clientService.getClientByChatIdAndPetType(chatId, petType) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Client client = clientService.getClientByChatIdAndPetType(chatId, petType);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/chatId")
    @Operation(
            summary = "Получение клиента по chatId.",
            description = "Метод для получения данных клиента по chatId."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Клиент получен.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<Client>> getClientByChatId(@RequestParam("chatId") Long chatId) {
        if (clientService.getClientByChatId(chatId) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(clientService.getClientByChatId(chatId));
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Удаление клиента.",
            description = "Метод для удаления данных клиента по userId."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Клиент удалён.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            }
    )
    public ResponseEntity<Long> deleteClient(@RequestParam("id") Long userId) {
        if (clientService.getClientByUserId(userId) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        clientService.deleteClient(userId);
        return ResponseEntity.ok(userId);
    }
}
