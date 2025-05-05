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
      name: "Pizza Margherita",
      description: "Molho de tomate, mussarela, manjericão fresco",
      price: 32.90,
      imageUrl: "https://source.unsplash.com/random/300x200/?pizza,margherita"
    },
    {
      id: 2,
      name: "Pizza Pepperoni",
      description: "Molho de tomate, mussarela, pepperoni",
      price: 38.90,
      imageUrl: "https://source.unsplash.com/random/300x200/?pizza,pepperoni"
    },
    {
      id: 3,
      name: "Pizza Quatro Queijos",
      description: "Molho de tomate, mussarela, provolone, parmesão, gorgonzola",
      price: 42.90,
      imageUrl: "https://source.unsplash.com/random/300x200/?pizza,cheese"
    },
    {
      id: 4,
      name: "Pizza Calabresa",
      description: "Molho de tomate, mussarela, calabresa, cebola",
      price: 35.90,
      imageUrl: "https://source.unsplash.com/random/300x200/?pizza,sausage"
    },
    {
      id: 5,
      name: "Pizza Vegetariana",
      description: "Molho de tomate, mussarela, pimentão, cebola, azeitona, champignon",
      price: 39.90,
      imageUrl: "https://source.unsplash.com/random/300x200/?pizza,vegetable"
    },
    {
      id: 6,
      name: "Pizza Frango com Catupiry",
      description: "Molho de tomate, mussarela, frango desfiado, catupiry",
      price: 41.90,
      imageUrl: "https://source.unsplash.com/random/300x200/?pizza,chicken"
    },
    {
      id: 7,
      name: "Pizza Portuguesa",
      description: "Molho de tomate, mussarela, presunto, ovo, cebola, azeitona",
      price: 37.90,
      imageUrl: "https://source.unsplash.com/random/300x200/?pizza,ham"
    },
    {
      id: 8,
      name: "Pizza Atum",
      description: "Molho de tomate, mussarela, atum, cebola",
      price: 40.90,
      imageUrl: "https://source.unsplash.com/random/300x200/?pizza,tuna"
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


