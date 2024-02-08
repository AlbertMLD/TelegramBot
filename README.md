# Telegram Bot with Java

This Java application implements a simple Telegram bot using the Telegram Bot API. The bot responds to user messages, handles callback queries (button presses), and interacts with a MongoDB database to store user IDs.

## Prerequisites

- Java 8 or later
- Maven
- MongoDB installed and running on localhost:27017

## Getting Started

1. **Clone the repository to your local machine:**
    ```bash
    git clone https://github.com/your-username/telegram-bot-java.git
    ```
2. **Open the project in your preferred Java IDE.**

3. **Replace the Bot.java class variables (BOT_TOKEN and USERNAME) with your Telegram bot's token and username:**
    ```java
    static final String BOT_TOKEN = "your-telegram-bot-token";
    static final String USERNAME = "your-bot-username";
    ```

4. **Run the Main.java class to start the Telegram bot.**

## Features

1. **Basic Responses**
   - If the user sends "Hello," the bot replies with "How are you?"
   - If the user sends "How are you?," the bot replies with "I'm fine, thank you!"

2. **Current Time**
   - If the user's message contains the word "time," the bot prompts the user to know the current time. It provides an inline keyboard with "Yes" and "No thanks" options.

3. **Day of the Week**
   - If the user sends "/day," the bot replies with the current day of the week.

4. **MongoDB Integration**
   - The bot connects to a MongoDB database and stores user IDs in the "customers" collection.

## Project Structure

- **Main.java:** Main class to initialize and register the Telegram bot.
- **Bot.java:** Configuration class with Telegram bot token and username.
- **Responder.java:** TelegramLongPollingBot implementation handling user messages and callback queries.
- **MongoDB.java:** Class for MongoDB integration, including database connection and user ID insertion.
- **CallBackData.java:** Enum for callback data constants.

## Dependencies

- `org.telegram.telegrambots:telegrambots-meta`: Telegram Bots API
- `org.mongodb:mongodb-driver-sync`: MongoDB Java Driver

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
