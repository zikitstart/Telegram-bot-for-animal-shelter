package animal_shelter.telegram_bot_for_animal_shelter.controller;

import animal_shelter.telegram_bot_for_animal_shelter.model.Client;
import animal_shelter.telegram_bot_for_animal_shelter.model.ClientDetails;
import animal_shelter.telegram_bot_for_animal_shelter.service.impl.ClientDetailsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/client-details")
@RestController
public class ClientDetailsController {

    private final ClientDetailsServiceImpl clientDetailsService;

    public ClientDetailsController(ClientDetailsServiceImpl clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    @PostMapping
    @Operation(
            summary = "Создание ClientDetails.",
            description = "Метод для создания ClientDetails."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "ClientDetails создан.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            }
    )
    public ResponseEntity<ClientDetails> createClient(@RequestBody ClientDetails clientDetails){
        clientDetailsService.createClientDetails(clientDetails);
        return ResponseEntity.ok(clientDetails);
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
                            description = "Клиент изменён.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            }
    )
    public ResponseEntity<ClientDetails> updateClient(@RequestBody ClientDetails clientDetails){
        if (clientDetailsService.getClientDetailsByClientDetailsId(clientDetails.getClientDetailsId()) ==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        clientDetailsService.updateClientDetails(clientDetails);
        return ResponseEntity.ok(clientDetails);
    }

    @GetMapping("/extra")
    public ResponseEntity<List<ClientDetails>> getClientsWithExtraPeriod(){
        List<ClientDetails> clientDetails = clientDetailsService.getClientsWithExtraPeriod();
        if (clientDetails == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(clientDetails);
        }
    }
    @GetMapping("/actual")
    public ResponseEntity<List<ClientDetails>> getActualClients(){
        List<ClientDetails> clientDetails = clientDetailsService.getActualClients();
        if (clientDetails == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(clientDetails);
        }
    }
    @GetMapping("/without-today-report")
    public ResponseEntity<List<ClientDetails>> getClientsWithoutTodayReport(){
        List<ClientDetails> clientDetails = clientDetailsService.getClientsWithoutTodayReport();
        if (clientDetails == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(clientDetails);
        }
    }
    @GetMapping("/without-report")
    public ResponseEntity<List<ClientDetails>> getClientsWithoutReportsForLastTwoDays(){
        List<ClientDetails> clientDetails = clientDetailsService.getClientsWithoutReportsForLastTwoDays();
        if (clientDetails == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(clientDetails);
        }
    }
    @GetMapping("/end-trial-period")
    public ResponseEntity<List<ClientDetails>> getClientsWhoEndTheirTrialPeriod(){
        List<ClientDetails> clientDetails = clientDetailsService.getClientsWhoEndTheirTrialPeriod();
        if (clientDetails == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(clientDetails);
        }
    }
    @GetMapping("/end-14-period")
    public ResponseEntity<List<ClientDetails>> getClientsWhoEndTheirExtra14Period(){
        List<ClientDetails> clientDetails = clientDetailsService.getClientsWhoEndTheirExtra14Period();
        if (clientDetails == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(clientDetails);
        }
    }
    @GetMapping("/end-30-period")
    public ResponseEntity<List<ClientDetails>> getClientsWhoEndTheirExtra30Period(){
        List<ClientDetails> clientDetails = clientDetailsService.getClientsWhoEndTheirExtra30Period();
        if (clientDetails == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(clientDetails);
        }
    }
    @GetMapping("/in-status-decision")
    public ResponseEntity<List<ClientDetails>> getClientsInStatusWaitFoForDecision(){
        List<ClientDetails> clientDetails = clientDetailsService.getClientsInStatusWaitForDecision();
        if (clientDetails == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(clientDetails);
        }
    }
    @GetMapping("/decision")
    public ResponseEntity<List<ClientDetails>> getClientsWhoMustGetDecision(){
        List<ClientDetails> clientDetails = clientDetailsService.getClientsWhoMustGetNotificationAboutStatusChange();
        if (clientDetails == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(clientDetails);
        }
    }

}
