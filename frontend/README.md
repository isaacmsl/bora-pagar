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

# Configuração do vscode

1. Baixe todas as extenções recomendadas
    - Na aba de extensões, digite `@recommended`. Irão aparecer as extensões:
      - Vue language features (Volar)
      - Typescript Vue Plugin
      - ESLint
      - Prettier
      - ZipFS
2. Desabilite a extensão padrão do Typescript.
    - Procure por `@builtin typescript` e desabilite a extensão Typescript and Javascript language features.
3. Instale o sdk do yarn para o vscode após ter instalado as dependências.
    - Execute no terminal `yarn dlx @yarnpkg/sdks vscode`
4. Selecione a versão do typescript
    1. Abra qualquer arquivo .ts
    2. Aperte <kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>P</kbd>, procure por `Volar: Select Typescript Version`
    3. Selecione a opção Use Workspace Version que aponta para a pasta `.yarn/sdks`
