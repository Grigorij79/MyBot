package projekt.telegram.Setting;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Setting {


    private long chatId;
    private int roundDigit;
    private Bank bank;
    private int reminderTime;
    private List<Currency> currencies;

    public Setting(long chatId) {
        this.chatId = chatId;
    }


    public Setting(long chatId, int roundDigit, Bank bank, int reminderTime, List<Currency> currencies) {
        this.chatId = chatId;
        this.roundDigit = roundDigit;
        this.bank = bank;
        this.reminderTime = reminderTime;
        this.currencies = currencies;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public void setRoundDigit(int roundDigit) {
        this.roundDigit = roundDigit;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setReminderTime(int reminderTime) {
        this.reminderTime = reminderTime;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public long getChatId() {
        return chatId;
    }

    public Bank getBank() {
        return bank;
    }

    public int getRoundDigit() {
        return roundDigit;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public static Setting getDefaultSettings(long chatId){

        Setting defaultSetting = new Setting(chatId);
        defaultSetting.setBank(new PrivatBankCurrencyServise());
        defaultSetting.setRoundDigit(2);
        List<Currency> currencyList = new ArrayList<>();

        currencyList.add(Currency.USD);
        defaultSetting.setCurrencies(currencyList);
        defaultSetting.setReminderTime(13);
        return defaultSetting;

    }


}
