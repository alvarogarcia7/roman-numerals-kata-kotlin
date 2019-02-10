package com.example.kata.romannumerals

class ByDigitsCompleteConversionTableConverter : RomanNumeralConverter {


    override fun convert(arabic: Int): String {
        val digits = arabic.toString().split("").filterNot { "" == it }.map { Integer.parseInt(it) }.toMutableList()

        var result = ""
        while (digits.size > 0) {

            val currentDigit = fetchNextDigit(digits)

            val xx1 = digits.size.toDouble()
            val magnitude = Math.pow(10.0, xx1).toInt()
            val x = currentDigit * magnitude

            result += when (x) {
                3000 -> "MMM"
                2000 -> "MM"
                1000 -> "M"
                900 -> "CM"
                800 -> "DCCC"
                700 -> "DCC"
                600 -> "DC"
                500 -> "D"
                400 -> "CD"
                300 -> "CCC"
                200 -> "CC"
                100 -> "C"
                90 -> "XC"
                80 -> "LXXX"
                70 -> "LXX"
                60 -> "LX"
                50 -> "L"
                40 -> "XL"
                30 -> "XXX"
                20 -> "XX"
                10 -> "X"
                9 -> "IX"
                8 -> "VIII"
                7 -> "VII"
                6 -> "VI"
                5 -> "V"
                4 -> "IV"
                3 -> "III"
                2 -> "II"
                1 -> "I"
                else -> ""
            }
        }


        return result
    }

    private fun fetchNextDigit(digits: MutableList<Int>): Int {
        val currentDigit = digits[0]
        digits.removeAt(0)
        return currentDigit
    }

    override fun convert(roman: String): Int {
        var remanining = roman
        var arabic = 0

        val xs = listOf(
            Pair(3000, "MMM"),
            Pair(2000, "MM"),
            Pair(1000, "M"),
            Pair(900, "CM"),
            Pair(800, "DCCC"),
            Pair(700, "DCC"),
            Pair(600, "DC"),
            Pair(500, "D"),
            Pair(400, "CD"),
            Pair(300, "CCC"),
            Pair(200, "CC"),
            Pair(100, "C"),
            Pair(90, "XC"),
            Pair(80, "LXXX"),
            Pair(70, "LXX"),
            Pair(60, "LX"),
            Pair(50, "L"),
            Pair(40, "XL"),
            Pair(30, "XXX"),
            Pair(20, "XX"),
            Pair(10, "X"),
            Pair(9, "IX"),
            Pair(8, "VIII"),
            Pair(7, "VII"),
            Pair(6, "VI"),
            Pair(5, "V"),
            Pair(4, "IV"),
            Pair(3, "III"),
            Pair(2, "II"),
            Pair(1, "I")
        )

        while (remanining.isNotEmpty()) {
            for (current in xs) {
                if (remanining.startsWith(current.second)) {
                    arabic += current.first
                    remanining = remanining.removePrefix(current.second)
                    break
                }
            }
        }
        return arabic
    }

}

