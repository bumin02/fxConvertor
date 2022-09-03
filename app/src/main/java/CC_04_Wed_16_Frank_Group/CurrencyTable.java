package CC_04_Wed_16_Frank_Group;

import java.util.HashMap;
import java.util.List;
// import org.json.simple.JSONObject;
// import org.json.simple.JSONArray;
// import processing.data.JSONArray;
// import processing.data.JSONObject;
// import org.json.simple.JSONObject;
// import org.json.simple.parser.ParseException;
// import org.json.simple.parser.JSONParser;


public class CurrencyTable {

    int TABLE_SIZE = 4;
    String todayDate = "3/9/22"; // somehow adjust date

    public Currency findCurrency(String name, List<Currency> currencies) {
        for (Currency a : currencies) {
            if (a.getName().equals(name)) {
                return a;
            }
        }
        return null;
    }

    public void display() {

        DataBase database = new DataBase();

        List<Currency> currencies = database.currencies.get(todayDate);
        HashMap<String, Double> aud = findCurrency("AUD", currencies).getConversionRates();
        HashMap<String, Double> usd = findCurrency("USD", currencies).getConversionRates();
        HashMap<String, Double> cny = findCurrency("CNY", currencies).getConversionRates();
        HashMap<String, Double> eur = findCurrency("EUR", currencies).getConversionRates();

        System.out.println("_____________________________________________________");

        System.out.println("| From/To | AUD | USD | CNY | EUR |");
        System.out.printf("|   %6s   | %6s | %6s | %6s | %6s |", "From/To", "AUD", "USD", "CNY", "EUR");
        System.out.println("-----------------------------------------------------");
        System.out.printf("|   AUD   | %6s | %6s | %6s | %6s |", "-", aud.get("USD"), aud.get("CNY"), aud.get("EUR"));
        System.out.println("-----------------------------------------------------");
        System.out.printf("|   USD   | %6s | %6s | %6s | %6s |", usd.get("AUD"), "-", usd.get("CNY"), usd.get("EUR"));
        System.out.println("-----------------------------------------------------");
        System.out.printf("|   CNY   | %6s | %6s | %6s | %6s |", cny.get("AUD"), cny.get("USD"), "-", cny.get("EUR"));
        System.out.println("-----------------------------------------------------");
        System.out.printf("|   EUR   | %6s | %6s | %6s | %6s |", eur.get("AUD"), cny.get("USD"), cny.get("CNY"), "-");
        System.out.println("_____________________________________________________");

    }
}