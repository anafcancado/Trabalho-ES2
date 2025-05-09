const API_BASE_URL = 'http://localhost:8081';

document.getElementById('login-form').addEventListener('submit', async function (e) {
    e.preventDefault();

    const userData = {
        username: document.getElementById('username').value,
        senha: document.getElementById('senha').value
    };

    localStorage.setItem('user', userData.username);
    
    const response = await fetch(`${API_BASE_URL}/users/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(userData)
    });

    const msg = await response.text();

    if (response.ok) {
        const checkoutRedirect = localStorage.getItem('checkoutRedirect');
            if (checkoutRedirect === 'true') {
                localStorage.removeItem('checkoutRedirect');
                window.location.href = '../checkout.html';
            } else {
                window.location.href = '../menu.html';
            }
        alert("Usuário logado com sucesso!.");
    } else {
        alert("Erro ao logar: Usuário ou senha incorretos.", true);
    }
});