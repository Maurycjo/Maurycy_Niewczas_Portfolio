package pl.edu.pwr.internetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.internetapp.entity.Charge;
import pl.edu.pwr.internetapp.entity.Client;
import pl.edu.pwr.internetapp.service.implementation.ChargeService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ChargeController {

    private final ChargeService chargeService;

    @Autowired
    public ChargeController(ChargeService chargeService) {
        this.chargeService = chargeService;
    }

    @GetMapping("/charges")
    List<Charge> getAllCharges(){
        return chargeService.getAllCharges();
    }

    @GetMapping("/charges/{id}")
    Charge getOneCharge(@PathVariable Long id){
        return chargeService.getChargeById(id);
    }

    @PostMapping("/charges/")
    Charge newCharge(@RequestParam LocalDate paymentDeadline, @RequestParam float amountToPay, @RequestParam Long installationID){
        return chargeService.addCharge(paymentDeadline, amountToPay, installationID);
    }

    @PostMapping("/charges/{id}")
    Charge modifyCharge(@PathVariable Long id,
                        @RequestParam LocalDate paymentDeadline,
                        @RequestParam float amountToPay,
                        @RequestParam Long installationId){

        return chargeService.updateCharge(id, paymentDeadline, amountToPay, installationId);

    }




}
