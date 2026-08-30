# ms-algashop-meta

Projeto em desenvolvimento para estudo e prática de e-commerce com arquitetura de microserviços, foco em Domain-Driven Design (DDD), modelagem de domínio e regras de negócio.

## Status do projeto

Em andamento.

Este repositório está sendo evoluído para estudar e aplicar conceitos de:
- microserviços
- DDD
- agregados e entidades
- value objects
- testes de domínio
- modelagem de pedidos e clientes

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

- `microservices/ms-algashop-ordering` - microsserviço responsável pela gestão de pedidos e regras de negócio do domínio.

### Documentação

- `docs/` - material de apoio, anotações e documentação do projeto.

## Tecnologias

- Java
- Spring Boot
- Gradle
- JUnit
- DDD / modelagem de domínio

## Visão geral da arquitetura

O projeto está sendo organizado com foco em isolamento de responsabilidades e modelagem do domínio, seguindo uma abordagem de microsserviços e DDD.

### Módulos em estudo

- `Customer`
- `Order`
- `OrderItem`
- `Money`
- `Quantity`
- `BillingInfo`
- `ShippingInfo`
- `Domain exceptions`
- `Value objects`
- `Domain validation`

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

- finalizar modelagem do domínio
- revisar regras de negócio do pedido
- implementar serviços de aplicação
- adicionar persistência e banco de dados
- expandir para outros microsserviços
- melhorar documentação e arquitetura geral

## Observação

Este repositório é um projeto de estudo e evolução contínua. A estrutura e as implementações podem mudar ao longo do desenvolvimento conforme os conceitos forem sendo aprofundados.

## Repositório

- GitHub: https://github.com/marcello222/ms-algashop-meta

