package projekt.telegram;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class BotConstans {
    public static final String BOT_NAME = "ExampleCurrencybot";

    public static final String BOT_TOKEN = "6731462935:AAGGeAfvJynh0QfJBLEGCToOztBoe1ylQHA";
    public static final Long MYCHATID  = 5153382627L;
    public static final List<String> TIME = List.of("15","25","35","12","13","14","45","0","55");
    public static final String PRIVAT_URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
    public static final String MONO_URL = "https://api.monobank.ua/bank/currency";
    public static final String NBU_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

}
