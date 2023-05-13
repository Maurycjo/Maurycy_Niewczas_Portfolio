package pl.edu.pwr.internetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.internetapp.entity.Payment;
import pl.edu.pwr.internetapp.service.implementation.PaymentService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payments")
    List<Payment> getAllPayments(){
        return paymentService.getAllPayments();
    }

    @GetMapping("payments/{id}")
    Payment getOnePayment(@PathVariable Long id){
        return paymentService.getPaymentById(id);
    }

    @PostMapping("/payments/")
    Payment newPayment(@RequestParam LocalDate paymentDate, float amount, Long installationId){
        return paymentService.addPayment(paymentDate, amount, installationId);
    }

    @DeleteMapping("/payments/{id}")
    void deletePayment(@PathVariable Long id){
        paymentService.deletePayment(id);
    }

}
