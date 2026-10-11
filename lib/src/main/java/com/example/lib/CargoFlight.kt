package com.example.lib

class CargoFlight(
    flightNumber: String,
    destination: String,
    durationMin: Int,
    price: Double,
    aircraft: Aircraft,
    val cargoTons: Double          // nova lastnost: teza tovora
) : Flight(flightNumber, destination, durationMin, price, aircraft) {

    override fun toString(): String =
        "CargoFlight($flightNumber -> $destination, trajanje=${formatDuration()}, " +
                "cena=${"%.2f".format(price)} EUR, tovor=${"%.1f".format(cargoTons)} t, letalo=$aircraft)"
}