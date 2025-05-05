package com.laura.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private String paymentId;
    private String paymentStatus;
    private String paymentUrl; // URL de checkout ou código PIX (caso aplicável)
    private String clientSecret; // Para integração com Stripe no frontend (opcional)
    private String message;
    private Boolean success;
} 