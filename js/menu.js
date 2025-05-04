/**
 * menu.js - Responsável pela lógica da página de cardápio
 * Implementa funções de renderização dinâmica e interação com o carrinho
 */

// Importando funções da API
import { fetchPizzaMenu } from './api.js';

/**
 * Adiciona um item ao carrinho
 * @param {string} name - Nome da pizza
 * @param {number} price - Preço da pizza
 */
function addToCart(name, price) {
  let cart = JSON.parse(localStorage.getItem('cart')) || [];

  const existingItem = cart.find(item => item.name === name);
  if (existingItem) {
    existingItem.quantity += 1;
  } else {
    cart.push({ name, price, quantity: 1 });
  }

  localStorage.setItem('cart', JSON.stringify(cart));
  alert(`${name} adicionada ao carrinho!`);
}

/**
 * Cria um card de pizza para o cardápio
 * @param {Object} pizza - Objeto contendo dados da pizza
 * @returns {HTMLElement} Elemento do card de pizza
 */
function createPizzaCard(pizza) {
  const card = document.createElement('div');
  card.classList.add('pizza-card');
  
  card.innerHTML = `
    <img src="${pizza.imageUrl}" alt="${pizza.name}">
    <h3>${pizza.name}</h3>
    <p>${pizza.description}</p>
    <span>R$ ${Number(pizza.price).toFixed(2)}</span>
    <button onclick="addToCart('${pizza.name}', ${pizza.price})">Adicionar</button>
  `;
  
  return card;
}

/**
 * Renderiza o cardápio de pizzas de forma dinâmica
 * Busca dados da API e cria os elementos DOM
 */
async function renderPizzaMenu() {
  const pizzaGrid = document.querySelector('.pizza-grid');
  
  try {
    // Exibir indicador de carregamento
    pizzaGrid.innerHTML = '<div class="loading">Carregando cardápio...</div>';
    
    // Buscar dados da API
    const pizzas = await fetchPizzaMenu();
    
    // Limpar grid antes de popular
    pizzaGrid.innerHTML = '';
    
    // Para cada pizza, criar e adicionar um card ao grid
    pizzas.forEach(pizza => {
      const card = createPizzaCard(pizza);
      pizzaGrid.appendChild(card);
    });
  } catch (error) {
    pizzaGrid.innerHTML = `
      <div class="error">
        <p>Erro ao carregar o cardápio. Por favor, tente novamente.</p>
        <button onclick="renderPizzaMenu()">Tentar Novamente</button>
      </div>
    `;
    console.error('Falha ao renderizar cardápio:', error);
  }
}

// Expondo a função addToCart ao escopo global para uso nos botões
window.addToCart = addToCart;

// Inicializar o cardápio quando a página carregar
document.addEventListener('DOMContentLoaded', renderPizzaMenu);

  
  // Se quiser popular dinamicamente as pizzas, pode usar o código abaixo
  /*
  document.addEventListener('DOMContentLoaded', () => {
    const pizzas = [
      { name: "Calabresa", desc: "Molho, mussarela, calabresa e cebola", price: 29.99 },
      { name: "Portuguesa", desc: "Molho, presunto, ovo, ervilha e cebola", price: 32.50 },
      { name: "Frango com Catupiry", desc: "Molho, frango desfiado, catupiry", price: 34.00 }
    ];
  
    const grid = document.querySelector('.pizza-grid');
    pizzas.forEach(pizza => {
      const card = document.createElement('div');
      card.classList.add('pizza-card');
      card.innerHTML = `
        <img src="/images/${pizza.name.toLowerCase().replace(/\s/g, '')}.jpg" alt="${pizza.name}">
        <h3>${pizza.name}</h3>
        <p>${pizza.desc}</p>
        <span>R$ ${pizza.price.toFixed(2)}</span>
        <button onclick="addToCart('${pizza.name}', ${pizza.price})">Adicionar ao Carrinho</button>
      `;
      grid.appendChild(card);
    });
  });
  */
  