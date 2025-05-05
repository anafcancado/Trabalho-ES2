package com.laura.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private Long amount; // Montante em centavos
    private String currency; // Moeda (ex: "brl" para Real brasileiro)
    private String description; // Descrição do pagamento
    private String paymentMethod; // Método de pagamento (ex: card, pix)
    private String email; // Email do cliente
    private String name; // Nome do cliente
    private String address; // Endereço para entrega
    private String phoneNumber; // Número de telefone
} 