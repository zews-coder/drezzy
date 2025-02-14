package shoppingservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoppingservice.enitites.enums.Status;
import shoppingservice.services.BillService;
import shoppingservice.services.JwtService;
import shoppingservice.utils.dtos.bill.ChangeBillStatusDto;
import shoppingservice.utils.dtos.bill.CreateBillDto;

@RestController
@RequestMapping("/api/v1/bills")
@AllArgsConstructor
public class BillController {
    private BillService billService;
    private final JwtService jwtService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            if (jwtService.isAdmin(authorizationHeader.substring(7))){
                return ResponseEntity.ok(billService.getAllBills());
            }
            return ResponseEntity.status(403).build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("/bills/getAll went wrong");
        }
    }

    @GetMapping("/getAllPaid")
    @Operation(description = "getting all bills with status PAID")
    public ResponseEntity<?> getAllPaid(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            if (jwtService.isAdmin(authorizationHeader.substring(7))){
                return ResponseEntity.ok(billService.getAllPaid());
            }
            return ResponseEntity.status(403).build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("/bills/getAllPaid went wrong");
        }
    }

    @GetMapping("/getAllShipped")
    @Operation(description = "getting all bills with status PAID")
    public ResponseEntity<?> getAllShipped(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            if (jwtService.isAdmin(authorizationHeader.substring(7))){
                return ResponseEntity.ok(billService.getAllShipped());
            }
            return ResponseEntity.status(403).build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("/bills/getAllShipped went wrong");
        }
    }

    @GetMapping("/getAllDelivered")
    @Operation(description = "getting all bills with status FINISHED")
    public ResponseEntity<?> getAllDelivered(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            if (jwtService.isAdmin(authorizationHeader.substring(7))){
                return ResponseEntity.ok(billService.getAllDelivered());
            }
            return ResponseEntity.status(403).build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("/bills/getAllDelivered went wrong");
        }
    }

    @GetMapping("/getAllFromCustomer")
    @Operation(description = "getting all bills for one customer")
    public ResponseEntity<?> getAllFromCustomer(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            Long customerId = jwtService.extractId(authorizationHeader.substring(7));
            return ResponseEntity.ok(billService.getAllBillsByCustomerId(customerId));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("/bills/getAllFromCustomer went wrong");
        }
    }

    @PostMapping("/add")
    @Operation(description = "creating new bill")
    public ResponseEntity<?> getBill(@RequestHeader("Authorization") String authorizationHeader, @RequestBody CreateBillDto createBillDto) {
        try{
            Long customerId = jwtService.extractId(authorizationHeader.substring(7));
            return ResponseEntity.ok(billService.createBill(createBillDto, customerId));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("/bills/add went wrong");
        }
    }

    @PutMapping("/changeStatus")
    @Operation(description = "changeing order status")
    public ResponseEntity<?> changeStatus (@RequestBody ChangeBillStatusDto changeBillStatusDto) {
        try {
//            if (jwtService.isAdmin(authorizationHeader.substring(7))){
                billService.setBillStatus(changeBillStatusDto.getBillId(), Status.valueOf(changeBillStatusDto.getStatus()));
                return ResponseEntity.ok().build();
//            }
//            return ResponseEntity.status(403).build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("/bills/changeStatus went wrong");
        }
    }
}
