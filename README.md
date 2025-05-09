# Pizza-ES2 🍕

## Integrantes
Ana Fernanda Cancado
Gabriel Praes
Laura Caetano 
Júlia Roque

## Como rodar o projeto localmente

### Pré-requisitos
- Java 17+
- Maven
- Node.js
- VS Code (recomendado)

### Backend (API)

1. Navegue até a pasta do backend:
```
cd backend
```

2. Compile e inicie o servidor Spring Boot:
```
mvn spring-boot:run
```

3. A API estará disponível em `http://localhost:8080`

### Frontend (Web)

1. Para executar o frontend, abra os arquivos HTML diretamente no navegador ou use uma extensão do VS Code como o Live Server:
   - Instale a extensão "Live Server" no VS Code
   - Clique com o botão direito no arquivo `index.html`
   - Selecione "Open with Live Server"

2. O site estará disponível em `http://127.0.0.1:5500/index.html` (ou porta similar)

## Funcionalidades

- Visualização do cardápio de pizzas
- Cadastro e login de usuários
- Adição de itens ao carrinho
- Finalização de pedidos
- Acompanhamento de status do pedido

## Tecnologias Utilizadas

- Backend: Java com Spring Boot
- Frontend: HTML, CSS e JavaScript
- Banco de Dados: SQL Server com um servidor na Azure

## Estrutura do Projeto

- `backend/`: API REST em Java
- `css/`: Estilos do site
- `js/`: Scripts JavaScript
- `images/`: Imagens utilizadas no site
- Arquivos HTML: Páginas do site