/**
 * api.js - Módulo responsável por todas as chamadas de API da aplicação
 * Seguindo os princípios de Single Responsibility e facilitando a manutenção
 */

// URL base da API (placeholder) - mudar quando tiver a API real
const API_BASE_URL = 'https://localhost:8081/';

/**
 * Função para buscar o cardápio de pizzas da API (ta chamando um mock, api so registra peduido e usuario)
 * @returns {Promise} Promise que resolve para um array de objetos pizza
 */
export async function fetchPizzaMenu() {
  const pizzasMock = [
    {
      id: 1,
      name: "Pizza Margherita com Manjericão",
      description: "Fatias de tomate, mussarela, manjericão fresco",
      price: 32.90,
      imageUrl: "images/2.png"
    },
    {
      id: 2,
      name: "Pizza Pepperoni",
      description: "Molho de tomate, mussarela, pepperoni",
      price: 38.90,
      imageUrl: "images/1.png"
    },
    {
      id: 3,
      name: "Pizza Quatro Queijos",
      description: "Molho de tomate, mussarela, provolone, parmesão, gorgonzola",
      price: 42.90,
      imageUrl: "images/3.png"
    },
    {
      id: 4,
      name: "Pizza Calabresa",
      description: "Molho de tomate, mussarela, calabresa, cebola",
      price: 35.90,
      imageUrl: "images/calabresa.png"
    },
    {
      id: 5,
      name: "Pizza Bacon",
      description: "Bacon, queijo, cebola roxa e azeitona",
      price: 39.90,
      imageUrl: "images/veggie.jpeg"
    },
    {
      id: 6,
      name: "Pizza Frango com Catupiry",
      description: "Molho de tomate, mussarela, frango desfiado, catupiry",
      price: 41.90,
      imageUrl: "images/4.png"
    },
    {
      id: 7,
      name: "Pizza Portuguesa",
      description: "Molho de tomate, mussarela, presunto, ovo, cebola, azeitona",
      price: 37.90,
      imageUrl: "images/5.png"
    },
    {
      id: 8,
      name: "Pizza Atum",
      description: "Molho de tomate, mussarela, atum, cebola",
      price: 40.90,
      imageUrl: "images/6.png"
    }
  ];

  return pizzasMock;
}


export async function registerUser(userData) {
  //to do
}

export async function registerOrder(orderData) {
  //to do
}


export async function loginUser(userData) {
  //to do
}


