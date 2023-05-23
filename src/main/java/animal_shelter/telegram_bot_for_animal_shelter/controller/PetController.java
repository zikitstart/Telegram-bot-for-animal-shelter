package animal_shelter.telegram_bot_for_animal_shelter.controller;

import animal_shelter.telegram_bot_for_animal_shelter.model.Client;
import animal_shelter.telegram_bot_for_animal_shelter.model.Pet;
import animal_shelter.telegram_bot_for_animal_shelter.model.enums.PetType;
import animal_shelter.telegram_bot_for_animal_shelter.service.PetService;
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

@RequestMapping("/pet")
@RestController
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @Operation(
            summary = "Создание питомца.",
            description = "Метод для создания питомца."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Питомец создан.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestParam Integer ageInMonths, @RequestParam String name, @RequestParam PetType petType) {
        if (petService.getPetByParameters(name, petType) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(petService.createPet(ageInMonths, name, petType));
    }

    @PutMapping
    @Operation(
            summary = "Изменение питомца.",
            description = "Метод для изменения питомца."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Питомец изменён.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            }
    )
    public ResponseEntity<Pet> updatePet(@RequestParam Long petId, @RequestParam Integer ageInMonths, @RequestParam String name) {
        if (petService.getPet(petId) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(petService.updatePet(petId, ageInMonths, name));
    }

    @GetMapping("/get-pet")
    @Operation(
            summary = "Получение питомца.",
            description = "Метод для получения данных питомца по petId."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Питомец получен.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            }
    )
    public ResponseEntity<Pet> getPet(@RequestParam("petId") Long petId) {
        if (petService.getPet(petId) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(petService.getPet(petId));
    }

    @GetMapping("/get-pets")
    @Operation(
            summary = "Получение питомцев.",
            description = "Метод для получения данных питомцев по petType."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Питомцы получены.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<Pet>> getPetsByType(@RequestParam("petId") PetType petType) {
        List<Pet> pets = petService.getPetsByTypes(petType);
        if (pets == null || pets.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(pets);
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Удаление питомца.",
            description = "Метод для удаления данных питомца по petId."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Питомец удалён.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            }
    )
    public ResponseEntity<Pet> deletePet(@RequestParam("id") Long petId) {
        if (petService.getPet(petId) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(petService.deletePet(petId));
    }
}
