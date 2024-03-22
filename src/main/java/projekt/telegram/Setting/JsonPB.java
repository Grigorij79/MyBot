package projekt.telegram.Setting;

import lombok.Data;

@Data
public class JsonPB {
    private Currency ccy;
    private Currency base_ccy;
    private float buy;
    private float sale;

    public Currency getCcy() {
        return ccy;
    }

    public Currency getBase_ccy() {
        return base_ccy;
    }

    public float getBuy() {
        return buy;
    }

    public float getSale() {
        return sale;
    }
}
