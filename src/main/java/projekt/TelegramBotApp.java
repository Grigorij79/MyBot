package projekt;

import projekt.telegram.BotInitialization;
import projekt.telegram.Setting.Setting;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import static projekt.telegram.BotConstans.MYCHATID;


public class TelegramBotApp {
//    private static Map<Integer, List<Setting>> listener;
//    private static List<Setting> settings;
//    public  TelegramBotApp(Map<Integer, List<Setting>> listener) {
//        settings = new ArrayList<>();
//        listener = new ConcurrentHashMap<>();
//    }

    public static void main(String[] args) throws IOException, InterruptedException {
        BotInitialization botService = new BotInitialization();
//        listener.put(14 ,settings.add(Setting.getDefaultSettings(MYCHATID)));
//        listener.put(15, Setting.getDefaultSettings(MYCHATID));
        //       System.out.println("Setting.getDefaultSettings() = " + Setting.getDefaultSettings(MYCHATID));
        Metod1 metod1 = new Metod1();
        Thread thread1 = new Thread(metod1);
        thread1.start();
    }
    }
class Metod1 implements Runnable {
    Date minet = new Date();
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Date minet = new Date();
            System.out.println("Seconds = " + minet.getSeconds());
        }
    }
}