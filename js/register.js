console.log("entrou");
const API_BASE_URL = 'http://localhost:8081';

document.getElementById('register-form').addEventListener('submit', async function (e) {
    e.preventDefault();

    const userData = {
        username: document.getElementById('username').value,
        senha: document.getElementById('senha').value
    };
    
    const response = await fetch(`${API_BASE_URL}/users/create`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(userData)
    });
    const msg = await response.text();

    if (response.ok) {
        alert("Usuário cadastrado com sucesso! Faça login.");
        window.location.href = '../login.html';
    } else {
        alert("Erro ao cadastrar: " + msg, true);
    }
});

