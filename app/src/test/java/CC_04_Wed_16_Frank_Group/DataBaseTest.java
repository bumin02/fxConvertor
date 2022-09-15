package CC_04_Wed_16_Frank_Group;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class DataBaseTest {
    DataBase dataBase;

    @BeforeEach
    public void setUp() {
        dataBase = new DataBase();
    }


    @Test
    @DisplayName("Validate correct data structure is created based on input file")
    public void initialiseDataTest(){
        HashMap<String, List<Currency>> currencies = null;
        dataBase.initialiseData();
        currencies = dataBase.getCurrencies();

        // assert that the currencies hashmap is created
        assertNotNull(currencies);

        // validates the value of the key
        List<Currency> currencyList = currencies.get("31/08/22");
        assertNotNull(currencyList);
        assertEquals(6, currencyList.size(), "size of currency list should be 6");
        assertNotNull(CurrencyTable.findCurrency("AUD", currencyList));
        assertNotNull(CurrencyTable.findCurrency("USD", currencyList));
        assertNotNull(CurrencyTable.findCurrency("CNY", currencyList));
        assertNotNull(CurrencyTable.findCurrency("EUR", currencyList));
        assertNotNull(CurrencyTable.findCurrency("GBP", currencyList));
        assertNotNull(CurrencyTable.findCurrency("JPY", currencyList));
    }

    @Test
    @DisplayName("Validate conversion rate update")
    public void testUpdateConversionRate() {


    }

    @Test
    @DisplayName("Write to file")
    public void testWriteToFile() {
        dataBase.initialiseData();
        dataBase.writeToFile();
    }

    @Test
    @DisplayName("Validate Standard Deviation function")
    public void testStandardDeviation() {
        dataBase.initialiseData();
        Double result = dataBase.getSD("30/08/22", "31/08/22", "AUD", "USD");
        assertSame(result.getClass(), Double.class);
    }

    @Test
    @DisplayName("Validate Get Mean function")
    public void testGetMean() {
        dataBase.initialiseData();
        Double result = dataBase.getMean("30/08/22", "31/08/22", "AUD", "USD");
        assertSame(result.getClass(), Double.class);
    }

    @Test
    @DisplayName("Validate Get Median function")
    public void testGetMedian() {
        dataBase.initialiseData();
        Double result = dataBase.getMedian("30/08/22", "31/08/22", "AUD", "USD");
        assertSame(result.getClass(), Double.class);
    }

    @Test
    @DisplayName("Validate Get Max function")
    public void testGetMax() {
        dataBase.initialiseData();
        Double result = dataBase.getMax("30/08/22", "31/08/22", "AUD", "USD");
        assertSame(result.getClass(), Double.class);
    }

    @Test
    @DisplayName("Validate Get Min function")
    public void testGetMin() {
        dataBase.initialiseData();
        Double result = dataBase.getMin("30/08/22", "31/08/22", "AUD", "USD");
        assertSame(result.getClass(), Double.class);
    }

    @Test
    @DisplayName("Validate Find Second Most Recent Date function")
    public void testSecondMostRecentDate() {
        dataBase.initialiseData();
        String result = dataBase.findSecondMostRecentDate();
        assertSame(result.getClass(), String.class);
    }

    @Test
    @DisplayName("Validate Convert Currency function")
    public void testConvertCurrency() {
        dataBase.initialiseData();
        Float result = dataBase.convertCurrency("AUD","USD", 100);
        assertSame(result.getClass(), Float.class);
    }

    @Test
    @DisplayName("Validate Find Currency function")
    public void testFindCurrency() {
        dataBase.initialiseData();
        List<Currency> currencyList = new ArrayList<>();
        Currency currency = new Currency("AUD");
        currencyList.add(currency);
        Currency result = dataBase.findCurrency("AUD", currencyList);
        assertSame(result.getClass(), Currency.class);
        assertSame(result, currency);
        Currency cnull = dataBase.findCurrency("USD", currencyList);
        assertNull(cnull);

    }    

    @Test
    @DisplayName("Validate Get Currency names function")
    public void testGetCurrencyNames() {
        dataBase.initialiseData();
        List<String> result = dataBase.getCurrencyNames();
        assertSame(result.getClass(), ArrayList.class);
        assertTrue(result.contains("AUD"));
        assertTrue(result.contains("USD"));
        assertTrue(result.contains("CNY"));
        assertTrue(result.contains("EUR"));
        assertTrue(result.contains("GBP"));
        assertTrue(result.contains("JPY"));
    }
}
