package animal_shelter.telegram_bot_for_animal_shelter.controller;

import animal_shelter.telegram_bot_for_animal_shelter.model.ClientDetails;
import animal_shelter.telegram_bot_for_animal_shelter.service.impl.ClientDetailsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/client-details")
@RestController
public class ClientDetailsController {

    private final ClientDetailsServiceImpl clientDetailsService;

    public ClientDetailsController(ClientDetailsServiceImpl clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
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
        List<ClientDetails> clientDetails = clientDetailsService.getClientsInStatusWaitFoForDecision();
        if (clientDetails == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(clientDetails);
        }
    }
    @GetMapping("/decision")
    public ResponseEntity<List<ClientDetails>> getClientsWhoMustGetDecision(){
        List<ClientDetails> clientDetails = clientDetailsService.getClientsWhoMustGetDecision();
        if (clientDetails == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(clientDetails);
        }
    }

}
