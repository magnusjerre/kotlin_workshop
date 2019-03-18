package jerre

import java.time.LocalDate
import java.time.Month
import java.time.Period

fun main(args: Array<String>) {
    // Instantiated without the new keyword
    val person1 = Person(name = "Magnus", birthDate = LocalDate.of(1989, Month.DECEMBER, 14))
    println(person1.calcAge())

    // Anonymous objects
    val electric = object : Engine {
        override val fuelType: String = "electricity"
        override fun makeSound(): String = "bzzzzzz"
    }

    val diesel = object : Engine {
        override val fuelType: String = "diesel"
        override fun makeSound(): String = "vroom"
    }

    val mazda = Vehicle(model = "Mazda MX-5", engine = diesel)
    mazda.engine = electric
    val tesla = Vehicle(model = "Tesla Model X", engine = electric)
    println("Mazda sound: ${mazda.makeSound()}")

    println(Beer.prettyPercent(0.047f))
}

// Example data class, show generated code and comment on the generated file-name
// Add the calcAge() later
data class Person(var name: String, val birthDate: LocalDate) {
    fun calcAge() = Period.between(birthDate, LocalDate.now()).years
}

class Beer(val name: String, percent: Float) {
    val alcoholStrength: Float

    init {  // Also legal for data-classes
        require(percent > Beer.minimunAlcoholStrength)
        alcoholStrength = percent
    }

    constructor(name: String, percent: Double) : this(name, percent.toFloat())

    override fun toString(): String = "Beer(name=$name, alcoholStrength=$alcoholStrength)"

    // Show static properties
    // Name is optional, defaults to companion
    companion object General {
        val minimunAlcoholStrength = 2.5f
        fun prettyPercent(percent: Float) = "${percent * 100} %"
    }
}

// Delegate example
interface Engine {
    val fuelType: String
    fun makeSound(): String
}

class Vehicle(val model: String, var engine: Engine) : Engine by engine



