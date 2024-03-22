package projekt.telegram.command;

import lombok.NonNull;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import projekt.telegram.BotConnection;
import projekt.telegram.Setting.Currency;
import projekt.telegram.Setting.Setting;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

public class KeyboardActions extends BotConnection {
    public void sendStandardKeyboard (Update update) {
        System.out.println("\"sendStandardKeyboard\" = " + "sendStandardKeyboard");
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        SendMessage answer = new SendMessage();

        answer.setText("TEXT");
        answer.setChatId(Long.toString(chatId));

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        rowInline1.add(InlineKeyboardButton.builder().text("INFO").callbackData("INFO").build());

        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
        rowInline2.add(InlineKeyboardButton.builder().text("SETTINGS").callbackData("SETTINGS").build());
        rowsInline.add(rowInline1);
        rowsInline.add(rowInline2);

        markupInline.setKeyboard(rowsInline);
        answer.setReplyMarkup(markupInline);

        try {
            execute(answer);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
 }

    public void sendGetCurrency(Update update, Setting setting) {
        System.out.println("\"sendGetCurrency\" = " + "sendGetCurrency");
        long chatId = setting.getChatId();

        SendMessage answer = new SendMessage();

        answer.setText("TEXT KURS");
        answer.setChatId(Long.toString(chatId));
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendSettingsMenu(Update update) {
        System.out.println("\"sendSettingsMenu\" = " + "sendSettingsMenu");
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        SendMessage answer = new SendMessage();

        answer.setText("SETTINGS");
        answer.setChatId(Long.toString(chatId));


        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text("ROUNDED_INDEX").callbackData("4").build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text("BANK").callbackData("7").build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text("CURRENCY").callbackData("15").build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text("REMINDER_TIME").callbackData("23").build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text("HOME").callbackData("HOME").build()
        ));

        InlineKeyboardMarkup keyboard = InlineKeyboardMarkup.builder().keyboard(buttons).build();
        keyboard.setKeyboard(buttons);
        answer.setReplyMarkup(keyboard);

        try {
            execute(answer);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendBankMenu(Update update, Setting setting) {
                                     System.out.println("\"sendBankMenu\" = " + "sendBankMenu");
                                     System.out.println("setting.getBank() = " + setting.getBank());

        CallbackQuery callbackquery = update.getCallbackQuery();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        EditMessageText editMarkup = new EditMessageText();

        editMarkup.setChatId(Long.toString(chatId));

        editMarkup.setInlineMessageId(callbackquery.getInlineMessageId());
        editMarkup.setText("TEXT BANK");
        editMarkup.enableMarkdown(true);
        editMarkup.setMessageId(callbackquery.getMessage().getMessageId());

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline4 = new ArrayList<>();
        InlineKeyboardButton but1 = new InlineKeyboardButton();
        but1.setText("\u2705" + "PB"); but1.setCallbackData("PB");


        InlineKeyboardButton but2 = new InlineKeyboardButton("MB");
        but2.setCallbackData("MB");

        if (setting.getBank().equals("MB")) {
            but2.setText("\u2705" + "MB");
        } else {
            but2.setText("MB");
        }
        InlineKeyboardButton but3 = new InlineKeyboardButton("NBU");
        but3.setCallbackData("NBU");

        if (setting.getBank().equals("NBU")) {
            but3.setText("\u2705" + "NBU");
        } else {
            but3.setText("NBU");
        }
        InlineKeyboardButton but4 = new InlineKeyboardButton("BACK");
        but4.setCallbackData("SETTINGS");
        rowInline1.add(but1);
        rowInline2.add(but2);
        rowInline3.add(but3);
        rowInline4.add(but4);


        rowsInline.add(rowInline1);
        rowsInline.add(rowInline2);
        rowsInline.add(rowInline3);
        rowsInline.add(rowInline4);


        markupInline.setKeyboard(rowsInline);

        editMarkup.setReplyMarkup(markupInline);
        try {
            execute(editMarkup);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void sendRoundedIndexMenu(Update update, Setting setting) {
        System.out.println("\"sendCountSignMenu\" = " + "sendCountSignMenu");
        int roundedIndex =  setting.getRoundDigit();
                                                             System.out.println("roundedIndex = " + roundedIndex);
        CallbackQuery callbackquery = update.getCallbackQuery();
        long chatId = update.getCallbackQuery().getMessage().getChatId();

        EditMessageText editMarkup = new EditMessageText();

        editMarkup.setChatId(Long.toString(chatId));

        editMarkup.setInlineMessageId(callbackquery.getInlineMessageId());
        editMarkup.setText("TEXT RoundedIndex");
        editMarkup.enableMarkdown(true);
        editMarkup.setMessageId(callbackquery.getMessage().getMessageId());

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInlineFirst = new ArrayList<>();
        List<InlineKeyboardButton> rowInlineSecond = new ArrayList<>();
        List<InlineKeyboardButton> rowInlineThree = new ArrayList<>();
        List<InlineKeyboardButton> rowInlineFour = new ArrayList<>();
        InlineKeyboardButton but1 = new InlineKeyboardButton();
        but1.setCallbackData("2");
        if (roundedIndex == 2) {
            but1.setText("\u2705 2");
        } else {
            but1.setText("2");
        }

        InlineKeyboardButton but2 = new InlineKeyboardButton("3");
        but2.setCallbackData("3");

        if (roundedIndex == 3) {
            but2.setText("\u2705 3");
        } else {
            but2.setText("3");
        }
        InlineKeyboardButton but3 = new InlineKeyboardButton("4");
        but3.setCallbackData("4");

        if (roundedIndex == 4) {
            but3.setText("\u2705 4");
        } else {
            but3.setText("4");
        }
        InlineKeyboardButton but4 = new InlineKeyboardButton("BACK");
        but4.setCallbackData("SETTINGS");
        rowInlineFirst.add(but1);
        rowInlineSecond.add(but2);
        rowInlineThree.add(but3);
        rowInlineFour.add(but4);


        rowsInline.add(rowInlineFirst);
        rowsInline.add(rowInlineSecond);
        rowsInline.add(rowInlineThree);
        rowsInline.add(rowInlineFour);


        markupInline.setKeyboard(rowsInline);

        editMarkup.setReplyMarkup(markupInline);
        try {
            execute(editMarkup);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void setCurrency(Update update, Setting setting) {
        System.out.println("\"setCurrency\" = " + "setCurrency");
        System.out.println("setting = " + setting);
        CallbackQuery callbackquery = update.getCallbackQuery();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        EditMessageText editMarkup = new EditMessageText();
        editMarkup.setChatId(Long.toString(chatId));

//        System.out.println("111111111111111setting.getCurrencies() = " + setting.getCurrencies());
//        System.out.println("setting.getCurrencies().equals(Currency.USD) = " + setting.getCurrencies().get(0).equals(Currency.USD));
//        System.out.println("setting.getCurrencies().get(0).equals(Currency.EUR) = " + setting.getCurrencies().get(1).equals(Currency.EUR));

//        String currency = setting.getCurrencies().toString();
//       System.out.println("currency = " + currency);
        editMarkup.setInlineMessageId(callbackquery.getInlineMessageId());
        editMarkup.setText("TEXT CURRENCY");
        editMarkup.enableMarkdown(true);
        editMarkup.setMessageId(callbackquery.getMessage().getMessageId());

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInlineFirst = new ArrayList<>();
        List<InlineKeyboardButton> rowInlineSecond = new ArrayList<>();
        List<InlineKeyboardButton> rowInlineThree = new ArrayList<>();
        InlineKeyboardButton but1 = new InlineKeyboardButton();
        but1.setCallbackData("USD");
        if (true) {
            but1.setText("\u2705 USD");
        } else {
            but1.setText("USD");
        }

        InlineKeyboardButton but2 = new InlineKeyboardButton("EUR");
        but2.setCallbackData("EUR");

        if (true) {
            but2.setText("\u2705 EUR");
        } else {
            but2.setText("EUR");
        }
        InlineKeyboardButton but3 = new InlineKeyboardButton("BACK");
        but3.setCallbackData("SETTINGS");
        rowInlineFirst.add(but1);
        rowInlineSecond.add(but2);
        rowInlineThree.add(but3);

        rowsInline.add(rowInlineFirst);
        rowsInline.add(rowInlineSecond);
        rowsInline.add(rowInlineThree);

        markupInline.setKeyboard(rowsInline);

        editMarkup.setReplyMarkup(markupInline);
        try {
            execute(editMarkup);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
