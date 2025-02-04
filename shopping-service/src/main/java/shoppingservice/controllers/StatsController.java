package shoppingservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shoppingservice.services.StatisticService;

@RestController
@RequestMapping("/api/v1/stats")
@AllArgsConstructor
public class StatsController {
    private final StatisticService statisticService;

    @GetMapping(path = "/earnings")
    @Operation(description = "Returns profit")
    public ResponseEntity<?> earnings() {
        try {
            return ResponseEntity.ok(statisticService.earnings());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("/earnings failed");
        }
    }

    @GetMapping(path = "/itemsSold")
    @Operation(description = "Returns profit")
    public ResponseEntity<?> itemsSold() {
        try{
            return ResponseEntity.ok(statisticService.itemsSold());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("/itemsSold failed");
        }
    }

    @GetMapping(path = "/billsByStats")
    @Operation(description = "Returns, in number, status of all bills")
    public ResponseEntity<?> billsByStats() {
        try{
            return ResponseEntity.ok(statisticService.billsByStatus());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("/billsByStats failed");
        }
    }
}
