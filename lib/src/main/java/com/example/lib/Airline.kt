package com.example.lib

class Airline(val name: String) : Sizable {
    val flights: MutableList<Flight> = mutableListOf()

    // dolzina = stevilo letov
    override fun size(): Int = flights.size

    fun addFlight(flight: Flight) {
        flights.add(flight)
    }

    fun sortFlights() {
        flights.sort()
    }

    fun printFlights() {
        println("Letalska druzba: $name (${flights.size} letov)")
        flights.forEach { println(it) }
    }

    // leti, katerih destinacija vsebuje niz
    fun flightsContaining(text: String): List<Flight> =
        flights.filter { it.destination.contains(text, ignoreCase = true) }

    // leti, katerih destinacija NE vsebuje niza
    fun flightsNotContaining(text: String): List<Flight> =
        flights.filterNot { it.destination.contains(text, ignoreCase = true) }

    // aritmeticna operacija: povprecno trajanje leta v minutah
    fun averageDuration(): Double =
        if (flights.isEmpty()) 0.0 else flights.map { it.durationMin }.average()

    // prvih 10 letov z imenom destinacije daljsim od 5 znakov
    fun firstTenWithLongDestination(): List<Flight> =
        flights.filter { it.destination.length > 5 }.take(10)

    // stevilo letov, ki ustrezajo nizu
    fun countContaining(text: String): Int =
        flights.count { it.destination.contains(text, ignoreCase = true) }
}