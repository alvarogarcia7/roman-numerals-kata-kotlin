package com.example.kata.romannumerals

class ByLettersRomanNumeralConverter : RomanNumeralConverter {
    private val C = RomanNumeralLetters.Group(100, "C")
    private val X = RomanNumeralLetters.Group(10, "X")
    private val I = RomanNumeralLetters.Group(1, "I")
    private val configurations = arrayListOf(
        Pair(RomanNumeralLetters.Group(1000, "M"), C),
        Pair(RomanNumeralLetters.Group(500, "D"), C),
        Pair(C, X),
        Pair(RomanNumeralLetters.Group(50, "L"), X),
        Pair(X, I),
        Pair(RomanNumeralLetters.Group(5, "V"), I),
        Pair(I, C)
    )

    override fun convert(arabic: Int): String {
        var remaining = arabic
        var roman = ""
        while (remaining > 0) {
            for (i in 0.rangeTo(configurations.size)) {
                val configuration = configurations[i]
                if (remaining >= configuration.first.arabic) {
                    remaining -= configuration.first.arabic
                    roman += configuration.first.roman
                    break
                }
                val arabic1 = configuration.first.arabic - configuration.second.arabic
                if (remaining >= arabic1) {
                    remaining -= arabic1
                    roman += configuration.second.roman + configuration.first.roman
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
                if (remaining.startsWith(configuration.second.roman + configuration.first.roman, true)) {
                    remaining = remaining.removePrefix(configuration.second.roman + configuration.first.roman)
                    arabic += (configuration.first.arabic - configuration.second.arabic)
                    break
                }
                if (remaining.startsWith(configuration.first.roman, true)) {
                    remaining = remaining.removePrefix(configuration.first.roman)
                    arabic += configuration.first.arabic
                    break
                }
            }
        }
        return arabic
    }

}
