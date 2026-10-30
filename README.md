# Lista de Convidados

Sistema em Java para controlar a lista de convidados de um casamento usando **códigos de convite**. Cada convidado recebe um código único e só consegue se cadastrar na lista se o código for válido. A lista tem limite de **50 convidados**.

> 🚧 **Projeto em desenvolvimento.** Esta é a versão inicial, que roda apenas no console e ainda não tem interação com o usuário. Veja a seção [Roadmap](#-roadmap) para saber o que está planejado.

---

## 📋 Funcionalidades (estado atual)

- Geração de 50 códigos únicos e aleatórios no formato `AA-0000` (duas letras maiúsculas, hífen e quatro dígitos).
- Salvamento dos códigos gerados em um banco PostgreSQL.
- Proteção contra geração duplicada: se já existirem códigos no banco, a geração não é executada de novo.
- Consulta no banco para saber se um código existe e ainda não foi utilizado.
- Validação do formato do código com expressão regular.
- Regras de cadastro com exceções próprias: código com formato inválido, código inexistente e lista cheia.

---

## 🛠️ Tecnologias

| Tecnologia | Uso |
|---|---|
| Java | Linguagem principal |
| Maven | Gerenciamento de dependências e build |
| PostgreSQL | Banco de dados |
| JDBC (driver `postgresql` 42.5.4) | Conexão com o banco |

---

## 📁 Estrutura do projeto

```
ListaDeConvidados/
├── pom.xml
└── src/
    ├── Main.java                      # Testa a conexão e a consulta de um código
    ├── GerarCodigosNoBanco.java       # Gera os 50 códigos e salva no banco
    ├── Codigos/
    │   ├── CodigosGerados.java        # Gera os códigos aleatórios (HashSet, sem repetição)
    │   ├── CodigosRepository.java     # Acesso ao banco (salvar, buscar, contar)
    │   └── RegraDeCodigo.java         # Regra de formato do código (regex)
    ├── Conexao/
    │   └── Conexao.java               # Conexão com o PostgreSQL (Singleton)
    ├── Usuarios/
    │   ├── Usuario.java               # Modelo: nome, idade e código
    │   ├── BancoDeUsuarios.java       # Cadastro de usuários (em memória)
    │   └── ListaDeUsuarioValidos.java # Lista de convidados confirmados + limite de 50
    ├── Validacao/
    │   └── Validacao.java             # Junta as regras para validar um cadastro
    └── Exceptions/
        ├── CodigoInvalidoException.java
        └── ListaCheiaException.java
```

---

## ⚙️ Como executar

### Pré-requisitos

- JDK instalado (o projeto está configurado para Java 21 ou superior)
- Maven
- PostgreSQL rodando localmente

### 1. Clonar o repositório

```bash
git clone <URL-DO-SEU-REPOSITORIO>
cd ListaDeConvidados
```

### 2. Criar o banco de dados

Crie um banco chamado `codigos` e a tabela usada pelo projeto. O código espera uma tabela `codigos` com as colunas `codigo` e `validado`. Uma sugestão de criação:

```sql
CREATE DATABASE codigos;

CREATE TABLE codigos (
    codigo   VARCHAR(7) PRIMARY KEY,
    validado BOOLEAN NOT NULL DEFAULT false
);
```

### 3. Configurar a conexão

Os dados de conexão estão atualmente em `src/Conexao/Conexao.java`:

```
URL:     jdbc:postgresql://localhost:5432/codigos
Usuário: postgres
Senha:   (defina a sua)
```

Ajuste para os dados do seu PostgreSQL local.

### 4. Gerar os códigos

Execute a classe `GerarCodigosNoBanco`. Ela cria os 50 códigos e os salva no banco. Se a tabela já tiver códigos, o programa avisa e não gera novamente.

### 5. Testar a conexão

Execute a classe `Main`. Ela deve imprimir `Banco Conectado` e depois informar se um código de teste existe e ainda está disponível.

---

## 🔎 Como funciona o código de convite

- **Formato:** duas letras maiúsculas, hífen e quatro números. Exemplo: `NR-1670`.
- **Validação:** feita pela regex `^[A-Z]{2}-\d{4}$` na classe `RegraDeCodigo`.
- **Geração:** feita com `Random` e armazenada em um `HashSet`, o que garante que não haja códigos repetidos.

### Fluxo de validação de um cadastro (`Validacao`)

1. O formato do código é válido? Se não, lança `CodigoInvalidoException`.
2. O código existe? Se não, lança `CodigoInvalidoException`.
3. A lista ainda tem vaga (menos de 50 convidados)? Se não, lança `ListaCheiaException`.
4. Se tudo estiver certo, o usuário é cadastrado e entra na lista de convidados válidos.

---

## 🗺️ Roadmap

### O que ainda falta na versão atual (v1 – console)

- [ ] **Integrar a validação ao banco de dados.** Hoje a classe `Validacao` confere os códigos em uma lista em memória; o correto é consultar a tabela `codigos` (via `CodigosRepository.existeDisponivel`).
- [ ] **Marcar o código como utilizado** (`validado = true`) depois de um cadastro bem-sucedido, para que o mesmo código não possa ser usado duas vezes.
- [ ] **Persistir os convidados no banco.** Atualmente `BancoDeUsuarios` e `ListaDeUsuarioValidos` guardam os dados só em memória, que se perdem ao fechar o programa.
- [ ] **Criar as demais operações do CRUD** (consultar, atualizar e remover convidados).
- [ ] **Tirar as credenciais do banco do código-fonte** (usar variáveis de ambiente ou arquivo de configuração ignorado pelo Git).
- [ ] **Deixar a mensagem de "códigos já gerados" mais clara** em `GerarCodigosNoBanco`, já que ela aparece sempre que a tabela tem qualquer código, e não só quando o limite é ultrapassado.
- [ ] **Adicionar testes automatizados** (por exemplo, com JUnit) para as regras de código e de validação.

### Próximas versões

Cada versão será marcada com uma *tag* no Git, para que todas continuem acessíveis.

| Versão | Descrição | Status |
|---|---|---|
| **v1.0 – Console** | Funcionamento básico, sem interação do usuário | 🚧 Em andamento |
| **v2.0 – Scanner** | Interação via terminal: o usuário digita nome, idade e código | ⏳ Planejada |
| **v3.0 – Spring Boot + JPA** | API com persistência usando JPA | ⏳ Planejada |
| **v4.0 – Interface gráfica** | Tela para cadastro e consulta de convidados | ⏳ Planejada |

---

## 👤 Autor

**Juan Jorge**
