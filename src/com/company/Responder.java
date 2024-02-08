package com.company;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Responder extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return Bot.USERNAME;
    }

    @Override
    public String getBotToken() {
        return Bot.BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {

        // Default response message
        String response = "I'm sorry, I haven't understood the message you sent";

        // Variable to store chat ID
        String chatId = "";

        // Create a new message object to send a response
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(response);

        // Check if the update contains a callback query (button press)
        if (update.hasCallbackQuery() && update.getCallbackQuery().getData() != null && !update.getCallbackQuery().getData().isEmpty()) {
            // Extract chat ID from callback query
            chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());

            // Extract callback data
            String callBackData = update.getCallbackQuery().getData();

            // Check if the callback data is "/YES"
            if (callBackData.equalsIgnoreCase("/YES")) {
                // Get the current time and set it as the response
                LocalDateTime currentTime = LocalDateTime.now();
                sendMessage.setText(currentTime.toString());
            }
        }

        // Check if the update contains a regular text message
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Extract chat ID from the message
            chatId = String.valueOf(update.getMessage().getChatId());
            // Get the user's message
            String userMessage = update.getMessage().getText().trim();

            // Check if the user's message is "Hello"
            if (userMessage.equalsIgnoreCase("Hello")){
                sendMessage.setText("How are you?");
            }

            // Check if the user's message is "How are you?"
            if (userMessage.equalsIgnoreCase("How are you?")) {
                sendMessage.setText("I'm fine thank you!");
            }

            // Check if the user's message contains the word "time"
            if (userMessage.contains("time")) {

                // Set a prompt message about the current time
                sendMessage.setText("Would you like to know the current time?");

                // Create an inline keyboard with a "Yes" button
                List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
                List<InlineKeyboardButton> buttonsRow = new ArrayList<>();
                InlineKeyboardButton yesButton = new InlineKeyboardButton();
                yesButton.setText("Yes?");
                yesButton.setCallbackData("/YES");
                buttonsRow.add(yesButton);
                keyboard.add(buttonsRow);

                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                inlineKeyboardMarkup.setKeyboard(keyboard);

                // Attach the inline keyboard to the response message
                sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            }
        }

        // Check if the chat ID is empty
        if (chatId.isEmpty()) {
            throw new IllegalStateException("The chat id couldn't be identified or found.");
        }

        // Set the chat ID for the response message
        sendMessage.setChatId(chatId);

        try {
            // Send the response message
            sendApiMethod(sendMessage);
        } catch (TelegramApiException e) {
            // Throw a runtime exception if there's an error sending the message
            throw new RuntimeException(e);
        }

    }
}
