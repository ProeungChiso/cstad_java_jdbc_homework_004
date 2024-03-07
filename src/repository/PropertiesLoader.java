package repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

public class PropertiesLoader {
    public static Properties properties = new Properties();
    public static void loaderPropertiesFile(){
        try(
                BufferedReader reader = new BufferedReader(new FileReader("application.properties"))
                ){
            properties.load(reader);
        }catch (Exception e){
            System.out.println("Exception" + e.getMessage());
        }
    }
}
