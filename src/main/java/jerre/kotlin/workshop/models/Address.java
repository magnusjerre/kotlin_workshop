package jerre.kotlin.workshop.models;

import java.util.Objects;
import java.util.Optional;

public class Address {
    private String streetName;
    private Integer streetNumber;

    public Address(String streetName, Integer streetNumber) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
    }

    public Address(String streetName) {
        this(streetName, null);
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Optional<Integer> getStreetNumber() {
        return Optional.ofNullable(streetNumber);
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(streetName, address.streetName) &&
                Objects.equals(streetNumber, address.streetNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetName, streetNumber);
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", streetNumber=" + streetNumber +
                '}';
    }

    public String pretty() {
        String prettyStreet = streetName == null ? "Unknown streetName" : streetName;
        String prettyStreetNumber = this.streetNumber == null ? "" : this.streetNumber.toString();
        return String.format("%s %s", prettyStreet, prettyStreetNumber);
    }
}
