package jerre

import java.io.File
import java.io.InputStream
import java.math.BigDecimal
import java.math.MathContext
import java.time.LocalDate

// Show the main function declaration, and explain the syntax.
// Package level declaration
// Show the compiled file
fun main(args: Array<String>): Unit {
    println("Hello ${args[0]}") // No semicolon!

    forLoops()
//    nullsafety("Magnus", "Jerre")
//    nullsafety("Yolo", null)
//
//
//    println("Hello world!".snakeCase())
    println("abcdefg" - "cd")   //abefg

    println(annuity(pv = 1000.toBigDecimal(), n = 10, r = 0.01.toBigDecimal()))
    println(Utils.annuity(1000.toBigDecimal(), 10, 0.01.toBigDecimal()))

    stdlibUse()
}

// Show that all types are objects
fun noPrimitives() {
    var ten : Int = 10
    val PI : Float = 3.14f
    var a : Char = 'a'
}

// Show for-loops
fun forLoops() {
    // Java: for (int i = 1; i <= 10; i++) { ... }
    for (i in 1..10) {  //1 2 3 4 5 6 7 8 9 10
        println(i)
    }

    // Java: for (int i = 0; i < 10; i++) { ... }
    for (i in 0 until 10) {
        println(i)
    }

    val array = arrayOf(0, 1, 2, 3, 4)
    // Java: for (int i = 0; i < array.size(); i = i + 2) { ... }
    for (i in 0 until array.size step 2) {  //0 2 4
        println(array[i])
    }
}

// Show nullability
fun nullsafety(notNullabe: String, nullable: String?) {
    println(notNullabe.toLowerCase())
    println(nullable?.toLowerCase())    // prints null if nullable is null
    println(nullable!!.toLowerCase())   // KotlinNullPointerException if nullable is null
}

// Show smart casting
fun smartCasting(value: Any?) {
    if (value != null) {
        println(value.hashCode())

        if (value is String) {
            println(value.toLowerCase())
        }
    }
}

// Named and default parameters
// Show both = for creating methods and that if/else is an expression and therefore returns a value (same with try/catch)
// An expression in a programming language is a combination of one or more explicit values, constants, variables, operators and functions that the programming language interprets and computes to produce another value.
// In computer programming, a statement is the y
fun doStringStuff(stringToModify: String, snakeCase: Boolean = false) : String =
        if (snakeCase) stringToModify.replace(" ", "_")
        else stringToModify

// Extension functions
// Show the compiled file
fun String.snakeCase() = replace(" ", "_")

// Operator overloading example, kind of not perfect, but okay
operator fun String.minus(other: String): String = replace(other, "")

// Operator overloading more useful example
/**
 * formula: P = [r * (PV)] / [1 - (1 + r)^(-n)]
 * P = Payment
 * PV = present value
 * r = rate per period
 * n = number of periods
 *
 * http://financeformulas.net/Annuity_Payment_Formula.html
 * @param n
 * @return
 */
fun annuity(pv: BigDecimal, n: Int, r: BigDecimal) = (r * pv) / (1 - (1 + r).pow(-n, MathContext.DECIMAL128))

private operator fun Int.plus(bigDecimal: BigDecimal) = toBigDecimal() + bigDecimal
private operator fun Int.minus(bigDecimal: BigDecimal) = toBigDecimal() - bigDecimal

enum class BeverageType {
    BEER, WHINE, WHISKY, SODA, BOOZE
}

// Show compile error for when with missing clauses
fun whenIsSmart(beverageType: BeverageType): String =
        when (beverageType) {
            BeverageType.SODA -> "0.0%"
            BeverageType.BEER -> "4.7%"
            BeverageType.WHINE -> "12.0%"
            else -> ">30.0%"
        }


// stdlib functions on collections
fun stdlibCollections(list: List<String>) {
    println(list.maxBy { it.hashCode() })
    println(list.filter { it.contains("a") })
    println(list
            .groupBy { it.substring(0, 2) }
            .flatMap { group -> group.value.map { str -> group.key + str.toUpperCase() } }
            .joinToString())
}

// stdlib helper stuff
fun stdlibUse() {
    val linesInFile = File(Beer::class.java.getResource("/sample.txt").file).readLines()
    linesInFile.forEach { println(it) }

    // No need for try-with-resources
    Beer::class.java.getResource("/sample.txt").openStream().use {
        println(String(it.readAllBytes()))
    }
}

// stdlib with, creates a "this"-context
fun stdlibWith(): Float = with(Beer(name = "Leffe", percent = 4.7f)) {
    println("beer name: $name")
    this.alcoholStrength
}

// stdlib apply
fun stdlibApply(): Person = Person(name = "Magnus", birthDate = LocalDate.now().minusDays(1)).apply {
    name = name.toUpperCase()
}

// stdlib let
fun stdlibLet(): String = Person(name = "Magnus", birthDate = LocalDate.now().minusDays(1)).let {
    "${it.name} - ${it.calcAge()}"
}
