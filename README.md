# ms-algashop-meta

Projeto em desenvolvimento para estudo e prática de e-commerce com arquitetura de microserviços, usando Domain-Driven Design (DDD), modelagem de domínio, value objects, entidades, agregados e regras de negócio encapsuladas.

## Status do projeto

Em andamento.

Este repositório está sendo evoluído para aplicar os principais conceitos ensinados pela Algaworks em arquitetura de software, DDD e microsserviços.

## Visão geral

O objetivo do projeto é construir uma base para um ecommerce robusto, seguindo boas práticas de:
- modelagem de domínio
- composição de agregados
- encapsulamento de regras de negócio
- testes automatizados
- arquitetura distribuída em microsserviços
- evolução gradual do sistema

## Estrutura do repositório

```text
ms-algashop-meta/
├── README.md
├── .gitignore
├── docs/
│   └── docs/
└── microservices/
    └── ms-algashop-ordering/
```

### Microserviço principal

- `microservices/ms-algashop-ordering` - microsserviço focado na gestão de pedidos, clientes e regras do domínio do ecommerce.

### Documentação

- `docs/` - documentação, anotações, levantamentos e materiais de apoio.

## Tecnologias

- Java
- Spring Boot
- Gradle
- JUnit
- DDD
- Microserviços

## Padrões e conceitos de design que estamos aplicando

A seguir estão os principais padrões e conceitos de modelagem que estamos estudando e implementando no projeto, seguindo a linha da Algaworks.

### 1) Entity
Uma entidade tem identidade própria e seu estado pode mudar ao longo do tempo.

Exemplos no projeto:
- `Customer`
- `Order`
- `OrderItem`

### 2) Value Object
Um value object representa um valor, não uma identidade. Normalmente é imutável e comparado pelo conteúdo.

Exemplos no projeto:
- `Money`
- `Quantity`
- `Address`
- `Email`
- `Phone`
- `Document`

### 3) Aggregate
Um aggregate agrupa entidades e value objects que devem ser tratados como uma unidade.

No projeto:
- `Order` é o aggregate root
- `OrderItem` pertence ao aggregate

### 4) Aggregate Root
É a entidade principal de um aggregate, responsável por controlar a consistência interna.

Exemplo:
- `Order` controla o ciclo do pedido e seus itens.

### 5) Factory
Factory encapsula a criação de objetos complexos, deixando explícita a intenção de criação.

Exemplos no projeto:
- `Order.draft(...)`
- `OrderItem.brandNew(...)`

### 6) Builder
Builder é usado para construir objetos com muitos atributos ou com múltiplos cenários de criação.

Exemplos no projeto:
- `Order.existing()`
- `OrderItem.existing()`
- `OrderItem.brandNew()`

### 7) Repository
Repository abstrai o acesso à persistência e isola o domínio da infraestrutura.

Em um próximo passo do projeto, iremos aplicar repositories para persistir entidades do domínio.

### 8) Domain Service
Domain service guarda regras de negócio que não pertencem a uma única entidade.

Exemplos esperados:
- cálculo de valor total do pedido
- validação de disponibilidade de itens
- regras de pagamento e entrega

### 9) Domain Event
Domain events representam algo importante que aconteceu no domínio e pode disparar ações futuras.

Exemplos esperados:
- pedido criado
- pagamento confirmado
- pedido cancelado
- pedido pronto para entrega

### 10) Specification
Specification representa regras de seleção e validação de domínio com linguagem clara.

Exemplos esperados:
- pedido em status pendente
- cliente ativo
- pedido com valor acima do mínimo

### 11) Strategy
Strategy encapsula variações de comportamento em algoritmos diferentes.

Exemplos esperados:
- estratégias de cálculo de frete
- estratégias de desconto
- estratégias de pagamento

### 12) Chain of Responsibility
Esse padrão pode ser usado para executar uma sequência de validações ou regras com responsabilidade encadeada.

Exemplos esperados:
- validação de dados do pedido
- validação de pagamento
- validação de regras de entrega

### 13) Singleton
Quando necessário em componentes de infraestrutura ou configuração compartilhada.

Exemplos esperados:
- gerenciadores de configuração
- componentes de infraestrutura compartilhados

### 14) Adapter
Adapter é útil para integrar peças externas sem quebrar o domínio.

Exemplos esperados:
- integração com serviço de pagamento
- integração com envio de email
- integração com estoque

### 15) Dependency Injection
A injeção de dependência é fundamental para manter acoplamento baixo e facilitar testes.

Será aplicada em:
- serviços de domínio
- repositories
- integrações externas
- framework e infraestrutura

## Padrões de aplicação no projeto

O projeto está sendo evoluído em fases, seguindo esta linha de pensamento:

1. modelar o domínio corretamente
2. criar entidades e value objects
3. garantir consistência com agregados e factory
4. usar builders para construção de objetos complexos
5. aplicar regras de validação com domain service e specification
6. preparar a base para persistência e microsserviços
7. evoluir para comunicação entre serviços e integração externa

## Fluxo de domínio em estudo

A ideia central é que o domínio da aplicação seja rico e encapsule regras, em vez de espalhar lógica por camadas de infraestrutura.

Exemplo do fluxo:
- criar pedido
- verificar status
- validar regras
- adicionar itens
- calcular valor total
- confirmar pagamento
- encaminhar para entrega

## Como executar

Acesse o microsserviço e execute:

```bash
cd microservices/ms-algashop-ordering
./gradlew bootRun
```

Se estiver no Windows:

```powershell
cd microservices\ms-algashop-ordering
gradlew.bat bootRun
```

## Próximos passos

- finalizar a modelagem do domínio
- revisar e reforçar regras de negócio
- ampliar os value objects e validações
- implementar repository e persistência
- introduzir serviços de aplicação
- evoluir para mais microsserviços
- preparar deploy na AWS
- implementar pipeline CI/CD e infraestrutura

## Observação

Este repositório é um projeto de estudo em evolução contínua. A estrutura e os padrões podem ser ajustados conforme o domínio for ficando mais rico e conforme a arquitetura dos microsserviços for amadurecendo.

## Repositório

- GitHub: https://github.com/marcello222/ms-algashop-meta

