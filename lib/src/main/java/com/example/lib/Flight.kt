package com.example.lib

import io.github.serpro69.kfaker.Faker
import kotlin.random.Random

open class Flight(
    val flightNumber: String,
    val destination: String,
    val durationMin: Int,
    val price: Double,
    val aircraft: Aircraft
) : Comparable<Flight>, Sizable {

    init {
        if (durationMin <= 0) {
            throw InvalidFlightException("Let $flightNumber mora trajati vsaj 1 minuto (podano: $durationMin)")
        }
        if (price < 0) {
            throw InvalidFlightException("Cena leta $flightNumber ne sme biti negativna (podano: $price)")
        }
    }

    // dolzina leta = trajanje v minutah
    override fun size(): Int = durationMin

    // primerjava po trajanju leta
    override fun compareTo(other: Flight): Int = durationMin.compareTo(other.durationMin)

    protected fun formatDuration(): String = "${durationMin / 60}h ${durationMin % 60}min"

    override fun toString(): String =
        "Flight($flightNumber -> $destination, trajanje=${formatDuration()}, " +
                "cena=${"%.2f".format(price)} EUR, letalo=$aircraft)"

    companion object {
        private val faker = Faker()

        private val airlineCodes = listOf("JP", "LH", "OS", "LX", "AF", "TK", "FR", "W6")

        private val aircraftModels = listOf(
            Triple("A320", "Airbus", 180),
            Triple("A321neo", "Airbus", 220),
            Triple("A350-900", "Airbus", 315),
            Triple("737-800", "Boeing", 189),
            Triple("787-9", "Boeing", 290),
            Triple("CRJ900", "Bombardier", 90),
            Triple("E195", "Embraer", 120),
            Triple("72", "ATR", 70)
        )

        // lastna funkcija: stevilke letov (faker jih ne zna generirati)
        fun generateFlightNumbers(n: Int): List<String> =
            List(n) { "${airlineCodes.random()}${Random.nextInt(100, 10000)}" }

        // lastna funkcija: nakljucno letalo
        fun generateAircraft(): Aircraft {
            val (model, manufacturer, seats) = aircraftModels.random()
            return Aircraft(model, manufacturer, seats)
        }

        // nakljucno ustvari navadne in tovorne lete
        fun generateFlights(n: Int): List<Flight> {
            val numbers = generateFlightNumbers(n)
            return List(n) { i ->
                val destination = faker.address.city()
                val duration = Random.nextInt(45, 900)
                val price = Random.nextDouble(29.0, 1200.0)
                val aircraft = generateAircraft()

                if (Random.nextBoolean()) {
                    CargoFlight(numbers[i], destination, duration, price, aircraft,
                        cargoTons = Random.nextDouble(2.0, 120.0))
                } else {
                    Flight(numbers[i], destination, duration, price, aircraft)
                }
            }
        }
    }
}