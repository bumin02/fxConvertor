package CC_04_Wed_16_Frank_Group;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
        for (int i = 0; i < currencies.size(); i++) {
            


        }
    }

    public String display() {
    
        DataBase database = new DataBase(); 
      	
      	List<Currency> currencies = database.currencies.get(todayDate);

 
        
        System.out.println("_____________________________________________________");
        //for (int i = 0; i < TABLE_SIZE; i++){ 
        //}
		System.out.println("| From/To | AUD | USD | CNY | EUR |");
      	System.out.printf("|   %6s   | %6s | %6s | %6s | %6s |", "From/To","AUD", "USD", "CNY", "EUR");
        System.out.println("-----------------------------------------------------");
     	System.out.printf("|   AUD   | %6s | %6s | %6s | %6s |", "-", database.get(currencies.currency.conversionRate.get("AUD")).get("USD"), database.get(currencies.get("AUD")).get("CNY")), database.get(currencies.get("AUD")).get("EUR"));
        System.out.println("-----------------------------------------------------");
        System.out.printf("|   USD   | %6s | %6s | %6s | %6s |", database.get(currencies.get("USD")).get("AUD"), "-", database.get(currencies.get("USD")).get("CNY"), database.get(currencies.get("USD")).get("EUR");
        System.out.println("-----------------------------------------------------");                  
        System.out.printf("|   CNY   | %6s | %6s | %6s | %6s |", database.get(currencies.get("CNY")).get("AUD")), database.get(currencies.get("CNY")).get("USD")), "-", database.get(currencies.get("CNY")).get("EUR"));
        System.out.println("-----------------------------------------------------");                  
        System.out.printf("|   EUR   | %6s | %6s | %6s | %6s |", database.get(currencies.get("EUR")).get("AUD")), database.get(currencies.get("EUR")).get("USD")), database.get(currencies.get("EUR")).get("CNY")), "-");
        System.out.println("_____________________________________________________");
        
    }

    public static void main(String[] args) {

        // JSONParser parser = new JSONParser();
    }