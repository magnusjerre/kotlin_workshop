package jerre.kotlin.workshop.models;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Person implements Id {

    private Long id;

    private final LocalDate birthDate;

    private String firstName;

    private String lastName;

    private Set<Person> children;

    public Person(Long id, LocalDate birthDate, String firstName, String lastName, Set<Person> children) {
        this.id = id;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.children = children;
    }

    public Person(LocalDate birthDate, String wholeName) {
        this(null, birthDate,
                wholeName.split(" ")[0],
                wholeName.substring(wholeName.indexOf(" ") + 1),
                new HashSet<>());
    }

    public LocalDate getBirthDate() {
        return birthDate;
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

    public Set<Person> getChildren() {
        return children.stream().collect(Collectors.toSet());
    }

    public boolean addChild(Person person) {
        return children.add(person);
    }

    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(birthDate, person.birthDate) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(children, person.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, birthDate, firstName, lastName, children);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", birthDate=" + birthDate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", children=" + children +
                '}';
    }
}
