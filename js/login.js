const API_BASE_URL = 'http://localhost:8081';

document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.getElementById("login-form");

    if (loginForm) {
        loginForm.addEventListener("submit", async function (e) {
            e.preventDefault();

            const username = document.getElementById("username").value;
            const senha = document.getElementById("senha").value;

            const userData = { username, senha };

            // Salva usuário no localStorage com chave "user"
            localStorage.setItem('user', username);

            // Requisição para API principal (porta 8081)
            const response = await fetch(`${API_BASE_URL}/users/login`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(userData)
            });

            const msg = await response.text();

            if (response.ok) {
                // Salva também como "loggedUser"
                localStorage.setItem("loggedUser", username);

                alert("Login realizado com sucesso!");

                // Redirecionamento baseado no localStorage
                const checkoutRedirect = localStorage.getItem('checkoutRedirect');
                if (checkoutRedirect === 'true') {
                    localStorage.removeItem('checkoutRedirect');
                    window.location.href = '../checkout.html';
                } else {
                    window.location.href = '../menu.html'; // ou "index.html"
                }
            } else {
                alert("Erro ao logar: Usuário ou senha incorretos.");
            }
        });
    }
});
