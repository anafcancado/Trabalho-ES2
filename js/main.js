/**
 * main.js - Arquivo principal para a página inicial
 */

import { addToCart } from './cart.js';

// Inicializa a página principal
document.addEventListener('DOMContentLoaded', () => {
  // Configura listeners para botões de adicionar ao carrinho
  setupAddToCartButtons();
});

/**
 * Configura os listeners para todos os botões de adicionar ao carrinho
 */
function setupAddToCartButtons() {
  const addButtons = document.querySelectorAll('.add-to-cart-btn');
  
  addButtons.forEach(button => {
    button.addEventListener('click', (event) => {
      const name = event.target.dataset.name;
      const price = parseFloat(event.target.dataset.price);
      
      if (name && price) {
        addToCart(name, price);
      }
    });
  });
}
  