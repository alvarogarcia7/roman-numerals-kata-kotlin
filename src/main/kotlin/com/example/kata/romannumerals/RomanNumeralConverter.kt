package com.example.kata.romannumerals

class RomanNumeralConverter {
    val configurations = arrayListOf(
        Group(1000, "M"),
        Group(900, "CM"),
        Group(500, "D"),
        Group(400, "CD"),
        Group(100, "C"),
        Group(90, "XC"),
        Group(50, "L"),
        Group(40, "XL"),
        Group(10, "X"),
        Group(9, "IX"),
        Group(5, "V"),
        Group(4, "IV"),
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
                    break
                }
            }
        }
        return roman
    }

    fun convert(roman: String): Int {
        var remaining = roman
        var arabic = 0
        while (remaining.isNotEmpty()) {

            for (configuration in configurations) {
                if (remaining.startsWith(configuration.roman, true)) {
                    remaining = remaining.removePrefix(configuration.roman)
                    arabic += configuration.arabic
                    break
                }
            }
        }
        return arabic
    }

}

data class Group(val arabic: Int, val roman: String)
