package com.example.kata.romannumerals

class ByAllConfigurationsConverter : RomanNumeralConverter {
    val configurations = arrayListOf(
        RomanNumeralLetters.Group(1000, "M"),
        RomanNumeralLetters.Group(900, "CM"),
        RomanNumeralLetters.Group(500, "D"),
        RomanNumeralLetters.Group(400, "CD"),
        RomanNumeralLetters.Group(100, "C"),
        RomanNumeralLetters.Group(90, "XC"),
        RomanNumeralLetters.Group(50, "L"),
        RomanNumeralLetters.Group(40, "XL"),
        RomanNumeralLetters.Group(10, "X"),
        RomanNumeralLetters.Group(9, "IX"),
        RomanNumeralLetters.Group(5, "V"),
        RomanNumeralLetters.Group(4, "IV"),
        RomanNumeralLetters.Group(1, "I")
    )

    override fun convert(arabic: Int): String {
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

    override fun convert(roman: String): Int {
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

