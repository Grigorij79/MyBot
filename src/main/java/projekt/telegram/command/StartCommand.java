package projekt.telegram.command;

import lombok.SneakyThrows;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.nio.charset.StandardCharsets;
import java.util.*;




public class StartCommand extends BotCommand {

    public StartCommand() {
        super("start", "Start command");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        StringBuilder messageBuilder = new StringBuilder();
        String userName = user.getFirstName();
        messageBuilder.append(new String("Hello  ".getBytes(), StandardCharsets.UTF_8))
                .append(userName).append("\n")
                .append(new String(("Laskavo prosimo").getBytes(), StandardCharsets.UTF_8));
        SendMessage answer = new SendMessage();

        answer.setText(messageBuilder.toString());
        answer.setChatId(chat.getId().toString());
        System.out.println("chat.getId().toString() = " + chat.getId().toString());


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
                absSender.execute(answer);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }

    }
}


