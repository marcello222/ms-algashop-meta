# ms-algashop-meta

Projeto de estudo e prática de e-commerce em arquitetura de microsserviços, com foco em Domain-Driven Design (DDD), modelagem de domínio e regras de negócio.

## Status

Em desenvolvimento.

Testes: 19 testes unitários passaram (0 falhas, 0 ignorados) — executado em 2026-09-16.



Este repositório está sendo evoluído para aplicar conceitos de arquitetura, modelagem de domínio e boas práticas de desenvolvimento, seguindo a linha de estudo da Algaworks.

## Objetivo do projeto

Construir uma base para um ecommerce robusto, com foco em:
- modelagem do domínio
- entidades e value objects
- agregados e aggregate root
- regras de negócio encapsuladas
- testes automatizados
- microsserviços
- evolução gradual da arquitetura

## Estrutura do repositório

```text
ms-algashop-meta/
├── README.md
├── .gitignore
├── docs/
│   └── ms-algashop-docs/
└── microservices/
    └── ms-algashop-ordering/
```

### Microserviço principal

- `microservices/ms-algashop-ordering` - microsserviço responsável pelo domínio de pedidos, clientes e regras do ecommerce.

### Documentação

- `docs/ms-algashop-docs` - material de apoio, diagramas e documentação do projeto.

## Tecnologias

- Java
- Spring Boot
- Gradle
- JUnit
- DDD
- Microserviços

## Padrões e conceitos em estudo

### Entity
Entidades com identidade própria, que mudam ao longo do tempo.

Exemplos:
- `Customer`
- `Order`
- `OrderItem`

### Value Object
Objetos imutáveis que representam valor, não identidade.

Exemplos:
- `Money`
- `Quantity`
- `Address`
- `Email`
- `Phone`
- `Document`

### Aggregate / Aggregate Root
Agrupamentos de entidades e value objects tratados como uma unidade.

Exemplo:
- `Order` como aggregate root
- `OrderItem` como parte do agregado

### Factory
Criação de objetos complexos com intenção explícita.

Exemplos:
- `Order.draft(...)`
- `OrderItem.brandNew(...)`

### Builder
Construção de objetos com muitos parâmetros ou cenários específicos.

Exemplos:
- `Order.existing()`
- `OrderItem.existing()`
- `OrderItem.brandNew()`

### Repository
Abstrai acesso e persistência do domínio.

### Domain Service
Centraliza regras de negócio que não pertencem a uma entidade específica.

### Domain Event
Representa eventos do negócio que podem disparar ações futuras.

### Specification
Representa regras de validação e seleção de domínio.

### Strategy
Encapsula diferentes formas de executar uma regra ou algoritmo.

### Chain of Responsibility
Permite validar regras em sequência.

### Dependency Injection
Reduz acoplamento e melhora testes e manutenção.

## Arquitetura em evolução

A ideia do projeto é evoluir em etapas:

1. modelar o domínio corretamente
2. criar entidades e value objects
3. definir aggregados e regras de consistência
4. usar builders e factories para criação segura
5. validar regras com domain services/specifications
6. preparar persistência e integração
7. evoluir para microsserviços e infraestrutura na AWS

## Fluxo de domínio atual

A base atual já contempla modelos de pedido, cliente, itens, valor e status.

O fluxo principal em estudo é:
- criar pedido
- validar dados
- adicionar itens
- calcular valor total
- pagar
- preparar entrega
- entregar
- cancelar quando aplicável

## Como executar

Acesse o microsserviço e rode:

```bash
cd microservices/ms-algashop-ordering
./gradlew bootRun
```

No Windows:

```powershell
cd microservices\ms-algashop-ordering
gradlew.bat bootRun
```

## Próximos passos

- revisar e reforçar regras de negócio do pedido
- implementar mais regras de status e transição
- adicionar persistência com banco de dados
- criar repositories e serviços de aplicação
- evoluir para novos microsserviços
- preparar infraestrutura e deploy na AWS
- automatizar CI/CD

## Observação

Este repositório é um projeto de estudo em evolução contínua. A estrutura e os conceitos serão ajustados conforme o domínio for ficando mais rico e a arquitetura for amadurecendo.

## Repositório

- GitHub: https://github.com/marcello222/ms-algashop-meta

