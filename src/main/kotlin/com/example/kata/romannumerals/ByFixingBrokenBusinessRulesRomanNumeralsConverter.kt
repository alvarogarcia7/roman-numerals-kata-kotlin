package com.example.kata.romannumerals

class ByFixingBrokenBusinessRulesRomanNumeralsConverter : RomanNumeralConverter {
    override fun convert(arabic: Int): String {
        return ByAllConfigurationsConverter().convert(arabic)
    }

    override fun convert(roman: String): Int {
        return ByAllConfigurationsConverter().convert(roman)
    }

}
