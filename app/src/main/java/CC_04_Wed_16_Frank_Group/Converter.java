package CC_04_Wed_16_Frank_Group;

public class Converter {
    CurrencyTable currencyTable;
    LogBook logBook;
    DataBase dataBase;
    public boolean isAdmin;


    public Converter() {
        currencyTable = new CurrencyTable();
        logBook = new LogBook();
        dataBase = new DataBase();
        isAdmin = false;
    }

    public void setAdmin(boolean isAdmin){
        isAdmin = isAdmin;
    }

    public int convertMoney(String cur1, String cur2, int money) {

        return 0;
    }

    public void displayCurrencyTable(){


    }






}
