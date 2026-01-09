# sistema-de-financiamentos-Java


# 📚 Sistema de financiamentos Java 

**Descrição:** Aplicação desenvolvida em Java para prática em desenvolvimento backend. Implementa funções de adicionar e remover entidades por meio banco de dados em SQL utilizando JDBC. Projeto pouco utilizavel/esqueleto e em constante evolução

## 📝 Changelog
- Implementação de persistência de dados via banco de dados MySQL utilizando JDBC
- Criação da classe "DatabaseConnection" para a configuração da conexão de acesso ao banco
- Utilização de variáveis de ambiente para a conexão do BD, visando evitar o versionamento de dados sensíveis
- Implementação total de JavaDocs ao projeto e remoção de comentários desnecessários
- Mudanças nos tratamentos de erros e melhorias na lógica geral dos códigos/projeto

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

1: Implementação de utilidade para os financiamentos

2: Melhorar tratamento de exceções

3: Revisar comentários e javadocs (caso seja necessário)

4: Implementar testes unitários

5: Migração total do projeto para Spring Boot
