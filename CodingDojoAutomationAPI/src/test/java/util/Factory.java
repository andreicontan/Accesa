package util;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import eu.accesa.model.Message;
import eu.accesa.model.Person;

import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by andreicontan on 05/09/16.
 */
public class Factory {

    public String addPerson(String name, String organization, Client client){


        WebResource webResource = client
                .resource("http://localhost:8090/rest/people");

        Person dedee = new Person(name, organization);

        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, dedee);

        Message output = response.getEntity(Message.class);
        return output.getMessage();
    }



    public List<Person> getAllPersons(Client client){

        WebResource webResource = client
                .resource("http://localhost:8090/rest/people");

        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        List<Person> persons = response.getEntity(new GenericType<List<Person>>(){});
        return persons;
    }
}
