package com.example.kata.romannumerals

class RomanNumeralConverter {
    fun convert(arabic: Int): String {
        return when (arabic) {
            1 -> "I"
            5 -> "V"
            10 -> "X"
            50 -> "L"
            100 -> "C"
            500 -> "D"
            1000 -> "M"
            else -> ""
        }
    }

}
