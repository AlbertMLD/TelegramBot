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

        String response = "I'm sorry, I haven't understood the message you sent";

        String chatId = "";

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(response);

        if (update.hasCallbackQuery() && update.getCallbackQuery().getData() != null && !update.getCallbackQuery().getData().isEmpty()) {
            chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());

            String callBackData = update.getCallbackQuery().getData();

            if (callBackData.equalsIgnoreCase("/YES")) {
                LocalDateTime currentTime = LocalDateTime.now();
                sendMessage.setText(currentTime.toString());
            }
        }

        if (update.hasMessage() && update.getMessage().hasText()) {
            String userMessage = update.getMessage().getText().trim();

            if (userMessage.equalsIgnoreCase("Hello")){
                sendMessage.setText("How are you?");
            }

            if (userMessage.equalsIgnoreCase("How are you?")) {
                sendMessage.setText("I'm fine thank you!");
            }

            if (userMessage.contains("time")) {

                sendMessage.setText("Would you like to know the current time?");

                // First create the keyboard
                List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

                // Then we create a button row
                List<InlineKeyboardButton> buttonsRow = new ArrayList<>();

                // Create yes button
                InlineKeyboardButton yesButton = new InlineKeyboardButton();
                yesButton.setText("Yes?");
                yesButton.setCallbackData("/YES");

                // We add the yes button to the button row
                buttonsRow.add(yesButton);

                // We add the newly created button row that contains the yes button to the keyboard
                keyboard.add(buttonsRow);

                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                inlineKeyboardMarkup.setKeyboard(keyboard);

                sendMessage.setReplyMarkup(inlineKeyboardMarkup);

            }

        }

        if (chatId.isEmpty()) {
            throw new IllegalStateException("The chat id couldn't be identified of found.");
        }

        sendMessage.setChatId(chatId);

        try {
            sendApiMethod(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

}