# sistema-de-financiamentos-Java


# 📚 Sistema de financiamentos Java | v.1.0.0 BETA

**Descrição:** Aplicação de financiamentos desenvolvida em Java para prática em desenvolvimento backend. Implementa funções de adicionar e remover entidades por meio banco de dados em SQL utilizando JDBC. Utiliza hash/salt de senhas, injeção de dependências via construtor e migração para Maven. Projeto em versão BETA, com funcionalidades básicas e em constante evolução.

## 📝 Changelog - v.1.0.0 BETA - 2026-01-22
### Autenticação e Registro de Usuários
- Implementação de login e registro com persistência em banco de dados
- Senhas protegidas com hash e salt (BCrypt)
- Separação clara entre View, Service e Repository
- Validação de CPF com exceções de domínio

### Arquitetura e Boas Práticas
- Injeção de dependências via construtor em todas as camadas
- Eliminação de instanciação direta (`new`) fora da classe Main
- Uso consistente de exceções de domínio para regras de negócio

### Gerenciamento de Dependências
- Migração do projeto para Maven
- Remoção de dependências manuais (.lib)
- Dependências declaradas via `pom.xml`



## 🚀 Tecnologias Utilizadas
- Java 24
- IDE: IntelliJ
- Estrutura de dados: Permanência de dados em SQL por meio de JDBC

---

## 📂 Estrutura do Projeto
**ProjetoFinanciamentos/**

**Model** → classes que representam as entidades (Apartamento, Casa, Financiamento e Terreno

**Repository** → armazenamento em arquivos TXT e parâmetros básicos

**Service** → regras de negócio e validações.

**View** → interação com o usuário via console (menus e mensagens).

**Main** → ponto de entrada, que chama o menu principal pela View

---

## 🗄️ Banco de Dados

Este projeto utiliza **MySQL** para persistência de dados.

A estrutura do banco de dados está definida no arquivo:

database/schema.sql

### Como criar o banco
1. Crie um banco vazio no MySQL
2. Execute o script de criação das tabelas:
```bash
mysql -u seu_usuario -p nome_do_banco < main/database/schema.sql
```

## ▶️ Como Executar
*--PONTO IMPORTANTE--*
**A partir do commit desde readme e das alterações feitas nessa versão do projeto, a permanência
de dados é feita exclusivamente via banco de dados SQL.
Para que não haja versionamento de informações sensíveis, são utilizadas variáveis de ambiente**

Antes de executar o projeto, é **obrigatório** configurar as seguintes variáveis de ambiente:

- `DB_URL`
- `DB_USER`
- `DB_PASSWORD`
*OBRIGATORIO A REINICIALIZAÇÃO DA IDE OU DO TERMINAL PARA QUE AS MUDANÇAS ENTREM EM VIGOR*

**COMO FAZER ISSO POR MEIO DE POWERSHELL/CMD (WINDOWS)**

**setx DB_URL "jdbc:mysql://localhost:3306/projeto_financiamentos"
setx DB_USER "root"
setx DB_PASSWORD "sua_senha"**

**(LINUX / MAC)**

**export DB_URL="jdbc:mysql://localhost:3306/projeto_financiamentos"
export DB_USER="root"
export DB_PASSWORD="sua_senha"**

1. **Clone este repositório**
   ```bash
   git clone https://github.com/lopesdev07/sistema-de-financiamentos-Java
   ```
2. **Abra o projeto**
   Abra a pasta do projeto na sua IDE
3. **Compilar e executar**
   Localize na IDE o arquivo Main.java e dê **Run**

---

📌 **Funcionalidades**

➕ Adicionar entidades 

📋 Listar entidades

---

🛠 Próximos Passos:

1:  Implementar mais utilidades para o usuário e para financiamentos

2: Revisão da fatoração de instâncias de objetos de todos os packages

3: Adicionar lógica de usuário (perfis, permissões, etc) e lógica de sessão 

4: Implementar testes unitários

5: Revisar comentários e javadocs (caso seja necessário)

6- Migração total do projeto para Spring Boot