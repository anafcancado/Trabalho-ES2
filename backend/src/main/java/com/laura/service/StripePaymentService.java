package com.laura.service;

import com.laura.model.PaymentRequest;
import com.laura.model.PaymentResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentMethodCreateParams;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class StripePaymentService {

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @PostConstruct
    public void init() {
        // Na prática, você usaria a chave da sua conta Stripe
        // Mas para um teste rápido, podemos usar uma chave de teste fixa
        if (stripeApiKey == null || stripeApiKey.isBlank()) {
            stripeApiKey = "sk_test_51ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; // Chave fictícia para testes
        }
        Stripe.apiKey = stripeApiKey;
    }

    /**
     * Cria um PaymentIntent no Stripe para simular um pagamento
     */
    public PaymentResponse createPaymentIntent(PaymentRequest paymentRequest) {
        try {
            // 1. Criar um cliente
            Customer customer = createCustomer(paymentRequest);
            
            // 2. Configurar os parâmetros do PaymentIntent
            PaymentIntentCreateParams.Builder paramsBuilder = PaymentIntentCreateParams.builder()
                    .setCurrency(paymentRequest.getCurrency())
                    .setAmount(paymentRequest.getAmount())
                    .setDescription(paymentRequest.getDescription())
                    .setCustomer(customer.getId())
                    .setReceiptEmail(paymentRequest.getEmail())
                    .setAutomaticPaymentMethods(
                            PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                    .setEnabled(true)
                                    .build()
                    )
                    // Adicionar metadados, como endereço de entrega e telefone
                    .putMetadata("address", paymentRequest.getAddress())
                    .putMetadata("phoneNumber", paymentRequest.getPhoneNumber());

            // 3. Criar o PaymentIntent
            PaymentIntent paymentIntent = PaymentIntent.create(paramsBuilder.build());
            
            // 4. Construir a resposta
            return PaymentResponse.builder()
                    .paymentId(paymentIntent.getId())
                    .clientSecret(paymentIntent.getClientSecret())
                    .paymentStatus(paymentIntent.getStatus())
                    .success(true)
                    .message("Payment Intent created successfully")
                    .build();

        } catch (StripeException e) {
            log.error("Error creating payment intent: {}", e.getMessage(), e);
            return PaymentResponse.builder()
                    .success(false)
                    .message("Failed to create payment: " + e.getMessage())
                    .build();
        }
    }

    /**
     * Simula um pagamento PIX usando o Stripe (em produção, usaria APIs específicas para PIX)
     */
    public PaymentResponse createPixPayment(PaymentRequest paymentRequest) {
        try {
            // Na prática, para PIX você usaria uma API que gera QR codes PIX
            // Aqui, simularemos apenas com um PaymentIntent
            
            Customer customer = createCustomer(paymentRequest);
            
            Map<String, Object> paymentIntentParams = new HashMap<>();
            paymentIntentParams.put("amount", paymentRequest.getAmount());
            paymentIntentParams.put("currency", paymentRequest.getCurrency());
            paymentIntentParams.put("customer", customer.getId());
            paymentIntentParams.put("description", paymentRequest.getDescription());
            paymentIntentParams.put("payment_method_types", new String[]{"card"}); // Na prática, seria "pix" se Stripe suportasse no Brasil
            
            PaymentIntent intent = PaymentIntent.create(paymentIntentParams);
            
            // Simulando um código PIX para teste
            String simulatedPixCode = "00020126580014br.gov.bcb.pix0136" + 
                    intent.getId().replace("pi_", "") + 
                    "5204000053039865406" + 
                    paymentRequest.getAmount() + 
                    "5802BR5913PizzaAqui";
            
            return PaymentResponse.builder()
                    .paymentId(intent.getId())
                    .paymentStatus("awaiting_payment")
                    .paymentUrl(simulatedPixCode) // Código PIX simulado
                    .success(true)
                    .message("PIX payment code generated")
                    .build();
            
        } catch (StripeException e) {
            log.error("Error creating PIX payment: {}", e.getMessage(), e);
            return PaymentResponse.builder()
                    .success(false)
                    .message("Failed to create PIX payment: " + e.getMessage())
                    .build();
        }
    }
    
    /**
     * Confirma o status de um pagamento
     */
    public PaymentResponse confirmPayment(String paymentId) {
        try {
            PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentId);
            
            // Para teste, sempre consideraremos como sucesso
            if ("succeeded".equals(paymentIntent.getStatus())) {
                return PaymentResponse.builder()
                        .paymentId(paymentId)
                        .paymentStatus(paymentIntent.getStatus())
                        .success(true)
                        .message("Payment confirmed")
                        .build();
            } else {
                // Simulando que o pagamento foi confirmado 
                // (em produção, você verificaria o status real)
                Map<String, Object> params = new HashMap<>();
                params.put("status", "succeeded");
                PaymentIntent updatedIntent = paymentIntent.update(params);
                
                return PaymentResponse.builder()
                        .paymentId(paymentId)
                        .paymentStatus("succeeded")
                        .success(true)
                        .message("Payment simulated as successful")
                        .build();
            }
        } catch (StripeException e) {
            log.error("Error confirming payment: {}", e.getMessage(), e);
            return PaymentResponse.builder()
                    .paymentId(paymentId)
                    .success(false)
                    .message("Failed to confirm payment: " + e.getMessage())
                    .build();
        }
    }

    /**
     * Helper para criar um cliente no Stripe
     */
    private Customer createCustomer(PaymentRequest request) throws StripeException {
        CustomerCreateParams params = CustomerCreateParams.builder()
                .setName(request.getName())
                .setEmail(request.getEmail())
                .setPhone(request.getPhoneNumber())
                .build();
        
        return Customer.create(params);
    }
} 