package com.example.kata.romannumerals

class RomanNumeralConverter {
    fun convert(arabic: Int): String {
        var remaining = arabic
        var s = ""
        while (true) {
            if (remaining >= 1000) {
                remaining -= 1000
                s += "M"
            } else if (remaining >= 500) {
                remaining -= 500
                s += "D"
            } else if (remaining >= 100) {
                remaining -= 100
                s += "C"
            } else if (remaining >= 50) {
                remaining -= 50
                s += "L"
            } else if (remaining >= 10) {
                remaining -= 10
                s += "X"
            } else if (remaining >= 5) {
                remaining -= 5
                s += "V"
            } else if (remaining >= 1) {
                remaining -= 1
                s += "I"
            } else {
                break
            }
        }
        return s
    }

}
