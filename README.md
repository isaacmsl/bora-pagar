# Bora Pagar

![Bora-Pagar](https://github.com/isaacmsl/bora-pagar/assets/31693006/7fd84d8d-d03d-4e69-adb7-6723ae3b2dd0)

Na Universidade Federal do Rio Grande do Norte (UFRN), diante várias possibilidades de escolha de disciplinas, é importante que o estudante escolha aquelas que colegas conhecidos também pretendam escolher. Dessa forma, o aluno se sente mais seguro e com a certeza de que existirão pessoas que irão apoiá-lo durante o desenvolvimento da disciplina. Dito isso, esse sistema permite que os alunos visualize de maneira prática as disciplinas que seus amigos estão escolhendo e o auxilia a planejar as disciplinas no período de matrículas.

Para tal, nós utilizamos os [dados abertos disponibilizados pela UFRN](https://dados.ufrn.br/dataset/componentes-curriculares) como fonte de informação das disciplinas que são ofertadas. Esses dados são de certa forma "frequentemente" atualizados.

Estamos atualmente hospedados em [bora-pagar.onrender.com](https://bora-pagar.onrender.com). 

## Futuro 🚀

- [ ] Autenticação com o SIGAA
- [ ] Integração com a API do SIGAA

Muito mais! Veja o nosso progresso [neste quadro digital](https://github.com/users/isaacmsl/projects/6).

## Tecnologias utilizadas

- Git e Github/GitLab para versionamento do código
- Docker para conteinerização
- Java Spring Boot para o backend
- Vue.js 3 para o frontend: Typescript, vuetify, Pinia
- Persistência de dados na nuvem com Mongo DB

## Como rodar

1. Instale o Node.js (v20.6.1 é suficiente)
1. Instale o Java na sua versão 21
1. Crie um arquivo chamado `secrets.yml` no caminho `backend/src/main/resources/` (O conteúdo deve ser solicitado no [grupo do Telegram](https://t.me/+BRUhNMr3v8c0YzFh))
1. Entre no diretório do backend com o comando `cd backend`
1. Rode o backend com `./mvnw spring-boot:run`
1. Atualize as credenciais encontradas em `frontend/.env` (Pedir ajuda no [grupo do Telegram](https://t.me/+BRUhNMr3v8c0YzFh))
1. Entre no diretório do frontend com o comando `cd frontend`
1. Instale as dependência com o comando `npm install`
1. Rode o backend com `npm run dev`

## Como contribuir

Agredecemos muito qualquer feedback de bug ou melhoria. Fique a vontade para [encontrar uma issue](https://github.com/isaacmsl/bora-pagar/issues). Estamos disponíveis para contato através do nosso grupo do Telegram https://t.me/+BRUhNMr3v8c0YzFh.

## Agredecimentos

Aos primeiro contribuidores ❤️

| [<img src="https://github.com/isaacmsl.png" width=60><br><sub>@isaacmsl</sub>](https://github.com/isaacmsl) | [<img src="https://github.com/IanGabriel12.png" width=60><br><sub>@IanGabriel12</sub>](https://github.com/IanGabriel12) | [<img src="https://github.com/Kywal.png" width=60><br><sub>@Kyyyyywal</sub>](https://github.com/Kywal) |
| :---: | :---: | :---: |
| [<img src="https://github.com/RamonJales.png" width=60><br><sub>@RamonJales</sub>](https://github.com/RamonJales) | [<img src="https://github.com/DanilloFonseca.png" width=60><br><sub>@DanilloFonseca</sub>](https://github.com/DanilloFonseca) | |
