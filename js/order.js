(async function () {
    const API_BASE_URL = 'http://localhost:8081';

    const username = localStorage.getItem("user"); 
    const endereco = localStorage.getItem("enderecoEntrega");
    const cart = JSON.parse(localStorage.getItem("cart")) || [];

    // Preparar lista de nomes dos itens
    const itens = cart.map(item => item.name);

    // Requisição para buscar o usuário logado
    const usuarioResponse = await fetch(`${API_BASE_URL}/users/${username}`);
    const usuario = await usuarioResponse.json();
    
    // Criar objeto de pedido para envio
    const pedido = {
        cliente: { id: usuario.id },
        itens: itens,
        metodoPagamento: "Cartão",
        endereco: endereco
    };

    // Enviar o pedido para o backend
    await fetch(API_BASE_URL + "/pedidos", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(pedido)
    });

    console.log("Pedido enviado com sucesso! "+ pedido);
})();