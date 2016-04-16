package za.co.paulscott.person.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.orientechnologies.orient.core.annotation.OId;
import com.orientechnologies.orient.core.annotation.OVersion;

import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by pscot on 3/2/2016.
 */
@JsonIgnoreProperties("handler")
public class Person {

    @OId
    private String id;

    @OVersion
    private Long version;

    private String firstName;

    private String lastName;

    private String email;

    private String title;

    private String image;

    @OneToMany
    private List<Person> knows;

    private int age;

    private String homepage;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Person> getKnows() {
        return knows;
    }

    public void setKnows(List<Person> knows) {
        this.knows = knows;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (getAge() != person.getAge()) return false;
        if (getId() != null ? !getId().equals(person.getId()) : person.getId() != null) return false;
        if (version != null ? !version.equals(person.version) : person.version != null) return false;
        if (getFirstName() != null ? !getFirstName().equals(person.getFirstName()) : person.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(person.getLastName()) : person.getLastName() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(person.getEmail()) : person.getEmail() != null) return false;
        if (getTitle() != null ? !getTitle().equals(person.getTitle()) : person.getTitle() != null) return false;
        if (getImage() != null ? !getImage().equals(person.getImage()) : person.getImage() != null) return false;
        if (getKnows() != null ? !getKnows().equals(person.getKnows()) : person.getKnows() != null) return false;
        return getHomepage() != null ? getHomepage().equals(person.getHomepage()) : person.getHomepage() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        result = 31 * result + (getKnows() != null ? getKnows().hashCode() : 0);
        result = 31 * result + getAge();
        result = 31 * result + (getHomepage() != null ? getHomepage().hashCode() : 0);
        return result;
    }
}