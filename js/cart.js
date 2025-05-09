/**
 * cart.js - Gerencia a funcionalidade do carrinho de compras
 * Implementa funções para adicionar, remover e renderizar itens do carrinho
 */

/**
 * Adiciona um item ao carrinho
 * @param {string} name - Nome do produto
 * @param {number} price - Preço do produto
 */
export function addToCart(name, price) {
  const cart = JSON.parse(localStorage.getItem("cart")) || [];
  const existingItem = cart.find(item => item.name === name);

  if (existingItem) {
    existingItem.quantity += 1;
  } else {
    cart.push({ name, price, quantity: 1 });
  }

  localStorage.setItem("cart", JSON.stringify(cart));
  alert(`${name} foi adicionada ao carrinho.`);
}

/**
 * Remove um item do carrinho
 * @param {number} index - Índice do item a ser removido
 */
export function removeItem(index) {
  const cart = JSON.parse(localStorage.getItem('cart')) || [];
  cart.splice(index, 1);
  localStorage.setItem('cart', JSON.stringify(cart));
  renderCart();
}

/**
 * Renderiza os itens do carrinho na página
 */
function renderCart() {
  const cartItemsContainer = document.getElementById('cart-items');
  const cartTotalContainer = document.getElementById('cart-total');
  const checkoutButton = document.querySelector(".checkout-button");

  if (!cartItemsContainer) {
    return; // Sai da função se não estiver na página do carrinho
  }

  const cart = JSON.parse(localStorage.getItem("cart")) || [];

  // Verifica se o carrinho está vazio
  if (cart.length === 0) {
    cartItemsContainer.innerHTML = "<p class='empty-cart-msg'>🛑 Seu carrinho está vazio. Adicione ao menos uma pizza para finalizar o pedido.</p>";
    cartTotalContainer.textContent = "";
    if (checkoutButton) {
      checkoutButton.style.display = "none"; // Oculta o botão de finalizar
    }
    return;
  }

  // Se o carrinho não estiver vazio, renderiza os itens
  cartItemsContainer.innerHTML = '';
  let total = 0;

  cart.forEach((item, index) => {
    const itemDiv = document.createElement('div');
    itemDiv.classList.add('cart-item');
    itemDiv.innerHTML = `
      <div class="cart-item-info">
        <strong>${item.name}</strong> - R$ ${Number(item.price).toFixed(2)} x ${item.quantity}
      </div>
      <button class="remove-button" onclick="window.cartModule.removeItem(${index})">Remover</button>
    `;
    cartItemsContainer.appendChild(itemDiv);
    total += item.price * item.quantity;
  });

  cartTotalContainer.innerHTML = `<h3>Total: R$ ${total.toFixed(2)}</h3>`;

  if (checkoutButton) {
    checkoutButton.style.display = "block";
  }
}

// Inicialização
document.addEventListener("DOMContentLoaded", () => {
  // Detectar se estamos na página do carrinho
  if (window.location.pathname.includes("cart.html")) {
    renderCart();

    // Protege o botão de finalizar pedido
    const checkoutButton = document.querySelector(".checkout-button");
    if (checkoutButton) {
      checkoutButton.addEventListener("click", (e) => {
        const loggedUser = localStorage.getItem("loggedUser");
        if (!loggedUser) {
          e.preventDefault();
          alert("Você precisa estar logado para finalizar o pedido.");
          localStorage.setItem("checkoutRedirect", "true"); // <-- MARCA que veio do carrinho
          window.location.href = "login.html";
        }
      });
    }

  }
});

// Expor as funções ao escopo global
window.cartModule = { addToCart, removeItem };
