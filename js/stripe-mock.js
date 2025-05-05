/**
 * stripe-mock.js - Módulo para simular integração com Stripe
 * Fornece funções mock para processar pagamentos sem backend real
 */

/**
 * Cria um token de pagamento simulado
 * @param {Object} cardDetails - Detalhes do cartão
 * @returns {Promise} Promise que resolve para um objeto token
 */
export async function createPaymentToken(cardDetails) {
  // Simular processamento assíncrono
  return new Promise((resolve) => {
    setTimeout(() => {
      // Gerar um ID de token aleatório
      const tokenId = 'tok_' + Math.random().toString(36).substring(2, 15);
      
      resolve({
        id: tokenId,
        object: 'token',
        created: Date.now() / 1000, // timestamp em segundos
        livemode: false,
        type: 'card',
        card: {
          brand: 'visa',
          last4: '4242',
          exp_month: cardDetails.exp_month || 12,
          exp_year: cardDetails.exp_year || 2025
        }
      });
    }, 1000); // Simular atraso de rede de 1 segundo
  });
}

/**
 * Processa um pagamento simulado
 * @param {Object} paymentDetails - Detalhes do pagamento
 * @returns {Promise} Promise que resolve para um objeto de resposta de pagamento
 */
export async function processPayment(paymentDetails) {
  // Simular processamento assíncrono
  return new Promise((resolve) => {
    setTimeout(() => {
      // Simular resposta de pagamento bem-sucedido
      resolve({
        success: true,
        paymentId: 'pi_' + Math.random().toString(36).substring(2, 15),
        paymentStatus: 'succeeded',
        created: Date.now(),
        amount: paymentDetails.amount,
        currency: paymentDetails.currency || 'brl',
        receipt_email: paymentDetails.email || null,
        metadata: paymentDetails.metadata || {}
      });
    }, 2000); // Simular atraso de rede de 2 segundos
  });
}

/**
 * Verifica o status de um pagamento
 * @param {string} paymentId - ID do pagamento
 * @returns {Promise} Promise que resolve para o status do pagamento
 */
export async function checkPaymentStatus(paymentId) {
  // Simular processamento assíncrono
  return new Promise((resolve) => {
    setTimeout(() => {
      // Sempre retornar sucesso para simulação
      resolve({
        id: paymentId,
        status: 'succeeded',
        updated: Date.now()
      });
    }, 500);
  });
}

/**
 * Cria um cliente simulado no Stripe
 * @param {Object} customerDetails - Detalhes do cliente
 * @returns {Promise} Promise que resolve para um objeto cliente
 */
export async function createCustomer(customerDetails) {
  // Simular processamento assíncrono
  return new Promise((resolve) => {
    setTimeout(() => {
      // Gerar um ID de cliente aleatório
      const customerId = 'cus_' + Math.random().toString(36).substring(2, 15);
      
      resolve({
        id: customerId,
        object: 'customer',
        created: Date.now() / 1000,
        email: customerDetails.email,
        name: customerDetails.name,
        phone: customerDetails.phone,
        metadata: customerDetails.metadata || {}
      });
    }, 1000);
  });
} 