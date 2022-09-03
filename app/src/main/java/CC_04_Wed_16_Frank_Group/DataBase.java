package CC_04_Wed_16_Frank_Group;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DataBase {
    HashMap<String, List<Currency>> currencies;

    public DataBase() {
        this.currencies = new HashMap<>();
        initialiseData();
    }

    public void initialiseData(){
        try {
            File file = new File("src/main/java/CC_04_Wed_16_Frank_Group/initialData.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String line;
            String date = br.readLine();
            ArrayList<Currency> curr = new ArrayList<>();


            while ((line = br.readLine()) != null) {
                String[] arrOfStr = line.split(" ");
                int i = 0;
                Currency currency = null;


                for (String a : arrOfStr) {
                    if (i == 0) {
                        currency = new Currency(a);
                        curr.add(currency);
                    } else {
                        String[] parser = a.split(":");
                        currency.addConversionRate(parser[0], Double.parseDouble(parser[1]));
                    }

                    i += 1;
                }
            }

            this.currencies.put(date, curr);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public HashMap<String, List<Currency>> getCurrencies() {
        return this.currencies;
    }

    public float convertCurrency(String inputCurrency, String outputCurrency, float amount) {
        
        // find string date that is most recent
        String date = "";
        for (String key : this.currencies.keySet()) {
            date = key;
        }

        // find input currency on date
        Currency inputCurr = null;
        for (Currency c : this.currencies.get(date)) {
            if (c.getName().equals(inputCurrency)) {
                inputCurr = c;
            }
        }

        // find conversion rate to output currency
        float conversionRate = 0;
        for (String key : inputCurr.getConversionRates().keySet()) {
            if (key.equals(outputCurrency)) {
                conversionRate = inputCurr.getConversionRates().get(key).floatValue();
            }
        }

        // return converted amount
        return amount * conversionRate;

    }




}
