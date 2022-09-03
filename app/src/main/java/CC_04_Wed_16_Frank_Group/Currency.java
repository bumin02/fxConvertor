package CC_04_Wed_16_Frank_Group;

import java.util.HashMap;

public class Currency {
    String name;
    HashMap<String, Double> conversionRate;

    public Currency(String name) {
        this.name = name;
        conversionRate = new HashMap<>();
    }

    public void addConversionRate(String name, Double rate) {
        this.conversionRate.put(name, rate);
    }

    public HashMap<String, Double> getConversionRates() {
        return this.conversionRate;
    }

    public String getName() {
        return this.name;

    }
}
