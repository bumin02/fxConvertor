/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package CC_04_Wed_16_Frank_Group;

import java.io.IOException;

public class App {
    Converter converter;

    public App(){
        converter = new Converter();


    }


    public static void main(String[] args) throws IOException {
        DataBase d = new DataBase();
        try{
            d.initialiseData();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}