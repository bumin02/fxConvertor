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

    public static Currency findCurrency(String name, List<Currency> currencies) {
        for (Currency a : currencies) {
            if (a.getName().equals(name)) {
                return a;
            }
        }
        return null;
    }

    public void display(DataBase database) {

        // todo: fix harcoded date
        // todo: extend for arbitrary number of currencies
        
        List<Currency> currencies = database.currencies.get("31/08/22");
        HashMap<String, Double> aud = findCurrency("AUD", currencies).getConversionRates();
        HashMap<String, Double> usd = findCurrency("USD", currencies).getConversionRates();
        HashMap<String, Double> cny = findCurrency("CNY", currencies).getConversionRates();
        HashMap<String, Double> eur = findCurrency("EUR", currencies).getConversionRates();

        System.out.println("___________________________________________________________");

        System.out.printf("|   %6s   | %6s   | %6s   | %6s   | %6s   |\n", "From/To", "AUD", "USD", "CNY", "EUR");
        System.out.println("-----------------------------------------------------------");
        System.out.printf("|             |          |          |          |          |\n");
        System.out.printf("|     AUD     |%6s    | %.6f | %.6f | %.6f |\n", "-", aud.get("USD"), aud.get("CNY"), aud.get("EUR"));
        System.out.printf("|             |          |          |          |          |\n");
        System.out.println("-----------------------------------------------------------");
        System.out.printf("|             |          |          |          |          |\n");
        System.out.printf("|     USD     | %6f |%6s    | %6f | %6f |\n", usd.get("AUD"), "-", usd.get("CNY"), usd.get("EUR"));
        System.out.printf("|             |          |          |          |          |\n");
        System.out.println("-----------------------------------------------------------");
        System.out.printf("|             |          |          |          |          |\n");
        System.out.printf("|     CNY     | %6f | %6f |%6s    | %6f |\n", cny.get("AUD"), cny.get("USD"), "-", cny.get("EUR"));
        System.out.printf("|             |          |          |          |          |\n"); 
        System.out.println("-----------------------------------------------------------");
        System.out.printf("|             |          |          |          |          |\n");
        System.out.printf("|     EUR     | %6f | %6f | %6f |%6s    |\n", eur.get("AUD"), eur.get("USD"), eur.get("CNY"), "-");
        System.out.printf("|             |          |          |          |          |\n");
        System.out.println("-----------------------------------------------------------");

    }
}