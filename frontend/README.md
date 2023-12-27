# Bora pagar frontend

Front-end do sistema "Bora Pagar".

# Rodando o projeto no modo de desenvolvimento

Construa a imagem docker do sistema utilizando o comando:

```bash
docker compose up --build --no-recreate
```

Este comando só precisa ser executado ao criar a imagem pela primeira vez ou ao fazer mudanças no `docker-compose.yml`.

Para subir o container nas próximas vezes, só usar o comando:

```bash
docker compose up
```

O server do front estará rodando em `http://localhost:8000`