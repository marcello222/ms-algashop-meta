# ms-algashop-docs

Documentação completa do projeto **AlgaShop - Microserviços**.

## 📋 Índice

- [Visão Geral](#visão-geral)
- [Microserviços](#microserviços)
  - [ms-algashop-ordering](#ms-algashop-ordering)
- [Arquitetura](#arquitetura)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)

---

## 🎯 Visão Geral

O projeto **AlgaShop** é uma aplicação de e-commerce desenvolvida utilizando arquitetura de microserviços e princípios de Domain-Driven Design (DDD). O objetivo é criar um sistema escalável, modular e bem estruturado para gerenciamento de pedidos e clientes.

---

## 🛍️ Microserviços

### ms-algashop-ordering

Microserviço responsável pela gestão de pedidos e clientes da plataforma AlgaShop.

#### 📦 Tecnologias

- **Java 21**
- **Spring Boot 3.5.6**
- **Gradle**
- **Lombok** (redução de boilerplate)
- **JUnit 5** e **AssertJ** (testes)
- **Apache Commons Validator** (validação de e-mail)
- **java-uuid-generator** (geração de UUIDs time-based)

---

## 🏗️ Arquitetura - Domain-Driven Design

O microserviço foi desenvolvido seguindo os princípios de **Domain-Driven Design (DDD)**, com foco na modelagem rica do domínio.

### 📁 Estrutura do Projeto

```
src/main/java/com/algaworks/algashop/ordering/
├── domain/
│   ├── entity/          # Entidades do domínio
│   ├── valueobject/     # Value Objects
│   ├── exception/       # Exceções de domínio
│   ├── validator/       # Validações compartilhadas
│   └── utility/         # Utilitários do domínio
└── OrderingApplication.java
```

---

## 🎯 Camada de Domínio

### 1️⃣ Entidades (Entity)

#### **Customer** (Cliente)
Representa um cliente da plataforma com comportamento rico e regras de negócio encapsuladas.

**Atributos:**
- `id`: Identificador único (CustomerId)
- `fullName`: Nome completo (FullName)
- `birthDate`: Data de nascimento (BirthDate)
- `email`: E-mail (Email)
- `phone`: Telefone (Phone)
- `document`: Documento (Document)
- `promotionNotificationsAllowed`: Aceita notificações promocionais
- `archived`: Cliente arquivado/anonimizado
- `registeredAt`: Data de registro
- `archivedAt`: Data de arquivamento
- `loyaltyPoints`: Pontos de fidelidade (LoyaltyPoints)
- `address`: Endereço (Address)

**Comportamentos Principais:**

- **Criação de Cliente Novo**:
  ```java
  Customer.brandNew()
      .fullName(new FullName("John", "Doe"))
      .email(new Email("john@example.com"))
      // ... outros atributos
      .build();
  ```

- **Adicionar Pontos de Fidelidade**:
  ```java
  customer.addLoyaltyPoints(new LoyaltyPoints(100));
  ```

- **Arquivamento (Anonimização - LGPD/GDPR)**:
  ```java
  customer.archive();
  ```
  - Anonimiza dados pessoais (nome, e-mail, telefone, documento)
  - Mantém histórico de pontos de fidelidade
  - Impede alterações futuras no cliente arquivado

- **Alterações de Dados**:
  ```java
  customer.changeName(new FullName("Jane", "Doe"));
  customer.changeEmail(new Email("jane@example.com"));
  customer.changePhone(new Phone("123-456-7890"));
  customer.changeAddress(newAddress);
  ```

- **Gerenciamento de Notificações**:
  ```java
  customer.enablePromotionNotifications();
  customer.disablePromotionNotifications();
  ```

**Regras de Negócio:**
- ✅ Clientes arquivados não podem ser modificados
- ✅ Ao arquivar, dados são anonimizados automaticamente
- ✅ Pontos de fidelidade só podem ser adicionados (não removidos)
- ✅ Apenas valores positivos podem ser adicionados aos pontos

---

### 2️⃣ Value Objects (Objetos de Valor)

Value Objects são objetos imutáveis que representam conceitos do domínio sem identidade própria.

#### **CustomerId**
- Identificador único do cliente usando UUID time-based
- Gerado automaticamente na criação
- Garante ordenação temporal dos registros

#### **FullName** (Nome Completo)
- Composto por `firstName` e `lastName`
- **Validações**:
  - ❌ Não pode ser nulo
  - ❌ Não pode estar em branco
  - ✅ Realiza trim automático

#### **Email**
- Endereço de e-mail válido
- **Validações**:
  - ❌ Não pode ser nulo ou vazio
  - ❌ Deve ser um e-mail válido (RFC 5322)
  - ✅ Utiliza Apache Commons Validator

#### **Phone** (Telefone)
- Número de telefone
- **Validações**:
  - ❌ Não pode ser nulo ou em branco

#### **Document** (Documento)
- Documento de identificação (CPF, SSN, etc.)
- **Validações**:
  - ❌ Não pode ser nulo ou em branco
  - ✅ Realiza trim automático

#### **BirthDate** (Data de Nascimento)
- Data de nascimento do cliente
- **Validações**:
  - ❌ Não pode ser no futuro
  - ❌ Não pode ser nulo
- **Comportamentos**:
  - Calcula idade automaticamente: `birthDate.age()`

#### **LoyaltyPoints** (Pontos de Fidelidade)
- Pontos acumulados pelo cliente
- **Constante**: `LoyaltyPoints.ZERO` (0 pontos)
- **Validações**:
  - ❌ Não pode ser negativo
  - ❌ Não pode adicionar valores <= 0
- **Operações**:
  ```java
  loyaltyPoints.add(10);  // Adiciona 10 pontos
  loyaltyPoints.add(new LoyaltyPoints(20));  // Adiciona 20 pontos
  ```
- Implementa `Comparable` para ordenação

#### **Address** (Endereço)
- Endereço completo do cliente
- **Atributos**:
  - `street`: Rua (obrigatório)
  - `number`: Número (obrigatório)
  - `complement`: Complemento (opcional)
  - `neighborhood`: Bairro (obrigatório)
  - `city`: Cidade (obrigatório)
  - `state`: Estado (obrigatório)
  - `zipCode`: CEP/Código Postal (obrigatório)
- **Validações**:
  - ❌ Campos obrigatórios não podem ser nulos ou vazios
  - ✅ Suporta builder pattern e toBuilder (imutabilidade)

#### **ZipCode** (Código Postal)
- CEP/Código Postal
- **Validações**:
  - ❌ Não pode ser nulo ou em branco
  - ❌ Deve ter exatamente 5 caracteres

---

### 3️⃣ Exceções de Domínio

#### **DomainException**
- Exceção base para todas as exceções do domínio
- Extends `RuntimeException`

#### **CustomerArchivedException**
- Lançada quando se tenta modificar um cliente arquivado
- Mensagem: *"Customer is archived cannot be changed"*

#### **ErrorMessages**
- Classe com constantes de mensagens de erro:
  - `VALIDATION_ERROR_EMAIL_IS_INVALID`
  - `VALIDATION_ERROR_BIRTHDATE_MUST_IN_PAST`
  - `VALIDATION_ERROR_FULLNAME_IS_NULL`
  - `VALIDATION_ERROR_FULLNAME_IS_BLANK`
  - `ERROR_CUSTOMER_ARCHIVED`

---

### 4️⃣ Validadores (Validators)

#### **FieldValidations**
Classe utilitária com validações reutilizáveis:

- **requiresNonBlank(String value)**:
  - Valida que uma string não é nula nem vazia

- **requiresValidEmail(String email)**:
  - Valida formato de e-mail usando Apache Commons Validator
  - Suporta RFC 5322

---

### 5️⃣ Utilitários (Utilities)

#### **IdGenerator**
- Gerador de UUIDs time-based (UUID v7)
- Utiliza `java-uuid-generator` (Fasterxml)
- **Benefícios**:
  - ✅ IDs ordenáveis temporalmente
  - ✅ Melhor performance em índices de banco de dados
  - ✅ Melhor distribuição em sistemas distribuídos

---

## 🧪 Testes

### Testes Implementados

#### **LoyaltyPointsTest**
- ✅ Criação com valor
- ✅ Adição de pontos válidos
- ✅ Rejeição de valores negativos
- ✅ Rejeição de valor zero

#### **CustomerTest**
- ✅ Validação de e-mail inválido na criação
- ✅ Validação de e-mail inválido na atualização
- ✅ Arquivamento e anonimização de dados
- ✅ Bloqueio de alterações em clientes arquivados
- ✅ Adição de pontos de fidelidade
- ✅ Validação de pontos inválidos (negativos/zero)

#### **CustomerTestDataBuilder**
Builders para criação de dados de teste:
- `brandNewCustomer()`: Cliente novo
- `existingCustomer()`: Cliente existente
- `existingAnonymizedCustomer()`: Cliente arquivado/anonimizado

---

## 🔧 Build e Execução

### Requisitos
- Java 21+
- Gradle 8.x

### Comandos

**Compilar o projeto:**
```bash
./gradlew build
```

**Executar testes:**
```bash
./gradlew test
```

**Executar a aplicação:**
```bash
./gradlew bootRun
```

**Gerar JAR:**
```bash
./gradlew bootJar
```

---

## 📊 Princípios Aplicados

### Domain-Driven Design (DDD)
- ✅ **Ubiquitous Language**: Linguagem compartilhada entre negócio e código
- ✅ **Rich Domain Model**: Entidades com comportamento rico
- ✅ **Value Objects**: Objetos imutáveis senza identidade
- ✅ **Encapsulation**: Regras de negócio encapsuladas nas entidades

### Clean Code
- ✅ **Imutabilidade**: Records para Value Objects
- ✅ **Builder Pattern**: Criação fluente de objetos
- ✅ **Factory Methods**: Métodos de fábrica para diferentes cenários
- ✅ **Validação no Construtor**: Fail-fast approach

### SOLID
- ✅ **Single Responsibility**: Cada classe tem uma única responsabilidade
- ✅ **Open/Closed**: Aberto para extensão, fechado para modificação
- ✅ **Dependency Inversion**: Dependências apontam para abstrações

### Outras Práticas
- ✅ **LGPD/GDPR Compliance**: Anonimização de dados pessoais
- ✅ **Defensive Programming**: Validações em todos os pontos de entrada
- ✅ **Test-Driven Development**: Cobertura de testes para regras de negócio

---

## 📝 Notas de Implementação

### Decisões de Design

1. **Records para Value Objects**: Java Records garantem imutabilidade e reduzem boilerplate
2. **Lombok para Builders**: Facilita criação de objetos complexos
3. **UUID Time-based**: Melhor performance e ordenação natural
4. **Arquivamento != Exclusão**: Mantém dados históricos importantes (pontos de fidelidade)
5. **Validações no Construtor**: Garante que objetos inválidos nunca sejam criados

### Melhorias Futuras
- 🔄 Implementar eventos de domínio (Domain Events)
- 🔄 Adicionar agregados para pedidos (Order)
- 🔄 Implementar repositórios
- 🔄 Adicionar camada de aplicação (Application Layer)
- 🔄 Implementar API REST
- 🔄 Adicionar persistência com JPA/Hibernate
- 🔄 Implementar autenticação e autorização

---

## 👥 Autor

Desenvolvido com ❤️ seguindo as melhores práticas de Domain-Driven Design e Clean Architecture.

---

## 📄 Licença

Este projeto é parte de um estudo de caso sobre microserviços e DDD.
