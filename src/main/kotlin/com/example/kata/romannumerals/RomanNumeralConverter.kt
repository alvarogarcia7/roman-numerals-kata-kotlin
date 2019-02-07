package com.example.kata.romannumerals

interface RomanNumeralConverter {
    fun convert(arabic: Int): String
    fun convert(roman: String): Int
}

object RomanNumeralLetters {

    val configurations = arrayListOf(
        Group(1000, "M"),
        Group(500, "D"),
        Group(100, "C"),
        Group(50, "L"),
        Group(10, "X"),
        Group(5, "V"),
        Group(1, "I")
    )

    data class Group(val arabic: Int, val roman: String)
}
