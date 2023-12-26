# Bora pagar frontend

Front-end do sistema "Bora Pagar".

# Rodando o projeto no modo de desenvolvimento
- Instale o Node.js na versão 20 LTS.
- Na pasta do projeto frontend, configure o yarn e instale as dependências usando os comandos

```bash
corepack enable
yarn set version stable
yarn install
```
- Inicie o servidor com `yarn dev`

# Rodando em um container Docker
Construindo a imagem docker:
```bash
docker build . -t bora-pagar-front
```

Rodando o container:
```bash
docker run -d -p 8080:80 bora-pagar-front
```

A aplicação estará rodando em `http://localhost:8080`