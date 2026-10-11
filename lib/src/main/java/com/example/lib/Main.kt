package com.example.lib

fun demonstrateSizable(airline: Airline) {
    println()
    println("--- Sizable ---")
    val sizables: List<Sizable> = listOf(airline, airline.flights.first(), airline.flights.last())
    for (s in sizables) {
        println("${s::class.simpleName}: size = ${s.size()}")
    }
}

fun demonstrateExceptions(airline: Airline) {
    println()
    println("--- Izjeme ---")

    // 1) NumberFormatException: niz ni stevilo
    try {
        val gate = "dvanajst".toInt()
        println(gate)
    } catch (e: NumberFormatException) {
        println("NumberFormatException: ${e.message}")
    }

    // 2) IndexOutOfBoundsException: indeks izven seznama
    try {
        println(airline.flights[500])
    } catch (e: IndexOutOfBoundsException) {
        println("IndexOutOfBoundsException: ${e.message}")
    }

    // 3) ArithmeticException: deljenje z 0
    try {
        val passengers = 0
        println(5000 / passengers)
    } catch (e: ArithmeticException) {
        println("ArithmeticException: ${e.message}")
    }

    // 4) NoSuchElementException: first() na praznem seznamu
    try {
        println(emptyList<Flight>().first())
    } catch (e: NoSuchElementException) {
        println("NoSuchElementException: ${e.message}")
    }

    // 5) Lastna izjema: prozena v init bloku, ujeta tukaj
    try {
        val aircraft = Aircraft("A320", "Airbus", 180)
        Flight("JP000", "Nikamor", -30, 99.0, aircraft)
    } catch (e: InvalidFlightException) {
        println("InvalidFlightException: ${e.message}")
    } finally {
        println("Preverjanje leta koncano.")
    }
}

fun demonstratePart2(airline: Airline) {
    println()
    println("--- 2. del ---")
    val search = "ville"

    val cargoCount = airline.flights.count { it is CargoFlight }
    println("Vseh letov: ${airline.size()}, od tega tovornih: $cargoCount")

    println("\nLeti, katerih destinacija vsebuje '$search':")
    airline.flightsContaining(search).forEach { println(it) }

    println("\nLeti, katerih destinacija NE vsebuje '$search' (prvih 5):")
    airline.flightsNotContaining(search).take(5).forEach { println(it) }

    println("\nStevilo letov, ki vsebujejo '$search': ${airline.countContaining(search)}")
    println("Povprecno trajanje leta: ${"%.1f".format(airline.averageDuration())} min")

    println("\nPrvih 10 letov z destinacijo daljso od 5 znakov:")
    airline.firstTenWithLongDestination().forEach { println(it) }
}

fun main() {
    val airline = Airline("Stavy Air")

    // 100 nakljucnih letov (navadnih in tovornih)
    Flight.generateFlights(100).forEach { airline.addFlight(it) }

    // uredi po trajanju (compareTo) in izpisi
    airline.sortFlights()
    airline.printFlights()

    demonstrateSizable(airline)
    demonstrateExceptions(airline)
    demonstratePart2(airline)
}