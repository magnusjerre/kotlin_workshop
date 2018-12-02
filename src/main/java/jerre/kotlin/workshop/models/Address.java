package jerre.kotlin.workshop.models;

import java.util.Objects;
import java.util.Optional;

public class Address {
    private String street;
    private String postalCode;
    private String postalArea;

    public Address(String street, String postalCode, String postalArea) {
        this.street = street;
        this.postalCode = postalCode;
        this.postalArea = postalArea;
    }

    public String getStreet() {
        return street;
    }

    public Optional<String> getPostalCode() {
        return Optional.ofNullable(postalCode);
    }

    public Optional<String> getPostalArea() {
        return Optional.ofNullable(postalArea);
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPostalArea(String postalArea) {
        this.postalArea = postalArea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
                Objects.equals(postalCode, address.postalCode) &&
                Objects.equals(postalArea, address.postalArea);
    }

    @Override
    public int hashCode() {

        return Objects.hash(street, postalCode, postalArea);
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", postalArea='" + postalArea + '\'' +
                '}';
    }

    public String pretty() {
        String prettyStreet = street == null ? "Unknown street" : street;
        String prettyPostalCode = postalCode == null ? "Unknown postal code" : postalCode;
        String prettyPostalArea = postalArea == null ? "Unknown postal area" : postalArea;
        return String.format("%s, %s %s", prettyStreet, prettyPostalCode, prettyPostalArea);
    }
}
