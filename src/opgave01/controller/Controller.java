package opgave01.controller;

import opgave01.models.Person;
import opgave01.models.Role;
import opgave01.storage.EaaaFileStorage;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    static EaaaFileStorage eaaaStorage;

    public Controller() {
        this.eaaaStorage = new EaaaFileStorage();
    }

    /**
     *
     * @param role
     * @return List<Person> where all person has the given role
     */
    public List<Person> filter(Role role) {
        List<Person> personList = new ArrayList<>();
        for (Person person : eaaaStorage.getPeople()) {
            if (person.getRole().equals(role)){
                personList.add(person);
            }
        }
        return personList;
    }

    /**
     *
     * @return all persons
     */
    public List<Person> getPeople() {
        return eaaaStorage.getPeople();
    }

    /**
     * Adds a new person
     * @param person
     */
    public void addPerson(Person person) {
        if (!eaaaStorage.getPeople().contains(person)){
            eaaaStorage.getPeople().add(person);
        }
    }
    public Person createPerson(String name, Role role){
        Person person = new Person(name, role);
        addPerson(person);
        return person;
    }

    /**
     * Persists all people
     */
    public void save() {
        eaaaStorage.save();
    }
}
