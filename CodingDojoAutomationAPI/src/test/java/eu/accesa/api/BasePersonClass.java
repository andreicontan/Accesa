package eu.accesa.api;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import util.EnvironmentSettings;

import java.io.IOException;

/**
 * Created by andreicontan on 01/09/16.
 */
public abstract class BasePersonClass {

    EnvironmentSettings environmentSettings = new EnvironmentSettings();
    String url = "";
    ClientConfig clientConfig = new DefaultClientConfig();
    Client client;


    @BeforeClass
    public void init() throws IOException {


        clientConfig.getClasses().add(JacksonJaxbJsonProvider.class);
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        client = Client.create(clientConfig);

        String host = environmentSettings.getPropValues("host").toString();
        String port = environmentSettings.getPropValues("port").toString();
        url = host + ":" +port;

        System.out.println("Aligning the planets on: " + url);
    }

    @AfterClass
    public void tearDown(){
        System.out.println("Everybody cooldown for a minute.....");
    }




}
