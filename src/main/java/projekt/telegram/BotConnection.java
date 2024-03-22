package projekt.telegram;


import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import projekt.telegram.Setting.Setting;
import projekt.telegram.command.HelpCommand;
import projekt.telegram.command.KeyboardActions;
import projekt.telegram.command.StartCommand;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.*;

import static projekt.telegram.BotConstans.MYCHATID;


public class BotConnection extends TelegramLongPollingCommandBot {

    //==============================================================================
    public  void executeOnSchedule (long start){
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::handleReminderCallback, start, 24, TimeUnit.SECONDS);
    }

    private void handleReminderCallback(){
        SendMessage answer = new SendMessage();
//========================================================================
        Date minet = new Date();
        System.out.println("minet.getSeconds() = " + minet.getSeconds());
 //===========================================================================
        answer.setText("TEXT MASSAGE");
        answer.setChatId(MYCHATID);
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    public long setTimeStart (String time){
        long times = Long.parseLong(time);
        long start = 0;
        Date minet = new Date();
        System.out.println("times = " + times);
        System.out.println("minet.getHours() = " + minet.getHours());
        System.out.println("minet.getTime() = " + minet.getMinutes());
        System.out.println("minet.getSeconds() = " + minet.getSeconds());
        if (minet.getHours() < times){
            start = (times - minet.getHours()) * 1;
          // start = (times - minet.getHours()) *60 *60 - minet.getMinutes() * 60 - minet.getSeconds();
        } else {
            start = (times + (24- minet.getHours())) * 1;
            //start = (times * 60 * 60 + ((24- minet.getHours()) *60 *60 - minet.getMinutes()*60 - minet.getSeconds()));
        }
    return start;
    }

    // ***** 1*****     private ScheduledExecutorService executorService;

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public BotConnection() {
       // **** 2**** executorService = Executors.newSingleThreadScheduledExecutor();

        register(new StartCommand());
        register(new HelpCommand());
    }

    @Override
    public void processNonCommandUpdate(Update update) {

        if (update.hasCallbackQuery()) {

            String callBackQuery = update.getCallbackQuery().getData();
                                                        System.out.println("callBackQuery = " + callBackQuery);

            Long chatId = update.getCallbackQuery().getMessage().getChatId();
                                                            System.out.println("chatId = " + chatId);
            KeyboardActions keyboardActions = new KeyboardActions();

            Setting setting = Setting.getDefaultSettings(MYCHATID);

//            executorService.schedule(() ->{
//                keyboardActions.sendGetCurrency(update, setting);
//                    keyboardActions.sendStandardKeyboard(update);}
//            , 20L, TimeUnit.SECONDS );
//            Long start = 20L;
            if (callBackQuery.equals("4") ){
            String time = update.getCallbackQuery().getData();
            System.out.println("time = " + time);
            executeOnSchedule(setTimeStart(time));}

            if (callBackQuery.equals("INFO")){
                keyboardActions.sendGetCurrency(update, setting);
                keyboardActions.sendStandardKeyboard(update);
            } else   if (callBackQuery.equals("HOME")){
                    keyboardActions.sendStandardKeyboard(update);
                } else if (callBackQuery.equals("SETTINGS")){
                keyboardActions.sendSettingsMenu(update);
            } else if (callBackQuery.equals("BANK")){
                                                                    System.out.println("setting2 = " + setting);
                keyboardActions.sendBankMenu(update, setting);
            } else if (callBackQuery.equals("ROUNDED_INDEX")){
                keyboardActions.sendRoundedIndexMenu(update,setting);
            } else if ( callBackQuery.equals("CURRENCY")  ) {


                keyboardActions.setCurrency(update, setting);
            }else if (callBackQuery.equals("USD")){
                keyboardActions.setCurrency(update, setting);
            }

        }
//==============================================================================
            String time = update.getCallbackQuery().getData();
            System.out.println("time = " + time);
            executeOnSchedule(setTimeStart(time));


        //==========================================================================================
        if (update.hasMessage()) {
            String message = update.getMessage().getText();
            String responseText = new String(("Ви напиcали:" + message).getBytes(), StandardCharsets.UTF_8);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText(responseText);
            sendMessage.setChatId(Long.toString(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }

    }
        @Override
        public String getBotUsername () {
            return BotConstans.BOT_NAME;
        }
        @Override
        public String getBotToken () {
            return BotConstans.BOT_TOKEN;
        }


        //**************************************************************************************************


//*******************************************************************************************************

}

