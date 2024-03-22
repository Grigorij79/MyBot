package projekt.telegram.Setting;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Bank {
    private String name;

    public abstract double getBuyRate(Currency currency);
    public abstract double getSellRate(Currency currency);
}