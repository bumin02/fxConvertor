package fxConverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Collections;
import java.lang.Math;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Scanner;

public class DataBase {

    HashMap<String, List<Currency>> currencies;

    public DataBase() {
        this.currencies = new HashMap<>();
    }

    public void initialiseData(String path) {
        try {
            File file = new File(path);
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

    public List<String> findPopularCurrencies() {

        try {

            // open popular.txt
            File file2 = new File("src/main/java/fxConverter/popular.txt");
            FileReader fr2 = new FileReader(file2);
            BufferedReader br2 = new BufferedReader(fr2);
            String line2;

            List<String> popularCurrencies = new ArrayList<>();

            while ((line2 = br2.readLine()) != null) {
                String[] arrOfStr = line2.split(",");
                for (String a : arrOfStr) {
                    popularCurrencies.add(a);
                }
            }

            // close
            br2.close();
            fr2.close();

            return popularCurrencies;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    public void updatePopularCurrencies(List<String> currencies) {

        try {

            // clear popular.txt
            PrintWriter writer = new PrintWriter("src/main/java/fxConverter/popular.txt");
            writer.print("");

            // write new popular currencies to popular.txt
            for (String currency : currencies) {
                writer.print(currency + ",");
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean fileExists(String path) {
        File f = new File(path);
        if (f.exists() && !f.isDirectory()) {
            return true;
        }
        return false;
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

    public String findMostRecentDate() {

        // function to find the latest date
        // in the form of DD/MM/YY

        String mostRecentDateString = "";
        Date mostRecentDate = new Date(0);

        for (String key : this.currencies.keySet()) {

            Date date = null;
            try {
                date = new SimpleDateFormat("dd/MM/yy").parse(key);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (date.after(mostRecentDate)) {
                mostRecentDate = date;
                mostRecentDateString = key;
            }

        }

        return mostRecentDateString;

    }

    // needed for comparisons in table
    public String findSecondMostRecentDate() {

        // function to find the latest date
        // in the form of DD/MM/YY

        String mostRecentDateString = "";
        Date mostRecentDate = new Date(0);
        String secondMostRecentDateString = "";
        Date secondMostRecentDate = new Date(0);

        for (String key : this.currencies.keySet()) {

            Date date = null;
            try {
                date = new SimpleDateFormat("dd/MM/yy").parse(key);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (date.after(mostRecentDate)) {
                secondMostRecentDate = mostRecentDate;
                secondMostRecentDateString = mostRecentDateString;
                mostRecentDate = date;
                mostRecentDateString = key;
            } else if (date.after(secondMostRecentDate)) {
                secondMostRecentDate = date;
                secondMostRecentDateString = key;
            }

        }

        return secondMostRecentDateString;

    }

    public Currency findCurrency(String name, List<Currency> ls) {
        for (Currency c : ls) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    // update the rate of currency1 in currency2's hashmap
    // please check the format of date prior to this method
    // return 1 if successful
    // 0 if input error, particularly null data
    // 2 if no currency is found
    // 3 if currency1 not found
    // 4 if the date already exists
    public int updateCurrency(String date, String currency1, String currency2, Double newRate1) {
        // check all input not null
        if (date == null || currency2 == null || currency1 == null || newRate1 == null) {
            return 0;
        }

        // check if date already exists
        if (this.currencies.containsKey(date)) {
            return 4;
        }

        String latest = findMostRecentDate();
        List<Currency> cloned = new ArrayList<>();

        // change the rate
        Currency curr2 = findCurrency(currency2, this.currencies.get(latest));
        if (curr2 == null) {
            return 2;
        }

        // update data
        if (!curr2.getConversionRates().containsKey(currency1)) {
            return 3;
        }

        // create new currency and add to list
        Currency c = new Currency(currency2);
        cloned.add(c);

        for (Currency i : this.currencies.get(latest)) {
            if (i != curr2) {
                cloned.add(i);
            }
        }

        // update value in the new currency object
        for (String i : curr2.getConversionRates().keySet()) {
            if (!i.equals(currency1)) {
                c.getConversionRates().put(i, curr2.getConversionRates().get(i));
            }
        }
        c.getConversionRates().put(currency1, newRate1);
        this.currencies.put(date, cloned);
        return 1;
    }

    public void writeToFile() {

        try {

            File file = new File("src/main/java/fxConverter/initialData.txt");

            // detele file if it exists
            if (file.exists()) {
                file.delete();
            }
            // create new file with the same name
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

    public static double calculateSD(List<Double> allRates) {
        double sum = 0.0, standardDeviation = 0.0;
        int length = allRates.size();

        for (double num : allRates) {
            sum += num;
        }

        double mean = sum / length;

        for (double num : allRates) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        return Math.sqrt(standardDeviation / length);
    }

    public double getSD(String dateA, String dateB, String currA, String currB) {
        List<Double> allRates = new ArrayList<Double>();
        for (String eachDate : this.currencies.keySet()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(("dd/MM/yy"));
            Date dateADate = null;
            Date dateBDate = null;
            Date eachDateDate = null;

            try {
                dateADate = dateFormat.parse(dateA);
                dateBDate = dateFormat.parse(dateB);
                eachDateDate = dateFormat.parse(eachDate);
            } catch (Exception e) {
                return 0;
            }

            if ((eachDate.equals(dateA) || eachDateDate.compareTo(dateADate) > 0)
                    && (eachDate.equals(dateB) || eachDateDate.compareTo(dateBDate) < 0)) {
                for (Currency curr : this.currencies.get(eachDate)) {
                    if (curr.getName().equals(currA.toUpperCase())) {
                        if (curr.getConversionRates().get(currB.toUpperCase()) == null) {
                            continue;
                        }
                        Double value = curr.getConversionRates().get(currB.toUpperCase());
                        allRates.add(value);
                    }
                }
            }
        }
        double standardDeviation = calculateSD(allRates);
        return standardDeviation;
    }

    public double getMean(String dateA, String dateB, String currA, String currB) {
        double total = 0;
        int count = 0;
        for (String eachDate : this.currencies.keySet()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(("dd/MM/yy"));
            Date dateADate = null;
            Date dateBDate = null;
            Date eachDateDate = null;

            try {
                dateADate = dateFormat.parse(dateA);
                dateBDate = dateFormat.parse(dateB);
                eachDateDate = dateFormat.parse(eachDate);
            } catch (Exception e) {
                return 0;
            }

            if ((eachDate.equals(dateA) || eachDateDate.compareTo(dateADate) > 0)
                    && (eachDate.equals(dateB) || eachDateDate.compareTo(dateBDate) < 0)) {
                for (Currency curr : this.currencies.get(eachDate)) {
                    if (curr.getName().equals(currA.toUpperCase())) {
                        if (curr.getConversionRates().get(currB.toUpperCase()) == null) {
                            continue;
                        }
                        Double value = curr.getConversionRates().get(currB.toUpperCase());
                        count += 1;
                        total += value;
                    }
                }
            }
        }
        return total / count;
    }

    public double getMedian(String dateA, String dateB, String currA, String currB) {
        List<Double> allRates = new ArrayList<Double>();
        for (String eachDate : this.currencies.keySet()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(("dd/MM/yy"));
            Date dateADate = null;
            Date dateBDate = null;
            Date eachDateDate = null;

            try {
                dateADate = dateFormat.parse(dateA);
                dateBDate = dateFormat.parse(dateB);
                eachDateDate = dateFormat.parse(eachDate);
            } catch (Exception e) {
                return 0;
            }

            if ((eachDate.equals(dateA) || eachDateDate.compareTo(dateADate) > 0)
                    && (eachDate.equals(dateB) || eachDateDate.compareTo(dateBDate) < 0)) {
                for (Currency curr : this.currencies.get(eachDate)) {
                    if (curr.getName().equals(currA.toUpperCase())) {
                        if (curr.getConversionRates().get(currB.toUpperCase()) == null) {
                            continue;
                        }
                        Double value = curr.getConversionRates().get(currB.toUpperCase());
                        allRates.add(value);
                    }
                }
            }
        }
        int listSize = allRates.size();
        Collections.sort(allRates);
        if (listSize % 2 != 0) {
            int index = (int) Math.ceil(listSize / 2);
            return allRates.get(index);
        }
        int index1 = (int) Math.ceil(listSize / 2);
        int index2 = (int) Math.floor(listSize / 2);
        double value1 = allRates.get(index1);
        double value2 = allRates.get(index2);
        return (value1 + value2) / 2;
    }

    public double getMax(String dateA, String dateB, String currA, String currB) {
        double maxAtoB = 0.000001;

        for (String eachDate : this.currencies.keySet()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(("dd/MM/yy"));
            Date dateADate = null;
            Date dateBDate = null;
            Date eachDateDate = null;

            try {
                dateADate = dateFormat.parse(dateA);
                dateBDate = dateFormat.parse(dateB);
                eachDateDate = dateFormat.parse(eachDate);
            } catch (Exception e) {
                return 0;
            }

            if ((eachDate.equals(dateA) || eachDateDate.compareTo(dateADate) > 0)
                    && (eachDate.equals(dateB) || eachDateDate.compareTo(dateBDate) < 0)) {
                for (Currency curr : this.currencies.get(eachDate)) {
                    if (curr.getName().equals(currA.toUpperCase())) {
                        if (curr.getConversionRates().get(currB.toUpperCase()) == null) {
                            continue;
                        }
                        Double value = curr.getConversionRates().get(currB.toUpperCase());
                        if (value > maxAtoB) {
                            maxAtoB = value;
                        }
                    }
                }
            }
        }
        return maxAtoB;
    }

    public double getMin(String dateA, String dateB, String currA, String currB) {

        double minAtoB = 10000000;

        for (String eachDate : this.currencies.keySet()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(("dd/MM/yy"));
            Date dateADate = null;
            Date dateBDate = null;
            Date eachDateDate = null;

            try {
                dateADate = dateFormat.parse(dateA);
                dateBDate = dateFormat.parse(dateB);
                eachDateDate = dateFormat.parse(eachDate);
            } catch (Exception e) {
                return 0;
            }

            if ((eachDate.equals(dateA) || eachDateDate.compareTo(dateADate) > 0)
                    && (eachDate.equals(dateB) || eachDateDate.compareTo(dateBDate) < 0)) {
                for (Currency curr : this.currencies.get(eachDate)) {
                    if (curr.getName().equals(currA.toUpperCase())) {
                        if (curr.getConversionRates().get(currB.toUpperCase()) == null) {
                            continue;
                        }
                        Double value = curr.getConversionRates().get(currB.toUpperCase());
                        if (value < minAtoB) {
                            minAtoB = value;
                        }
                    }
                }
            }
        }
        return minAtoB;
    }

    // 0 if input error, particularly null data
    // 3 if the currency already exists
    // 4 if the data already exists
    // 1 if successful
    public int addCurrency(String date, String currency, HashMap<String, Double> rates,
            HashMap<String, Double> rateForOtherCurr) {
        if (date == null || currency == null || rates == null || rateForOtherCurr == null) {
            return 0;
        }

        // check if date already exists
        if (this.currencies.containsKey(date)) {
            return 4;
        }

        // check if the currency already exists
        String latest = findMostRecentDate();
        if (findCurrency(currency, this.currencies.get(latest)) != null) {
            return 3;
        }

        // set up currency object
        List<Currency> cloned = new ArrayList<>(this.currencies.get(latest));
        Currency curr = new Currency(currency);
        curr.setConversionRate(rates);

        // add currency rate to all other currencies hashmap
        for (Currency c : cloned) {
            c.getConversionRates().put(currency, rateForOtherCurr.get(c.getName()));
        }

        cloned.add(curr);
        this.currencies.put(date, cloned);
        return 1;
    }

}