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
import java.util.*;
import java.util.concurrent.*;

import static projekt.telegram.BotConstans.MYCHATID;


public class BotConnection extends TelegramLongPollingCommandBot {

    //==============================================================================


    // ***** 1*****     private ScheduledExecutorService executorService;

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public BotConnection() {
       // **** 2**** executorService = Executors.newSingleThreadScheduledExecutor();

        register(new StartCommand());
        register(new HelpCommand());
    }
    Map<Long, String> users = new HashMap<>();
    boolean flag1 = true;

    @Override
    public void processNonCommandUpdate(Update update) {



//        String user_first_name = update.getMessage().getChat().getLastName();
//        String user_last_name = update.getMessage().getChat().getLastName();
//        String user_username = update.getMessage().getChat().getUserName();
//        System.out.println("user_first_name = " + user_first_name);
//        System.out.println("user_username = " + user_username);
//        System.out.println("user_last_name = " + user_last_name);
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        System.out.println("chatId = " + chatId);

        if (BotConstans.TIME.contains(update.getCallbackQuery().getData())) {
            //if (!users.containsKey(chatId)) {
                users.put(chatId, update.getCallbackQuery().getData());
            //}
            System.out.println("users = " + users);
        }


//        Date minet = new Date();
//        System.out.println("minet.getSeconds()_START = " + minet.getSeconds());
        if (update.hasCallbackQuery()) {

            String callBackQuery = update.getCallbackQuery().getData();
                                                        System.out.println("callBackQuery = " + callBackQuery);


                                                            System.out.println("chatId = " + chatId);
            KeyboardActions keyboardActions = new KeyboardActions();

            Setting setting = Setting.getDefaultSettings(MYCHATID);

//            executorService.schedule(() ->{
//                keyboardActions.sendGetCurrency(update, setting);
//                    keyboardActions.sendStandardKeyboard(update);}
//            , 20L, TimeUnit.SECONDS );
//            Long start = 20L;


            if (callBackQuery.equals("INFO")){
                keyboardActions.sendGetCurrency(update, setting);
                keyboardActions.sendStandardKeyboard(update);
            } else   if (callBackQuery.equals("HOME")){
                    keyboardActions.sendStandardKeyboard(update);
                } else if (callBackQuery.equals("SETTINGS")){
                keyboardActions.sendSettingsMenu(update);
            } else if (callBackQuery.equals("BANK")){
                                                                   // System.out.println("setting2 = " + setting);
                keyboardActions.sendBankMenu(update, setting);
            } else if (callBackQuery.equals("ROUNDED_INDEX")){
                keyboardActions.sendRoundedIndexMenu(update,setting);
            } else if ( callBackQuery.equals("CURRENCY")  ) {


                keyboardActions.setCurrency(update, setting);
            }else if (callBackQuery.equals("USD")){
                keyboardActions.setCurrency(update, setting);
            }

        }
        //****************************executorServiceGeneralL****************************************
        //Map<Long, String> users = new HashMap<>();
        if (flag1) {
            ScheduledExecutorService executorServiceGeneral = Executors.newSingleThreadScheduledExecutor();
            Date minet = new Date();
            executorServiceGeneral.scheduleAtFixedRate(() -> {
                        System.out.println("General.scheduleAtFixedRate(() -> {" + minet.getSeconds());
                        System.out.println("users = " + users);
                        for (Long key : users.keySet()) {
                            long time = Long.parseLong(users.get(key));
                            long start = 0;
                            if (time != 0) {
                                if (minet.getSeconds() < time) {
                                    start = (time - minet.getSeconds());
                                } else {
                                    start = (time + (60 - minet.getSeconds()));
                                }

                                System.out.println("time = users.get(key);" + time);
                                System.out.println("key = " + key);
                                System.out.println("minet.getSeconds() = " + minet.getSeconds());
                                ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                                executorService.schedule(() -> {
                                    System.out.println("\"TEXT MASSAGE\" = " + "TEXT MASSAGE" + minet.getSeconds());

                                    SendMessage answer = new SendMessage();
                                    answer.setText("TEXT MASSAGE" + minet.getSeconds());
                                    answer.setChatId(key);
                                    try {
                                        execute(answer);
                                    } catch (TelegramApiException e) {
                                        throw new RuntimeException(e);
                                    }
                                }, start, TimeUnit.SECONDS);
                                System.out.println("minet.getSeconds()333 = " + minet.getSeconds());
                            }
                        }
                    }
                    , 0, 60, TimeUnit.SECONDS);
            flag1 = false;
        }
    }




//    public  void executeOnSchedule (long start, Long chatId){
//        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
//        executorService.scheduleAtFixedRate(() ->{
//            handleReminderCallback(chatId);
//        }, start, 0, TimeUnit.SECONDS);
//    }
    private void handleReminderCallback(Long chatId){
        SendMessage answer = new SendMessage();
        answer.setText("TEXT MASSAGE");
        answer.setChatId(chatId);
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
        if (minet.getSeconds() < times){
            start = (times - minet.getSeconds());
        } else {
            start = (times + (60- minet.getSeconds()));

        }
        return start;
    }



        @Override
        public String getBotUsername () {
            return BotConstans.BOT_NAME;
        }
        @Override
        public String getBotToken () {
            return BotConstans.BOT_TOKEN;
        }


}

