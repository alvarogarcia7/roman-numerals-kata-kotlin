package com.example.kata.romannumerals.test

import com.example.kata.romannumerals.ByAllConfigurationsConverter
import com.example.kata.romannumerals.RomanNumeralConverter
import com.example.kata.romannumerals.RomanNumeralLetters
import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.generator.InRange
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitQuickcheck::class)
abstract class RomanNumeralsTest {

    private lateinit var romanNumeralConverter: RomanNumeralConverter

    abstract fun obtainRomanNumeralsConverter(): RomanNumeralConverter

    @Before
    fun before() {
        romanNumeralConverter = obtainRomanNumeralsConverter()
    }

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
        assertConversion(10 - 1, "IX")
        assertConversion(10 + 10 - 1, "XIX")
        assertConversion(50 - 10, "XL")
        assertConversion(100 - 10, "XC")
        assertConversion(500 - 100, "CD")
        assertConversion(1000 - 100, "CM")
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

    @Property(trials = 1000)
    fun cannot_have_4_equal_letters_in_a_row(@InRange(minInt = 1, maxInt = 3999) arabic: Int) {
        val convert = romanNumeralConverter.convert(arabic)
        for (configuration in RomanNumeralLetters.configurations) {
            val contains =
                convert.contains(configuration.roman + configuration.roman + configuration.roman + configuration.roman)
            if (contains) {
                fail<String>("$arabic contains 4 times the same letter: $convert")
            }
            assertThat(contains).isFalse()
        }
    }
    @Property(trials = 4000)
    fun can_convert_back_and_forth(@InRange(minInt = 1, maxInt = 3999) arabic: Int) {
        assertThat(romanNumeralConverter.convert(romanNumeralConverter.convert(arabic))).isEqualTo(arabic)
    }

    @Property(trials = 1000)
    fun always_the_biggest_roman_available(@InRange(minInt = 1, maxInt = 3999) arabic: Int) {
        val convert = romanNumeralConverter.convert(arabic)
        // Example: MCMC is not a valid Roman, because it should be written MM
        // testing that CMC => 1000 => M != CMC
        // calculate all substrings to check this
        for (i in 0.rangeTo(convert.length)) {
            val subst = convert.substring(i)
            val convert1 = romanNumeralConverter.convert(subst)
            var convert2 = romanNumeralConverter.convert(convert1)
            assertThat(convert2).isEqualTo(subst).withFailMessage("$arabic, $subst")
        }
    }

    fun assertConversion(arabic: Int, expectedRoman: String) {
        assertThat(romanNumeralConverter.convert(arabic)).isEqualTo(expectedRoman)
        assertThat(romanNumeralConverter.convert(expectedRoman)).isEqualTo(arabic)
    }
}
