package com.example.kata.romannumerals.test

import com.example.kata.romannumerals.RomanNumeralConverter
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class RomanNumeralsTest {
    @Test
    fun convert_single_letters() {
        assertConversion(1, "I")
        assertConversion(5, "V")
        assertConversion(10, "X")
        assertConversion(50, "L")
        assertConversion(100, "C")
        assertConversion(500, "D")
        assertConversion(1000, "M")
    }

    @Test
    fun convert_numbers_that_have_two_letters() {
        assertConversion(2, "II")
    }

    @Test
    fun convert_numbers_with_more_letters_simple_cases() {
        assertConversion(7, "VII")
        assertConversion(8, "VIII")
    }

    @Test
    fun convert_numbers_with_more_letters_exceptional_cases() {
        assertConversion(10-1, "IX")
        assertConversion(10+10-1, "XIX")
        assertConversion(50-10, "XL")
        assertConversion(100-10, "XC")
        assertConversion(500-100, "CD")
        assertConversion(1000-100, "CM")
    }

    @Test
    fun can_repeat_letters() {
        assertConversion(2000, "MM")
    }

    @Test
    fun acceptance_cases() {
        assertConversion(3927, "MMMCMXXVII")
        assertConversion(3999, "MMMCMXCIX")
    }

    private fun assertConversion(arabic: Int, expectedRoman: String) {
        assertThat(RomanNumeralConverter().convert(arabic)).isEqualTo(expectedRoman)
    }
}
