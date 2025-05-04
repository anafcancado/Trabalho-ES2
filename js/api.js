/**
 * api.js - Módulo responsável por todas as chamadas de API da aplicação
 * Seguindo os princípios de Single Responsibility e facilitando a manutenção
 */

// URL base da API (placeholder) - mudar quando tiver a API real
const API_BASE_URL = 'https://jsonplaceholder.typicode.com';

/**
 * Função para buscar o cardápio de pizzas da API
 * @returns {Promise} Promise que resolve para um array de objetos pizza
 */
export async function fetchPizzaMenu() {
  try {
    // Usando jsonplaceholder como API temporária
    // Na implementação real, apontaria para o endpoint correto de pizzas
    const response = await fetch(`${API_BASE_URL}/photos?_limit=8`);
    
    if (!response.ok) {
      throw new Error(`Erro ao buscar cardápio: ${response.status}`);
    }
    
    const data = await response.json();
    
    // Transformando os dados do placeholder para o formato de pizzas
    // Esta transformação não seria necessária com uma API real dedicada
    return data.map(item => ({
      id: item.id,
      name: `Pizza ${item.id}`,
      description: `Deliciosa pizza com ingredientes especiais ${item.title.slice(0, 20)}...`,
      price: (Math.random() * 30 + 20).toFixed(2), // Preço aleatório entre 20 e 50
      imageUrl: item.thumbnailUrl // Usando a thumbnail como imagem da pizza
    }));
  } catch (error) {
    console.error('Erro ao buscar o cardápio:', error);
    throw error;
  }
}

/**
 * Função para buscar detalhes de uma pizza específica
 * @param {number} id - ID da pizza
 * @returns {Promise} Promise que resolve para um objeto com detalhes da pizza
 */
export async function fetchPizzaDetails(id) {
  try {
    const response = await fetch(`${API_BASE_URL}/photos/${id}`);
    
    if (!response.ok) {
      throw new Error(`Erro ao buscar detalhes da pizza: ${response.status}`);
    }
    
    const item = await response.json();
    
    return {
      id: item.id,
      name: `Pizza ${item.id}`,
      description: `Deliciosa pizza com ingredientes especiais ${item.title}`,
      price: (Math.random() * 30 + 20).toFixed(2),
      imageUrl: item.url,
      ingredients: ['Massa', 'Molho de tomate', 'Queijo', 'Ingrediente especial']
    };
  } catch (error) {
    console.error(`Erro ao buscar detalhes da pizza ${id}:`, error);
    throw error;
  }
} 