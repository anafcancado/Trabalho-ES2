## 🍕 Projeto Pizzaria - ES2

Este projeto é composto por um **backend** em Java com Spring Boot e um **frontend** em HTML, CSS e JavaScript.

## Integrantes
- Ana Fernanda Cancado
- Gabriel Praes
- Laura Caetano 
- Júlia Roque

## 🚀 Como rodar o projeto localmente

### ✅ Pré-requisitos

- Java 17 ou superior  
- Maven (ou Maven Wrapper já incluído no projeto)  
- Node.js (opcional, para rodar servidor local do frontend)  
- Visual Studio Code (recomendado)  
- Extensão **Live Server** (opcional)

---

### 🔧 Backend (API - Java + Spring Boot)

#### Caminho:
```
/backend/src/main/java/com/laura/ChatApiApplication.java
```

#### Opção 1: Rodando com Maven Wrapper

1. Navegue até a pasta do backend:
   ```bash
   cd backend
   ```

2. Execute a aplicação:
   ```bash
   ./mvnw spring-boot:run        # Linux/Mac
   .\mvnw spring-boot:run        # Windows (PowerShell)
   ```

#### Opção 2: Rodando diretamente no VS Code

1. Navegue até o arquivo:
   ```
   backend/src/main/java/com/laura/ChatApiApplication.java
   ```

2. Clique no botão ▶️ ao lado do método `main`.

> A API será iniciada em:  
👉 `http://localhost:8080`

---

### 🌐 Frontend (HTML/CSS/JS)

#### Caminho:
```
/ (raiz do projeto)
```
Arquivos principais:  
`index.html`, `menu.html`, `cart.html`, `checkout.html`, `login.html`, etc.

#### Opção 1: Usando Live Server (VS Code)

1. Instale a extensão "Live Server".
2. Clique com o botão direito em `index.html`.
3. Selecione **"Open with Live Server"**.

> O site abrirá automaticamente em:  
👉 `http://127.0.0.1:5500/index.html`

#### Opção 2: Usando terminal com Node.js

1. No terminal, vá até a raiz do projeto.

2. Rode um servidor com:
   ```bash
   npx http-server .
   ```

> Acesse em:  
👉 `http://localhost:8080`

---

### 🛠️ Ferramentas e Tecnologias Usadas

- **Linguagem Backend:** Java 17  
- **Framework Backend:** Spring Boot  
- **Gerenciador de Dependências:** Maven  
- **Frontend:** HTML5, CSS3, JavaScript  
- **API REST:** Postman (testes de endpoints)  
- **IDE recomendada:** Visual Studio Code  
- **Servidor Local:** Live Server ou http-server (Node.js)
- **Banco de Dados:** SQL Server com um servidor na Azure


## Funcionalidades

- Visualização do cardápio de pizzas
- Cadastro e login de usuários
- Adição de itens ao carrinho
- Finalização de pedidos
- Acompanhamento de status do pedido


## Estrutura do Projeto

- `backend/`: API REST em Java
- `css/`: Estilos do site
- `js/`: Scripts JavaScript
- `images/`: Imagens utilizadas no site
- Arquivos HTML: Páginas do site
