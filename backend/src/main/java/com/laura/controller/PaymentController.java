package com.laura.controller;

import com.laura.model.PaymentRequest;
import com.laura.model.PaymentResponse;
import com.laura.service.StripePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Permitir acesso do frontend
public class PaymentController {

    private final StripePaymentService stripePaymentService;

    @PostMapping("/card")
    public ResponseEntity<PaymentResponse> createCardPayment(@RequestBody PaymentRequest paymentRequest) {
        paymentRequest.setCurrency(paymentRequest.getCurrency() != null ? paymentRequest.getCurrency() : "brl");
        PaymentResponse response = stripePaymentService.createPaymentIntent(paymentRequest);
        return response.getSuccess() 
                ? ResponseEntity.ok(response)
                : ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/pix")
    public ResponseEntity<PaymentResponse> createPixPayment(@RequestBody PaymentRequest paymentRequest) {
        paymentRequest.setCurrency(paymentRequest.getCurrency() != null ? paymentRequest.getCurrency() : "brl");
        PaymentResponse response = stripePaymentService.createPixPayment(paymentRequest);
        return response.getSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.badRequest().body(response);
    }

    @GetMapping("/confirm/{paymentId}")
    public ResponseEntity<PaymentResponse> confirmPayment(@PathVariable String paymentId) {
        PaymentResponse response = stripePaymentService.confirmPayment(paymentId);
        return response.getSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.badRequest().body(response);
    }

    @GetMapping("/test")    
    public ResponseEntity<String> testPaymentApi() {
        return ResponseEntity.ok("Stripe payment API is working!");
    }
} 