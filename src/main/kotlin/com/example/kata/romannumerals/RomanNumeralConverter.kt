package com.example.kata.romannumerals

class RomanNumeralConverter {
    private val configurations = arrayListOf(
        Group(1000, "M"),
        Group(500, "D"),
        Group(100, "C"),
        Group(50, "L"),
        Group(10, "X"),
        Group(5, "V"),
        Group(1, "I")
    )

    fun convert(arabic: Int): String {
        var remaining = arabic
        var roman = ""
        while (remaining > 0) {
            for (configuration in configurations) {
                if (remaining >= configuration.arabic) {
                    remaining -= configuration.arabic
                    roman += configuration.roman
                }
            }
        }
        return roman
    }

}

data class Group(val arabic: Int, val roman: String)
