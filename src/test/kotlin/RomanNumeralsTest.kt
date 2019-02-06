package com.example.kata.romannumerals.test

import com.example.kata.romannumerals.RomanNumeralConverter
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class RomanNumeralsTest{
    @Test
    fun convert_single_letters(){
        assertConversion(1, "I")
    }

    private fun assertConversion(arabic: Int, expectedRoman: String) {
        assertThat(RomanNumeralConverter().convert(arabic)).isEqualTo(expectedRoman)
    }
}