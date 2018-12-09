package jerre.models

import java.util.*

class AddressJalla(streetNameParam: String, streetNumberParam: Int?) {
    constructor(streetName: String): this(streetNameParam = streetName, streetNumberParam = null)

    private val streetName: String
    private val streetNumber: Int?

    init {
        this.streetName = streetNameParam
        this.streetNumber = streetNumberParam
    }

    fun getStreetName(): String {
        return this.streetName
    }

    fun getStreetNumber(): Int? {
        return this.streetNumber
    }

    override fun equals(other: Any?): Boolean {
        if (this == other) return true
        if (other == null || this.javaClass != other.javaClass) return false
        val address = other as AddressJalla
        return Objects.equals(streetName, address.streetName)
                && Objects.equals(streetNumber, address.streetNumber)
    }

    override fun hashCode(): Int {
        return Objects.hash(streetNumber, streetNumber)
    }

    override fun toString(): String {
        return "Address{" +
                "streetName='" + streetName + "\'" +
                ", streetNumber=" + streetNumber +
                "}"
    }

    fun pretty(): String {
        val prettyStreetName = streetName   //Trenger ikke null-sjekk her siden streetName ikke kan være null
        val prettyStreetNumber = if (streetNumber == null) "" else streetNumber
        return streetName + " " + streetNumber
    }
}

class AddressMindreJalla(val streetName: String, val streetNumber: Int? = null) {

    override fun equals(other: Any?): Boolean {
        if (this == other) return true
        if (other == null || this.javaClass != other.javaClass) return false
        val address = other as AddressJalla
        return Objects.equals(streetName, address.getStreetName())
                && Objects.equals(streetNumber, address.getStreetName())
    }

    override fun hashCode(): Int {
        return Objects.hash(streetNumber, streetNumber)
    }

    override fun toString(): String {
        return "Address{" +
                "streetName='" + streetName + "\'" +
                ", streetNumber=" + streetNumber +
                "}"
    }

    fun pretty(): String {
        val prettyStreetName = streetName   //Trenger ikke null-sjekk her siden streetName ikke kan være null
        val prettyStreetNumber = if (streetNumber == null) "" else streetNumber
        return streetName + " " + streetNumber
    }
}

data class Address @JvmOverloads constructor(val streetName: String, val streetNumber: Int? = null) {
    fun pretty(): String = "$streetName ${streetNumber ?: ""}"
}

fun main(args: Array<String>) {
    //Jalla varianten
    var addressJalla = AddressJalla(streetNameParam = "Gata", streetNumberParam = 1)
//    var addressJalla = AddressJalla("Gata")
    println(addressJalla)
    println("streetName: ${addressJalla.getStreetName()}")
    println("streetNumber: ${addressJalla.getStreetNumber()}")
    println("pretty: ${addressJalla.pretty()}")
    println()

    //Mindre jalla variant
    var addressMindreJalla = AddressMindreJalla(streetNumber = 1, streetName = "Gata")
//    var addressMindreJalla = AddressMindreJalla("Gata")
    println(addressMindreJalla)
    println("streetName: ${addressMindreJalla.streetName}")
    println("streetNumber: ${addressMindreJalla.streetNumber}")
    println("pretty: ${addressMindreJalla.pretty()}")
    println()

    //Kotlin variant
    var address = Address(streetName = "Gata", streetNumber = 1)
//    var address = Address("Gata")

    println(addressMindreJalla)
    println("streetName: ${addressMindreJalla.streetName}")
    println("streetNumber: ${addressMindreJalla.streetNumber}")
    println("pretty: ${addressMindreJalla.pretty()}")

    var addressCopy = address.copy()
    println("copy is same: ${address == addressCopy}")

    var neighbour = address.copy(streetNumber = 2)
    println("neighbour address: $neighbour")
}