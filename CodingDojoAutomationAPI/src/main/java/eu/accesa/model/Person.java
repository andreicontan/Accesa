package eu.accesa.model;

/**
 * Created by andreicontan on 01/09/16.
 */
public class Person {

    private int id;

    private String name;

    private String organisation;

    public Person() {
    }

    public Person(String n, String o) {
        this.name = n;
        this.organisation = o;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }


    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id : " + this.id + '\n');
        stringBuilder.append("Name : " + this.name + '\n');
        stringBuilder.append("Organization : " + this.organisation + '\n');

        return stringBuilder.toString();
    }

}
