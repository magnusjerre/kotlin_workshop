package jerre.kotlin.workshop.models;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public class Person implements Id {

    private Long id;
    private final LocalDate birthDate;
    private String name;
    private Address address;

    public Person(Long id, LocalDate birthDate, String name, Address address) {
        if (name == null || birthDate == null) {
            throw new IllegalArgumentException("Can't instantiate Person, birthDate and name can't be null");
        }
        this.id = id;
        this.birthDate = birthDate;
        this.name = name;
        this.address = address;
    }

    public Person(LocalDate birthDate, String name, Address address) {
        this(null, birthDate, name, address);
    }

    public Person(LocalDate birthDate, String name) {
        this(null, birthDate, name, null);
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

}
