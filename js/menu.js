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
  