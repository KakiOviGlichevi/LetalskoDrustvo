package com.example.lib

class Aircraft(
    val model: String,
    val manufacturer: String,
    val seats: Int
) {
    override fun toString(): String = "$manufacturer $model ($seats sedezev)"
}