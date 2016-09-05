package eu.accesa.api;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import eu.accesa.model.Message;
import eu.accesa.model.People;
import eu.accesa.model.Person;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * Created by andreicontan on 01/09/16.
 */
public class GetPersonTests extends BasePersonClass{


    @Test
    public void testGetPersonById(){
        String id = "1";

        WebResource webResource = client
                .resource(url + "/rest/people/" + id);

        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        Person result = response.getEntity(new GenericType<Person>(){});

        Assert.assertEquals(result.getId(), 1);
        Assert.assertEquals(result.getOrganisation(), "Spidertracks");
    }

    @Test
    public void testGetAllPersons() throws IOException {
        ClientConfig clientConfig = new DefaultClientConfig();

        clientConfig.getClasses().add(JacksonJaxbJsonProvider.class);
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);

        WebResource webResource = client
            .resource(url + "/rest/people");

        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        People people = response.getEntity(People.class);

        for (Person person : people.getPeople()){
            Assert.assertTrue(person.getId() > 0);
            Assert.assertTrue(person.getName().length() > 0);
            Assert.assertTrue(person.getOrganisation().length() > 0);
        }


}

    @Test
    public void createNewPerson() {

        ClientConfig clientConfig = new DefaultClientConfig();

        clientConfig.getClasses().add(JacksonJaxbJsonProvider.class);
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);

        WebResource webResource = client
                .resource(url +"/rest/people");

        Person dedee = new Person("PowerPuff Girls" + new Timestamp(System.currentTimeMillis())
        , "Cartoon Network");

        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, dedee);

      Message output = response.getEntity(Message.class);

        Assert.assertTrue(output.getMessage().contains("Person added"));
    }

}


