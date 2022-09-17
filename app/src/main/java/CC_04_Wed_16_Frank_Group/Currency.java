package CC_04_Wed_16_Frank_Group;

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

    public void removeConversionRate(String name) {
        this.conversionRate.remove(name);
    }

    public String getName() {
        return this.name;
    }

    public void setConversionRate(HashMap<String, Double> conversion) {
        this.conversionRate = conversion;
    }

}
