package projekt.telegram.Setting;

public interface BankService {

    double getBuyRate(Currency currency);
    double getSellRate(Currency currency);
}
