# AgroManejo

API de backend para o produtor rural controlar os lotes de plantio, registrar o que foi colhido e vender a produção para compradores, sem vender mais do que colheu e sem fugir do preço combinado.

Projeto Final da disciplina de Backend (Engenharia de Software, SATC). **Etapa 1: arquitetura do backend.**

## Índice

- [Integrantes](#integrantes)
- [O problema](#o-problema)
- [Tecnologias](#tecnologias)
- [Arquitetura](#arquitetura)
- [Entidades](#entidades)
- [Rotas](#rotas)
- [Como executar](#como-executar)
- [Situação do projeto](#situação-do-projeto)
- [Documentação](#documentação)

## Integrantes

| Nome | GitHub |
|---|---|
| Davi Goulart | [@davigoularts](https://github.com/davigoularts) |
| Laurent Vieira | indisponível |
| Arthur Ramos | [@ArthurRamos567](https://github.com/ArthurRamos567) |

## O problema

**Tema sorteado:** Agronegócio

Muitos produtores rurais controlam os lotes, as colheitas e as vendas em caderno ou planilha solta. Nesse controle é comum anotar uma colheita impossível para o tamanho da área, fechar uma venda por um preço muito diferente do combinado com o comprador ou vender mais sacas do que realmente foram colhidas. O AgroManejo guarda os lotes, as colheitas, os insumos e os compradores num só lugar e barra esses erros na hora de registrar.

**Quem usa o sistema:**

| Quem | O que faz no sistema |
|---|---|
| Produtor rural | Cadastra lotes e insumos, registra as colheitas de cada lote e as vendas da produção |
| Gestor da propriedade | Cadastra os compradores, com o preço combinado, e acompanha colheitas e vendas |

**Regras de negócio:**

| # | Regra | Onde está no código | Rota que usa |
|---|---|---|---|
| RN01 | A colheita só pode ser registrada em um lote que existe, e a quantidade colhida não pode passar de 100 sacas por hectare do lote | `ColheitaService` | `POST /colheitas` |
| RN02 | O preço da venda não pode divergir mais de 50% do preço combinado com o comprador, e a planta vendida precisa ser a que ele negociou | `VendaService` | `POST /vendas` |
| RN03 | Não se pode vender mais do que o estoque da planta: o total colhido nos lotes dessa planta menos o total já vendido | `VendaService` | `POST /vendas` |

**Fora do escopo:** `PUT` e `DELETE`, autenticação e login, consumo de insumos por lote, emissão de nota fiscal e controle de pagamento das vendas.

## Tecnologias

| O quê | Qual |
|---|---|
| Linguagem | Java 25 |
| Framework | Spring Boot 3.5 (Web, Validation, Data JPA) |
| Banco de dados | PostgreSQL 16, em Docker |
| Migrations | Flyway |
| Documentação da API | springdoc-openapi (Swagger UI) |

## Arquitetura

A API é organizada em camadas. Uma requisição entra pelo Controller, passa pelo Service, que aplica as regras de negócio, e chega ao banco pelo Repository; a resposta faz o caminho de volta. O Mapper converte DTO em entidade e entidade em DTO.

**Mapa da estrutura.** Uma linha por entidade. Em cada célula, a classe e a situação.

| Entidade | Migration | Model | Repository | DTOs | Mapper | Service | Controller |
|---|---|---|---|---|---|---|---|
| Lote | `V1__criar_lotes.sql`, pronta | `Lote`, pronta | `LoteRepository`, pronto | `LoteRequestDTO`, `LoteResponseDTO`, prontos | `LoteMapper`, implementado | `LoteService`, implementado | `LoteController`, implementado |
| Insumo | `V2__criar_insumos.sql`, pronta | `Insumo`, pronta | `InsumoRepository`, pronto | `InsumoRequestDTO`, `InsumoResponseDTO`, prontos | `InsumoMapper`, implementado | `InsumoService`, implementado | `InsumoController`, implementado |
| Comprador | `V3__criar_compradores.sql`, pronta | `Comprador`, pronta | `CompradorRepository`, pronto | `CompradorRequestDTO`, `CompradorResponseDTO`, prontos | `CompradorMapper`, implementado | `CompradorService`, implementado | `CompradorController`, implementado |
| Colheita | `V4__criar_colheitas.sql`, pronta | `Colheita`, pronta | `ColheitaRepository`, pronto | `ColheitaRequestDTO`, `ColheitaResponseDTO`, prontos | `ColheitaMapper`, implementado | `ColheitaService`, implementado (RN01) | `ColheitaController`, implementado |
| Venda | `V5__criar_vendas.sql`, pronta | `Venda`, pronta | `VendaRepository`, pronto | `VendaRequestDTO`, `VendaResponseDTO`, prontos | `VendaMapper`, implementado | `VendaService`, implementado (RN02 e RN03) | `VendaController`, implementado |

Também fazem parte do projeto, vindas do template: `RecursoNaoEncontradoException`, `RegraDeNegocioException` e `OpenApiConfig`. Cada entidade tem a sua exceção de "não encontrado", em `service/`: `LoteNaoEncontradoException`, `InsumoNaoEncontradoException`, `CompradorNaoEncontradoException`, `ColheitaNaoEncontradaException` e `VendaNaoEncontradaException`.

## Entidades

| Entidade | O que representa | Como se relaciona |
|---|---|---|
| Lote | Um talhão: área onde uma planta é cultivada | Recebe várias colheitas |
| Insumo | Adubo, semente ou outro material, com a quantidade em estoque | Não se liga a outra entidade |
| Comprador | Quem compra a produção, com o preço combinado para uma planta | Faz várias vendas |
| Colheita | Quantidade de sacas colhida de um lote | Pertence a um lote |
| Venda | Venda de uma planta para um comprador, com quantidade, preço e data | Pertence a um comprador |

Atributos, tipos e o diagrama das tabelas estão em [docs/modelo-de-dominio.md](docs/modelo-de-dominio.md).

## Rotas

### As que já funcionam

| Verbo | Caminho | O que faz | Regra | Sucesso | Erros |
|---|---|---|---|---|---|
| `POST` | `/lotes` | Cadastra um lote | Nenhuma | `201` | `400` |
| `GET` | `/lotes` | Lista os lotes | Nenhuma | `200` | Nenhum |
| `GET` | `/lotes/{id}` | Busca um lote | Nenhuma | `200` | `404` |
| `POST` | `/insumos` | Cadastra um insumo | Nome único | `201` | `400` |
| `GET` | `/insumos` | Lista os insumos | Nenhuma | `200` | Nenhum |
| `GET` | `/insumos/{id}` | Busca um insumo | Nenhuma | `200` | `404` |
| `POST` | `/compradores` | Cadastra um comprador | CNPJ único | `201` | `400` |
| `GET` | `/compradores` | Lista os compradores | Nenhuma | `200` | Nenhum |
| `GET` | `/compradores/{id}` | Busca um comprador | Nenhuma | `200` | `404` |
| `POST` | `/colheitas` | Registra uma colheita | RN01 | `201` | `400`, `404` |
| `GET` | `/colheitas` | Lista as colheitas | Nenhuma | `200` | Nenhum |
| `GET` | `/colheitas/{id}` | Busca uma colheita | Nenhuma | `200` | `404` |
| `POST` | `/vendas` | Registra uma venda | RN02 e RN03 | `201` | `400`, `404` |
| `GET` | `/vendas` | Lista as vendas | Nenhuma | `200` | Nenhum |
| `GET` | `/vendas/{id}` | Busca uma venda | Nenhuma | `200` | `404` |

Exemplo de requisição:

```http
POST /colheitas
Content-Type: application/json

{
  "loteId": 1,
  "quantidade": 800
}
```

Resposta, `201 Created`:

```json
{
  "id": 1,
  "loteId": 1,
  "quantidade": 800
}
```

### Erros

Todo erro sai no mesmo formato. Este é o `400` de uma regra de negócio violada (venda maior que o estoque):

```json
{
  "status": 400,
  "erro": "Bad Request",
  "mensagem": "RN03: estoque insuficiente de Soja (disponível: 300.00, pedido: 400)",
  "caminho": "/vendas",
  "timestamp": "2026-10-14T19:30:00",
  "campos": []
}
```

Um campo inválido também devolve `400`, com a lista `campos` dizendo qual campo corrigir. Um id que não existe devolve `404`.

### As planejadas

Não há rotas planejadas que ainda não respondam. Todas as rotas do contrato funcionam.

| Recurso | O que vai fazer |
|---|---|
| Nenhum | `PUT` e `DELETE` ficaram fora do escopo desta etapa |

O contrato completo, com todas as rotas e os DTOs, está em [docs/contrato-da-api.md](docs/contrato-da-api.md).

## Como executar

Pré-requisitos: Docker aberto (Docker Desktop no Windows e no Mac) e Git. Não precisa de Java instalado: a API é compilada e roda dentro de um container.

```bash
git clone https://github.com/davigoularts/AGRO-MANEJO.git
cd AGRO-MANEJO

docker compose up -d --build   # sobe o PostgreSQL (porta 5437) e a API (porta 8080)
```

Na primeira vez o build baixa as dependências e leva alguns minutos. A API está no ar quando `docker compose logs api` mostrar `Started AgroManejoApplication`.

**Rodando a API pelo Maven (com JDK 25 instalado):** suba só o banco, para a porta 8080 ficar livre, e rode a API:

```bash
docker compose up -d postgres
./mvnw spring-boot:run        # Windows: .\mvnw.cmd spring-boot:run
```

Com a API no ar:

| Endereço | O que é |
|---|---|
| <http://localhost:8080/swagger-ui.html> | Swagger UI: as rotas, com **Try it out** para enviar requisições |
| <http://localhost:8080/v3/api-docs> | O contrato OpenAPI em JSON |

Para testar as rotas, use o Swagger UI ou o **Postman**. No Postman, importe todas as rotas de uma vez: **Import** → **Link** → `http://localhost:8080/v3/api-docs`.

Para ver as regras barrando um pedido, siga esta ordem: cadastre um lote (`POST /lotes`), uma colheita nele (`POST /colheitas`), um comprador (`POST /compradores`) e depois uma venda (`POST /vendas`). Depois tente uma colheita acima de 100 sacas por hectare (RN01), uma venda com preço muito diferente do combinado (RN02) e uma venda maior que o estoque (RN03).

Depois de mudar o código, rode `docker compose up -d --build` de novo para recompilar a API. Para parar: `docker compose down` (os dados do banco ficam guardados). Para apagar o banco e rodar as migrations do zero: `docker compose down -v` e suba de novo.

## Situação do projeto

| Parte | Situação |
|---|---|
| Desenho | Completo: problema e regras neste README, modelo e contrato em `docs/` |
| Estrutura | As 5 entidades têm migration, entidade, Repository, DTOs, Mapper, Service e Controller |
| API e regras | As rotas das 5 entidades funcionam; RN01, RN02 e RN03 estão implementadas |

**Ainda não funciona:** `PUT` e `DELETE`, que ficaram fora do escopo desta etapa.

## Documentação

| Documento | O que tem |
|---|---|
| [Modelo de domínio](docs/modelo-de-dominio.md) | Entidades, relacionamentos, diagrama das tabelas, migrations |
| [Contrato da API](docs/contrato-da-api.md) | Todas as rotas, os DTOs, exemplos de JSON e erros |
| [Autoavaliação](docs/autoavaliacao.md) | Os critérios da revisão do código, marcados pelo grupo |
