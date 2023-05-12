package animal_shelter.telegram_bot_for_animal_shelter.controller;

import animal_shelter.telegram_bot_for_animal_shelter.model.Client;
import animal_shelter.telegram_bot_for_animal_shelter.model.enums.PetType;
import animal_shelter.telegram_bot_for_animal_shelter.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/client")
@RestController
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @Operation(
            summary = "Создание клиента.",
            description = "Метод для создания клиента."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Клиент создан."
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
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        clientService.createClient(client);
        return ResponseEntity.ok(client);
    }

    @PutMapping
    @Operation(
            summary = "Изменение клиента.",
            description = "Метод для изменения клиента."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Клиент изменён."
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
    public ResponseEntity<Client> updateClient(@RequestBody Client client){
        if (clientService.getClientByChatId(client.getChatId()) ==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        clientService.updateClient(client);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/chatId/petType")
    @Operation(
            summary = "Получение клиента по chatId и petType.",
            description = "Метод для получения клиента."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Клиент получен."
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
    public ResponseEntity<Client> getClientByChatIdAndPetType(@RequestParam("chatId") Long chatId, @RequestParam("petType") PetType petType){
        return ResponseEntity.ok(clientService.getClientByChatIdAndPetType(chatId,petType));
    }

    @GetMapping("/chatId")
    @Operation(
            summary = "Получение клиента по chatId.",
            description = "Метод для получения клиента."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Клиент получен."
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
    public ResponseEntity<List<Client>> getClientByChatId(@RequestParam("chatId")  Long chatId){
        return ResponseEntity.ok(clientService.getClientByChatId(chatId));
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Удаление клиента.",
            description = "Метод для удаления клиента по id."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Клиент удалён."
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
    public ResponseEntity<Long> deleteClient(@RequestParam("id")  Long id){
        if (clientService.getClientByChatId(id) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        clientService.deleteClient(id);
        return ResponseEntity.ok(id);
    }

    //Возможно излишний метод,так как в телеграмме регистрация через номер телефона и он указывается перманентно
    public void addPhoneNumber(Long chatId, String phoneNumber) {
        clientService.fillClientPhoneNumberByChatId(chatId, phoneNumber);
    }
}
