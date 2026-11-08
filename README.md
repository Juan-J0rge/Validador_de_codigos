# Lista de Convidados

Um projeto em Java que desenvolvi para simular o controle de convidados de um casamento. A ideia é simples: cada convidado recebe um código único e precisa utilizá-lo para entrar na lista. Para isso, o código deve existir e ainda não ter sido utilizado.

O sistema trabalha com um limite de 50 convidados e utiliza o PostgreSQL para armazenar os códigos de convite.

**Projeto em desenvolvimento:** atualmente, o sistema está em sua primeira versão e funciona apenas pelo console, sem interação direta com o usuário.

---

## Funcionalidades

- Geração de 50 códigos aleatórios e únicos no formato `AA-0000`.
- Armazenamento dos códigos no PostgreSQL.
- Verificação para evitar a geração duplicada de códigos.
- Consulta para verificar se um código existe e está disponível.
- Validação do formato dos códigos utilizando Regex.
- Regras para impedir cadastros com códigos inválidos ou quando a lista estiver cheia.
- Exceções personalizadas para tratar situações específicas.

---

## Tecnologias utilizadas

| Tecnologia | Aplicação no projeto |
|---|---|
| Java | Desenvolvimento da aplicação |
| Maven | Gerenciamento de dependências |
| PostgreSQL | Armazenamento dos códigos |
| JDBC | Comunicação com o banco de dados |

---

## Estrutura do projeto

```text
ListaDeConvidados/
├── pom.xml
└── src/
    ├── Main.java
    ├── GerarCodigosNoBanco.java
    ├── Codigos/
    │   ├── CodigosGerados.java
    │   ├── CodigosRepository.java
    │   └── RegraDeCodigo.java
    ├── Conexao/
    │   └── Conexao.java
    ├── Usuarios/
    │   ├── Usuario.java
    │   ├── BancoDeUsuarios.java
    │   └── ListaDeUsuarioValidos.java
    ├── Validacao/
    │   └── Validacao.java
    └── Exceptions/
        ├── CodigoInvalidoException.java
        └── ListaCheiaException.java
```

---

## Como executar

### Pré-requisitos

- JDK 21 ou superior
- Maven
- PostgreSQL instalado e em execução

### 1. Clone o repositório

```bash
git clone <URL-DO-SEU-REPOSITORIO>
cd ListaDeConvidados
```

### 2. Crie o banco de dados

```sql
CREATE DATABASE codigos;

CREATE TABLE codigos (
    codigo VARCHAR(7) PRIMARY KEY,
    validado BOOLEAN NOT NULL DEFAULT false
);
```

### 3. Configure a conexão

As configurações estão na classe `Conexao.java`, dentro da pasta `Conexao`.

Ajuste a URL, o usuário e a senha de acordo com seu PostgreSQL.

```text
URL: jdbc:postgresql://localhost:5432/codigos
Usuário: postgres
Senha: sua senha
```

### 4. Gere os códigos

Execute a classe `GerarCodigosNoBanco`. Ela será responsável por gerar os 50 códigos e armazená-los no banco.

### 5. Execute a aplicação

Execute a classe `Main` para testar a conexão com o banco e verificar a disponibilidade de um código.

---

## Validação dos convites

Os códigos seguem um padrão definido: duas letras maiúsculas, um hífen e quatro números.

Exemplo: `NR-1670`

Para garantir esse formato, utilizei uma expressão regular:

```java
^[A-Z]{2}-\\d{4}$
```

A geração utiliza `Random` e `HashSet`, evitando repetições durante a criação.

### Fluxo de cadastro

A classe `Validacao` reúne as regras necessárias para verificar se um convidado pode entrar na lista:

1. Verifica se o código possui o formato correto.
2. Confere se o código existe.
3. Verifica se ainda existem vagas disponíveis.
4. Se todas as condições forem atendidas, o convidado é adicionado à lista.

Caso alguma regra não seja atendida, uma exceção específica é lançada.

---

## Próximas melhorias

Ainda tenho algumas melhorias planejadas para o projeto. A ideia é evoluir aos poucos, adicionando funcionalidades e aprimorando a estrutura da aplicação.

- Integrar a validação diretamente com o banco de dados.
- Marcar os códigos como utilizados após um cadastro bem-sucedido.
- Salvar os dados dos convidados no PostgreSQL.
- Implementar as operações de consulta, atualização e remoção (CRUD).
- Remover as credenciais do banco do código-fonte.
- Melhorar as mensagens de retorno da aplicação.
- Adicionar testes automatizados com JUnit.

### Evolução do projeto

| Versão | Objetivo | Status |
|---|---|---|
| **v1.0 – Console** | Estrutura inicial e regras de validação | Em andamento |
| **v2.0 – Scanner** | Cadastro interativo pelo terminal | Planejada |
| **v3.0 – Spring Boot + JPA** | Transformar o projeto em uma API | Planejada |
| **v4.0 – Interface gráfica** | Criar uma interface para cadastro e consulta | Planejada |

Pretendo manter cada versão registrada por meio de tags no Git, permitindo acompanhar a evolução do projeto.

---

## Autor

**Juan Jorge**

Estudante de Ciência da Computação e desenvolvedor com foco em Java e Spring Boot.

[GitHub](https://github.com/Juan-J0rge) • [LinkedIn](https://www.linkedin.com/in/devjuan)
