# Autoavaliação

Marcação do grupo, com base nos 10 critérios da revisão do código (`AVALIACAO.md`).

**Legenda:** ✅ atende · ◐ parcial · ❌ não atende

| Critério | Marca | Onde conferir |
|---|---|---|
| **P1** Sobe pelo README | ✅ | `docker compose up -d --build`, como no README. Testado clonando o repositório numa pasta vazia |
| **P2** Camadas | ✅ | Cada classe está no pacote da sua camada (`controller`, `service`, `mapper`, `repository`, `model`, `dto`) com os nomes da convenção. As exceções do domínio ficam em `service/` |
| **P3** Banco e entidades | ✅ | Migrations `V1` a `V5` criam as 5 tabelas, com as chaves estrangeiras `lote_id` e `id_comprador`. Cada tabela tem a sua `@Entity` e o Hibernate confere com `ddl-auto=validate` |
| **P4** DTOs | ✅ | As 5 entidades têm `RequestDTO` com validações e `ResponseDTO`. Nenhum Controller importa classe de `model` |
| **P5** Estrutura completa e documentada | ✅ | As 5 entidades têm Repository, Mapper, Service e Controller, ligados pelo construtor. Todas as classes têm o comentário no topo e o mapa do README bate com elas |
| **P6** API simples | ✅ | `POST`, `GET` e `GET /{id}` de `/lotes` (e das outras entidades). Campo inválido devolve `400` e id inexistente devolve `404`, com `LoteNaoEncontradoException` no Service |
| **P7** Regras de negócio implementadas | ✅ | RN01 em `ColheitaService`; RN02 e RN03 em `VendaService`. Violar uma regra devolve `400` com a mensagem da regra |
| **P8** Regras que atendem ao tema | ✅ | As regras tratam de colheita por hectare, preço combinado com o comprador e estoque da planta: problemas do manejo e da venda da produção |
| **P9** Código igual ao desenho | ✅ | Entidades, colunas e rotas do código são as de `docs/modelo-de-dominio.md` e `docs/contrato-da-api.md` |
| **P10** README | ✅ | O README tem todas as seções do template, sem sobra do template, e diz o que funciona |

## Como o grupo testou

1. Clonou o repositório numa pasta vazia e seguiu o README: banco e API subiram.
2. Cadastrou um lote, um comprador e uma colheita pelo Swagger UI (`201`).
3. Tentou uma colheita acima de 100 sacas por hectare (RN01, `400`), uma venda com preço fora da faixa (RN02, `400`) e uma venda acima do estoque (RN03, `400`).
4. Buscou ids que não existem em `/lotes/{id}` (`404`).
