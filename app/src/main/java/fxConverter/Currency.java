package fxConverter;

import java.util.HashMap;

public class Currency {
    String name;
    HashMap<String, Double> conversionRate;

    public Currency(String name) {
        this.name = name;
        this.conversionRate = new HashMap<>();
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

    public void setConversionRate(HashMap<String, Double> conversion) {
        this.conversionRate = conversion;
    }

}
