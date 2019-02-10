package com.example.kata.romannumerals

class ByFixingBrokenBusinessRulesRomanNumeralsConverter : RomanNumeralConverter {
    override fun convert(arabic: Int): String {
        var remaining = (0 until arabic).joinToString("") { "I" }

        while (true) {
            val (shouldStop, remaining2) = compact(remaining)
            remaining = remaining2
            if (shouldStop) {
                break
            }

        }

        return remaining
    }

    private fun compact(input: String): Pair<Boolean, String> {
        val fixes = listOf(
            Pair("DD", "M"),
            Pair("DCD", "CM"),
            Pair("CCCCC", "D"),
            Pair("CCCC", "CD"),
            Pair("LL", "C"),
            Pair("LXL", "XC"),
            Pair("XXXXX", "L"),
            Pair("XXXX", "XL"),
            Pair("VV", "X"),
            Pair("VIV", "IX"),
            Pair("IIIII", "V"),
            Pair("IIII", "IV")
        )
        var applied = true
        var remaining = input
        for (fix in fixes) {
            if (remaining.contains(fix.first)) {
                applied = false
                val replaceFirst = remaining.replace(fix.first, fix.second)
                remaining = replaceFirst
                break
            }
        }
        return Pair(applied, remaining)
    }

    override fun convert(roman: String): Int {
        return ByAllConfigurationsConverter().convert(roman)
    }

}
