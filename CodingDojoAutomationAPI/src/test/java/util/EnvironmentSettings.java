package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by andreicontan on 05/09/16.
 */
public class EnvironmentSettings {

    String envVariable = "";
    InputStream inputStream;

    public  String getPropValues(String property) throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "testsettings.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            prop.load(inputStream);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            String envVariable = prop.getProperty(property);

            return  envVariable;
//            result = "Company List = " + company1 + ", " + company2 + ", " + company3;
//            System.out.println(result + "\nProgram Ran on " + time + " by user=" + user);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return envVariable;
    }
}


