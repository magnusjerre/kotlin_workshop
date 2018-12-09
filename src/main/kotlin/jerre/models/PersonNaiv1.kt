package jerre.models

import jerre.kotlin.workshop.models.Address
import java.time.LocalDate
import java.util.*

class PersonNaiv1(var id: Long?, val birthDate: LocalDate, var name: String, var address: Address?) {
    constructor(birthDate: LocalDate, name: String, address: Address?) : this(id = null, birthDate = birthDate, name = name, address = address)
    constructor(birthDate: LocalDate, name: String) : this(birthDate = birthDate, name = name, address = null)



    override fun equals(other: Any?): Boolean {
        if (this == other) return true
        if (other == null || javaClass != other.javaClass) return false;
        val person = other as PersonNaiv1
        return id == other.id && birthDate == person.birthDate && name == person.name && address == person.address
    }

    override fun hashCode(): Int {
        return Objects.hash(id, birthDate, name, address)
    }

    override fun toString(): String {
        return "PersonNaiv1{" +
                "id=" + id +
                ", birthDate=" + birthDate +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}'
    }
}