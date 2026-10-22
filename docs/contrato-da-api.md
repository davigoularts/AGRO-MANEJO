# Contrato da API

Todas as rotas respondem e recebem JSON. A documentação interativa está em <http://localhost:8080/swagger-ui.html>.

## Resumo das rotas

| Recurso | Verbo e caminho | O que faz | Regra | Sucesso | Erros |
|---|---|---|---|---|---|
| Lote | `POST /lotes` | Cadastra um lote | Nenhuma | `201` | `400` |
| Lote | `GET /lotes` | Lista os lotes | Nenhuma | `200` | Nenhum |
| Lote | `GET /lotes/{id}` | Busca um lote | Nenhuma | `200` | `404` |
| Insumo | `POST /insumos` | Cadastra um insumo | Nome único | `201` | `400` |
| Insumo | `GET /insumos` | Lista os insumos | Nenhuma | `200` | Nenhum |
| Insumo | `GET /insumos/{id}` | Busca um insumo | Nenhuma | `200` | `404` |
| Comprador | `POST /compradores` | Cadastra um comprador | CNPJ único | `201` | `400` |
| Comprador | `GET /compradores` | Lista os compradores | Nenhuma | `200` | Nenhum |
| Comprador | `GET /compradores/{id}` | Busca um comprador | Nenhuma | `200` | `404` |
| Colheita | `POST /colheitas` | Registra uma colheita | RN01 | `201` | `400`, `404` |
| Colheita | `GET /colheitas` | Lista as colheitas | Nenhuma | `200` | Nenhum |
| Colheita | `GET /colheitas/{id}` | Busca uma colheita | Nenhuma | `200` | `404` |
| Venda | `POST /vendas` | Registra uma venda | RN02 e RN03 | `201` | `400`, `404` |
| Venda | `GET /vendas` | Lista as vendas | Nenhuma | `200` | Nenhum |
| Venda | `GET /vendas/{id}` | Busca uma venda | Nenhuma | `200` | `404` |

Listagem sem resultado devolve `200` com lista vazia. `PUT` e `DELETE` ficaram fora do escopo desta etapa.

## Regras de negócio

| # | Regra | Rota | Classe |
|---|---|---|---|
| RN01 | A colheita só pode ser de um lote que existe, e a quantidade não pode passar de 100 sacas por hectare do lote | `POST /colheitas` | `ColheitaService` |
| RN02 | O preço da venda não pode divergir mais de 50% do preço combinado com o comprador, e a planta vendida precisa ser a que ele negociou | `POST /vendas` | `VendaService` |
| RN03 | Não se pode vender mais do que o estoque da planta (total colhido nos lotes menos total já vendido) | `POST /vendas` | `VendaService` |

## DTOs

### Lote

Entrada (`LoteRequestDTO`):

```json
{
  "hectares": 10,
  "endereco": "Estrada Rural km 12, Zona Rural",
  "planta": "Soja"
}
```

| Campo | Validação |
|---|---|
| `hectares` | Obrigatório, maior que zero |
| `endereco` | Obrigatório, até 255 caracteres |
| `planta` | Obrigatório, até 100 caracteres |

Saída (`LoteResponseDTO`), `201 Created`:

```json
{
  "id": 1,
  "hectares": 10,
  "endereco": "Estrada Rural km 12, Zona Rural",
  "planta": "Soja"
}
```

### Insumo

Entrada (`InsumoRequestDTO`):

```json
{
  "nomeItem": "Adubo NPK 10-10-10",
  "quantidade": 500
}
```

| Campo | Validação |
|---|---|
| `nomeItem` | Obrigatório, até 100 caracteres, sem repetir nome já cadastrado |
| `quantidade` | Obrigatório, zero ou mais |

Saída (`InsumoResponseDTO`), `201 Created`:

```json
{
  "id": 1,
  "nomeItem": "Adubo NPK 10-10-10",
  "quantidade": 500
}
```

### Comprador

Entrada (`CompradorRequestDTO`):

```json
{
  "cnpj": "11.222.333/0001-81",
  "endereco": "Av. Industrial, 1000, Distrito Industrial",
  "plantaPreco": "Soja",
  "preco": 120.00
}
```

| Campo | Validação |
|---|---|
| `cnpj` | Obrigatório, CNPJ válido, sem repetir um já cadastrado. Pode vir com ou sem pontos; é guardado só com números |
| `endereco` | Obrigatório, até 255 caracteres |
| `plantaPreco` | Obrigatório, até 100 caracteres |
| `preco` | Obrigatório, maior que zero |

Saída (`CompradorResponseDTO`), `201 Created`:

```json
{
  "id": 1,
  "cnpj": "11222333000181",
  "endereco": "Av. Industrial, 1000, Distrito Industrial",
  "plantaPreco": "Soja",
  "preco": 120.00
}
```

### Colheita

Entrada (`ColheitaRequestDTO`):

```json
{
  "loteId": 1,
  "quantidade": 800
}
```

| Campo | Validação |
|---|---|
| `loteId` | Obrigatório. Se o lote não existir: `404` |
| `quantidade` | Obrigatório, maior que zero. Acima de 100 sacas por hectare do lote: `400` (RN01) |

Saída (`ColheitaResponseDTO`), `201 Created`:

```json
{
  "id": 1,
  "loteId": 1,
  "quantidade": 800
}
```

### Venda

Entrada (`VendaRequestDTO`):

```json
{
  "compradorId": 1,
  "planta": "Soja",
  "quantidade": 500,
  "preco": 125.00,
  "data": "2026-10-10"
}
```

| Campo | Validação |
|---|---|
| `compradorId` | Obrigatório. Se o comprador não existir: `404` |
| `planta` | Obrigatório, até 100 caracteres. Precisa ser a planta do preço combinado com o comprador (RN02) |
| `quantidade` | Obrigatório, maior que zero. Não pode passar do estoque da planta (RN03) |
| `preco` | Obrigatório, maior que zero. Não pode divergir mais de 50% do preço do comprador (RN02) |
| `data` | Obrigatório, formato `AAAA-MM-DD` |

Saída (`VendaResponseDTO`), `201 Created`:

```json
{
  "id": 1,
  "compradorId": 1,
  "planta": "Soja",
  "quantidade": 500,
  "preco": 125.00,
  "data": "2026-10-10"
}
```

## Erros

Todo erro sai no mesmo formato (`ErroDTO`, do template):

```json
{
  "status": 400,
  "erro": "Bad Request",
  "mensagem": "RN02: o preço 300 diverge mais de 50% do preço combinado (120.00)",
  "caminho": "/vendas",
  "timestamp": "2026-10-14T19:30:00",
  "campos": []
}
```

| Situação | Status | Exemplo de mensagem |
|---|---|---|
| Campo inválido no JSON | `400` | A lista `campos` diz qual campo corrigir |
| RN01 violada | `400` | `RN01: a quantidade colhida (1500) passa da capacidade do lote (1000.00 sacas)` |
| RN02 violada | `400` | `RN02: o preço 300 diverge mais de 50% do preço combinado (120.00)` |
| RN02, planta diferente da negociada | `400` | `RN02: o comprador negociou preço para Soja, não para Milho` |
| RN03 violada | `400` | `RN03: estoque insuficiente de Soja (disponível: 300.00, pedido: 400)` |
| Insumo ou CNPJ repetido | `400` | `Já existe um insumo com o nome Adubo NPK 10-10-10` |
| Id que não existe | `404` | `Lote não encontrado: 99` |
