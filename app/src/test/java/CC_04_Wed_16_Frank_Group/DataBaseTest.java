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

}
