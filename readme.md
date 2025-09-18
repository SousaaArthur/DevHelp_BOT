# DevHelp BOT 🤖

Um bot para **Discord** desenvolvido em **Java** com a biblioteca **JDA (Java Discord API)**. O objetivo do DevHelp BOT é oferecer ferramentas úteis para programadores e comunidades, unindo **estudos, diversão e utilidades** em um só projeto.

---

## ✨ Funcionalidades

* 📚 **Estudos**: comandos para exercícios e aprendizado interativo.
* 🎭 **Diversão**: sistema de memes com curtidas e visualizações.
* 🧑‍💻 **Perfis de usuário**: adição de bio e gerenciamento de informações.
* ⚡ **Comandos slash**: modernos e organizados.
* 🔔 **Listeners de eventos**: reagem a mensagens, chamadas e interações.

---

## ⚙️ Tecnologias utilizadas

* **Java 17+**
* **Maven** (gerenciador de dependências)
* **JDA (Java Discord API)**
* **Banco de dados** (implementação de repositórios para usuários, exercícios e memes)
* **Arquivos `.env`** para configuração

---

## 🚀 Como rodar o projeto localmente

### 1. Clonar o repositório

```bash
git clone https://github.com/seu-usuario/DevHelp_BOT.git
cd DevHelp_BOT
```

### 2. Configurar variáveis de ambiente

Copie o arquivo de exemplo:

```bash
cp .env.example .env
```

Edite o arquivo `.env` e insira:

```env
TOKEN=seu_token_do_discord
GUILD_ID=id_do_servidor
```

### 3. Build do projeto

```bash
mvn clean install
```

### 4. Executar o bot

```bash
mvn exec:java -Dexec.mainClass="devhelp.bot.Bot"
```

---

## 📂 Estrutura do projeto

```
DevHelp_BOT/
├── pom.xml                  # Configuração Maven
├── .env.example             # Exemplo de variáveis de ambiente
├── src/main/java/devhelp/bot
│   ├── Bot.java             # Classe principal do bot
│   ├── commands/            # Comandos do bot
│   ├── events/              # Listeners de eventos
│   ├── database/            # Gerenciamento de dados (Users, Memes, Exercícios)
│   ├── config/              # Configurações e utilidades
│   └── exceptions/          # Exceções personalizadas
```

---

## 💡 Exemplos de comandos

* `/exercise` → gera um exercício de programação.
* `/profile addbio` → adiciona uma bio ao seu perfil.
* `/meme` → envia um meme e permite reações.
* `/help` → mostra categorias de ajuda.

---

## 🤝 Contribuição

1. Faça um fork do projeto.
2. Crie uma branch para sua feature: `git checkout -b minha-feature`
3. Commit suas alterações: `git commit -m 'feat: nova funcionalidade'`
4. Faça o push: `git push origin minha-feature`
5. Abra um Pull Request 🚀

---

## 📜 Licença

Este projeto é de uso livre para estudo e colaboração. Verifique a licença definida no repositório.

---

👨‍💻 Desenvolvido com dedicação pela comunidade DevHelp.
