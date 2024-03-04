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
    public ArrayList<Person> filter(Role role) {
        ArrayList<Person> personList = new ArrayList<>();
        for (int i = 0; i < eaaaStorage.getPeople().size(); i++) {
            if (eaaaStorage.getPeople().get(i).getRole().equals(role)){
                personList.add(eaaaStorage.getPeople().get(i));
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
            eaaaStorage.addPerson(person);
        }
        eaaaStorage.save();
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
