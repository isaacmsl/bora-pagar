# Bora pagar frontend

Front-end do sistema "Bora Pagar".

# Rodando o projeto no modo de desenvolvimento

Construa a imagem docker do sistema utilizando o comando:

```bash
docker compose up --build --no-recreate -d
```

Este comando só precisa ser executado ao criar a imagem pela primeira vez ou ao fazer mudanças no `docker-compose.yml`.

Para subir o container nas próximas vezes, só usar o comando:

```bash
docker compose up -d
```

O nome do container é `bora_pagar_front`. Acesse o terminal do container com o comando:

```bash
docker exec -it bora_pagar_front sh
```

Dentro do container, instale as dependências com o comando `yarn install` e rode o servidor de desenvolvimento com o comando `yarn dev`.
A aplicação estará rodando em `http://localhost:8000`.