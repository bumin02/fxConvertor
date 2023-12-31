package fxConverter;

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
    public void initialiseDataTest() {
        HashMap<String, List<Currency>> currencies = null;
        dataBase.initialiseData("src/main/java/fxConverter/testData.txt");
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
        // check error
        dataBase.initialiseData("src/main/java/fxConverter/testFile1.txt");
        assertEquals(0, dataBase.updateCurrency(null, null, null, null));
        assertEquals(4, dataBase.updateCurrency("31/08/22", "AUD", "JPY", 5.3010));
        assertEquals(3, dataBase.updateCurrency("1/09/22", "AAA", "JPY", 5.3010));
        assertEquals(2, dataBase.updateCurrency("1/09/22", "AUD", "AAA", 5.3010));

        // check updated correctly
        assertEquals(1, dataBase.updateCurrency("1/09/22", "AUD", "USD", 5.3010));
        assertTrue(dataBase.getCurrencies().containsKey("1/09/22"));
        List<Currency> ls = null;
        ls = dataBase.getCurrencies().get("1/09/22");
        assertNotNull(ls);
        assertNotNull(dataBase.findCurrency("AUD", ls));
        assertNotNull(dataBase.findCurrency("USD", ls));
        assertEquals(5.3010, dataBase.findCurrency("USD", ls).getConversionRates().get("AUD"));

        // check previous data were not changed
        List<Currency> ls2 = dataBase.getCurrencies().get("31/08/22");
        assertEquals(2.458886, dataBase.findCurrency("USD", ls2).getConversionRates().get("AUD"));
        List<Currency> ls3 = dataBase.getCurrencies().get("30/08/22");
        assertEquals(1.458886, dataBase.findCurrency("USD", ls3).getConversionRates().get("AUD"));
    }

    @Test
    @DisplayName("Write to file")
    public void testWriteToFile() {
        dataBase.initialiseData("src/main/java/fxConverter/testFile1.txt");
        dataBase.writeToFile();
        // assert to check if testFile1.txt exists
        assertTrue(dataBase.fileExists("src/main/java/fxConverter/testFile1.txt"));
    }

    @Test
    @DisplayName("Validate Standard Deviation function")
    public void testStandardDeviation() {
        dataBase.initialiseData("src/main/java/fxConverter/testFile1.txt");
        Double result = dataBase.getSD("30/08/22", "31/08/22", "AUD", "USD");
        // assertSame(result.getClass(), Double.class);
        assertEquals(result, 4.999999997368221E-8);
    }

    @Test
    @DisplayName("Validate Get Mean function")
    public void testGetMean() {
        dataBase.initialiseData("src/main/java/fxConverter/testFile1.txt");
        Double result = dataBase.getMean("30/08/22", "31/08/22", "AUD", "USD");
        // assertSame(result.getClass(), Double.class);
        assertEquals(result, 0.6856014500000001);
    }

    @Test
    @DisplayName("Validate Get Median function")
    public void testGetMedian() {
        dataBase.initialiseData("src/main/java/fxConverter/testFile1.txt");
        Double result = dataBase.getMedian("30/08/22", "31/08/22", "AUD", "USD");
        // assertSame(result.getClass(), Double.class);
        assertEquals(result, 0.6856015);
    }

    @Test
    @DisplayName("Validate Get Max function")
    public void testGetMax() {
        dataBase.initialiseData("src/main/java/fxConverter/testFile1.txt");
        Double result = dataBase.getMax("30/08/22", "31/08/22", "AUD", "USD");
        // assertSame(result.getClass(), Double.class);
        assertEquals(result, 0.6856015);
    }

    @Test
    @DisplayName("Validate Get Min function")
    public void testGetMin() {
        dataBase.initialiseData("src/main/java/fxConverter/testFile1.txt");
        Double result = dataBase.getMin("30/08/22", "31/08/22", "AUD", "USD");
        // assertSame(result.getClass(), Double.class);
        assertEquals(result, 0.6856014);
    }

    @Test
    @DisplayName("Validate Find Second Most Recent Date function")
    public void testSecondMostRecentDate() {
        dataBase.initialiseData("src/main/java/fxConverter/testFile1.txt");
        String result = dataBase.findSecondMostRecentDate();
        assertSame(result.getClass(), String.class);
        assertEquals(result, "30/08/22");
    }

    @Test
    @DisplayName("Validate Convert Currency function")
    public void testConvertCurrency() {
        dataBase.initialiseData("src/main/java/fxConverter/testFile1.txt");
        float result = dataBase.convertCurrency("AUD", "USD", 100);
        // compare with 0.0001 precision
        assertEquals(result, 68.56015, 0.0001);
    }

    @Test
    @DisplayName("Validate Find Currency function")
    public void testFindCurrency() {
        dataBase.initialiseData("src/main/java/fxConverter/testFile1.txt");
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
        dataBase.initialiseData("src/main/java/fxConverter/testFile1.txt");
        List<String> result = dataBase.getCurrencyNames();
        assertSame(result.getClass(), ArrayList.class);
        assertTrue(result.contains("AUD"));
        assertTrue(result.contains("USD"));
        assertTrue(result.contains("CNY"));
        assertTrue(result.contains("EUR"));
        assertTrue(result.contains("GBP"));
        assertTrue(result.contains("JPY"));
    }

    @Test
    @DisplayName("Test updatePopularCurrencies Function & getPopularCurrencies Function")
    public void testUpdatePopularCurrencies() {
        dataBase.initialiseData("src/main/java/fxConverter/testFile1.txt");

        List<String> popularCurrencies = new ArrayList<>();
        popularCurrencies.add("AUD");
        popularCurrencies.add("USD");
        popularCurrencies.add("CNY");
        popularCurrencies.add("EUR");

        dataBase.updatePopularCurrencies(popularCurrencies);

        List<String> result = dataBase.findPopularCurrencies();

        assertTrue(result.contains("AUD"));
        assertTrue(result.contains("USD"));
        assertTrue(result.contains("CNY"));
        assertTrue(result.contains("EUR"));

    }

    @Test
    @DisplayName("Validate add currency")
    public void testAddCurrency() {
        dataBase.initialiseData("src/main/java/fxConverter/testFile1.txt");

        // test error
        assertEquals(0, dataBase.addCurrency(null, null, null, null));
        HashMap<String, Double> rates = new HashMap<>();
        HashMap<String, Double> rateForOtherCurr = new HashMap<>();
        assertEquals(4, dataBase.addCurrency("31/08/22", "AUD", rates, rateForOtherCurr));
        assertEquals(3, dataBase.addCurrency("01/09/22", "AUD", rates, rateForOtherCurr));

        // test successful call
        rates.put("AUD", 2.148119);
        rates.put("JPY", 3.148119);
        rates.put("EUR", 4.148119);
        rates.put("GBP", 5.148119);
        rates.put("USD", 6.148119);
        rates.put("CNY", 7.148119);

        rateForOtherCurr.put("AUD", 2.148119);
        rateForOtherCurr.put("JPY", 3.148119);
        rateForOtherCurr.put("EUR", 4.148119);
        rateForOtherCurr.put("GBP", 5.148119);
        rateForOtherCurr.put("USD", 6.148119);
        rateForOtherCurr.put("CNY", 7.148119);

        assertEquals(1, dataBase.addCurrency("01/09/22", "ABC", rates, rateForOtherCurr));
        HashMap<String, List<Currency>> curr = dataBase.getCurrencies();
        assertTrue(curr.containsKey("01/09/22"));
        List<Currency> ls = curr.get("01/09/22");
        Currency currency = dataBase.findCurrency("ABC", ls);
        assertNotNull(currency);
        assertEquals(rates, currency.getConversionRates());

        Currency abc = dataBase.findCurrency("AUD", ls);
        assertEquals(2.148119, abc.getConversionRates().get("ABC"));
    }

}
