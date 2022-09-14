package CC_04_Wed_16_Frank_Group;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Scanner;

public class DataBase {

    HashMap<String, List<Currency>> currencies;

    public DataBase() {
        this.currencies = new HashMap<>();
        initialiseData();
    }

    public void initialiseData() {
        try {
            File file = new File("src/main/java/CC_04_Wed_16_Frank_Group/initialData.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            String date = "";
            ArrayList<Currency> curr = new ArrayList<>();

            while ((line = br.readLine()) != null) {

                if (line.contains("date")) {

                    if (curr.size() > 0 && !date.equals("")) {
                        this.currencies.put(date, curr);
                        date = line.substring(5);
                        curr = new ArrayList<>();
                    } else {
                        date = line.substring(5);
                        curr = new ArrayList<>();
                    }

                    continue;

                }

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

            // close file
            br.close();
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, List<Currency>> getCurrencies() {
        return this.currencies;
    }

    public List<String> getCurrencyNames() {

        List<String> currencyNames = new ArrayList<>();

        for (String key : this.currencies.keySet()) {

            for (Currency currency : this.currencies.get(key)) {

                if (!currencyNames.contains(currency.getName())) {
                    currencyNames.add(currency.getName());
                }
            }

        }

        return currencyNames;

    }

    public float convertCurrency(String inputCurrency, String outputCurrency, float amount) {

        // find string date that is most recent
        String mostRecentDateString = findMostRecentDate();

        // find input currency on date
        Currency inputCurr = null;
        for (Currency c : this.currencies.get(mostRecentDateString)) {
            if (c.getName().equals(inputCurrency)) {
                inputCurr = c;
            }
        }

        // find conversion rate to output currency
        if (inputCurr == null) {
            System.out.println("Cannot find currency\n");
            return -1;
        }

        float conversionRate = 0;
        for (String key : inputCurr.getConversionRates().keySet()) {
            if (key.equals(outputCurrency)) {
                conversionRate = inputCurr.getConversionRates().get(key).floatValue();
            }
        }

        // return converted amount
        return amount * conversionRate;

    }

    public void addCurrency(String date2Day, String currency2Add) {
        
        Scanner sc = new Scanner(System.in);
        // add to hashmap, which will then be fed into a txtfile
        // addCurrency method calls writeToFile (?)
        Currency currency = new Currency(currency2Add);
        List<Currency> currenciesList = new ArrayList<>();

        // find most recent date
        String mostRecentCurrData = this.findMostRecentDate(); 

        for (Currency c : this.currencies.get(mostRecentCurrData)) {
            // for (Currency j : i) {
                
            System.out.println("What is the conversion rate from " + currency2Add + " to " + c.getName() + "?");
            Double XRrate = Double.parseDouble(sc.next());
            // if inputted currency already exists, no not allow
            currency.addConversionRate(c.getName(), XRrate);

                // String newCurrencyXR = sc.next();
            System.out.println(currency.getConversionRates());
            currenciesList.add(c); 
            // reference went to lines ~223 currenciesList.add(this.currency)

            // adding new currency's to every every other currency's conversion rate (inverse)
            c.addConversionRate(currency2Add, Math.pow(XRrate, -1));
        }

        
        currenciesList.add(currency);
        this.currencies.put(date2Day, currenciesList);

        // System.out.println(this.currencies);
        // sc.close();

        writeToFile();

        //TODO
        // Math.pow(currency, -1) rounding
        
    }

    public String findMostRecentDate() {

        Date mostRecentDate = new Date(0);
        String mostRecentDateString = "";
        for (String key : this.currencies.keySet()) {
            Date date = new Date(key);
            if (date.after(mostRecentDate)) {
                mostRecentDate = date;
                mostRecentDateString = key;
            }
        }

        return mostRecentDateString;

    }

    // needed for comparisons in table
    public String findSecondMostRecentDate() {

        Date mostRecentDate = new Date(0);
        String mostRecentDateString = "";
        for (String key : this.currencies.keySet()) {
            Date date = new Date(key);
            if (date.after(mostRecentDate)) {
                mostRecentDate = date;
                mostRecentDateString = key;
            }
        }

        Date secondMostRecentDate = new Date(0);
        String secondMostRecentDateString = "";
        for (String key : this.currencies.keySet()) {
            Date date = new Date(key);
            if (date.after(secondMostRecentDate) && !date.equals(mostRecentDate)) {
                secondMostRecentDate = date;
                secondMostRecentDateString = key;
            }
        }

        return secondMostRecentDateString;

    }

    public void writeToFile() {

        try {
            File file = new File("src/main/java/CC_04_Wed_16_Frank_Group/testData.txt");

            //detele file if it exists
            if (file.exists()) {
                file.delete();
            }
            //create new file with the same name
            file.createNewFile();

            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            
            for (String key : this.currencies.keySet()) {
                br.write("date:" + key + "\n");
                for (Currency c : this.currencies.get(key)) {
                    br.write(c.getName() + " ");
                    for (String key2 : c.getConversionRates().keySet()) {
                        br.write(key2 + ":" + c.getConversionRates().get(key2) + " ");
                    }
                    br.write("\n");
                }
            }

            // close file
            br.close();
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}