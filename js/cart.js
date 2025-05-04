// cart.js

document.addEventListener("DOMContentLoaded", () => {
  const cartItemsContainer = document.getElementById("cart-items");
  const cartTotalContainer = document.getElementById("cart-total");
  const checkoutButton = document.querySelector(".checkout-button");

  const cart = JSON.parse(localStorage.getItem("cart")) || [];

  if (cart.length === 0) {
    cartItemsContainer.innerHTML = "<p class='empty-cart-msg'>🛑 Seu carrinho está vazio. Adicione ao menos uma pizza para finalizar o pedido.</p>";
    cartTotalContainer.textContent = "";
    checkoutButton.style.display = "none"; // Oculta o botão de finalizar
    return;
  }

  // Código normal para exibir os itens, se o carrinho não estiver vazio
  // ...
});

function addToCart(name, price) {
  const cart = JSON.parse(localStorage.getItem('cart')) || [];
  const existingItem = cart.find(item => item.name === name);

  if (existingItem) {
    existingItem.quantity += 1;
  } else {
    cart.push({ name, price, quantity: 1 });
  }

  localStorage.setItem('cart', JSON.stringify(cart));
  alert(`${name} foi adicionada ao carrinho.`);
}

// Para a página cart.html
function renderCart() {
  const cartItemsContainer = document.getElementById('cart-items');
  const cartTotalContainer = document.getElementById('cart-total');

  const cart = JSON.parse(localStorage.getItem('cart')) || [];

  cartItemsContainer.innerHTML = '';
  cartTotalContainer.innerHTML = '';

  if (cart.length === 0) {
    cartItemsContainer.innerHTML = '<p>Seu carrinho está vazio.</p>';
    return;
  }

  let total = 0;

  cart.forEach((item, index) => {
    const itemDiv = document.createElement('div');
    itemDiv.innerHTML = `
      <strong>${item.name}</strong> - R$ ${item.price.toFixed(2)} x ${item.quantity}
      <button onclick="removeItem(${index})">Remover</button>
    `;
    cartItemsContainer.appendChild(itemDiv);
    total += item.price * item.quantity;
  });

  cartTotalContainer.innerHTML = `<h3>Total: R$ ${total.toFixed(2)}</h3>`;
}

function removeItem(index) {
  const cart = JSON.parse(localStorage.getItem('cart')) || [];
  cart.splice(index, 1);
  localStorage.setItem('cart', JSON.stringify(cart));
  renderCart();
}

// Detectar se estamos na página do carrinho
if (window.location.pathname.includes("cart.html")) {
  renderCart();
}

