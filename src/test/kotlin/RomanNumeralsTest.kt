package com.example.kata.romannumerals.test

import com.example.kata.romannumerals.RomanNumeralConverter
import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.generator.InRange
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.Before
import org.junit.Test
import org.junit.experimental.theories.ParametersSuppliedBy
import org.junit.experimental.theories.Theory
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

open class RomanNumeralsTest {
    protected lateinit var romanNumeralConverter: RomanNumeralConverter

    @Before
    fun before() {
        romanNumeralConverter = RomanNumeralConverter()
    }

    internal fun assertConversion(arabic: Int, expectedRoman: String) {
        assertThat(romanNumeralConverter.convert(arabic)).isEqualTo(expectedRoman)
        assertThat(romanNumeralConverter.convert(expectedRoman)).isEqualTo(arabic)
    }

    @RunWith(Parameterized::class)
    open class Examples_Convert_single_letters(val arabic: Int, val roman: String) : RomanNumeralsTest() {

        companion object Single_Letters {
            @JvmStatic
            @Parameterized.Parameters(name = "Arabic = {0}, Roman = {1}")
            fun data(): Collection<Array<Any>> {
                return arrayListOf(
                    arrayOf(1, "I"),
                    arrayOf(5, "V"),
                    arrayOf(10, "X"),
                    arrayOf(50, "L"),
                    arrayOf(100, "C"),
                    arrayOf(500, "D"),
                    arrayOf(1000, "M")
                )
            }
        }

        @Test()
        open fun convert_single_letters() {
            assertConversion(arabic, roman)
        }
    }

    @RunWith(Parameterized::class)
    open class Examples_2(val arabic: Int, val roman: String) : RomanNumeralsTest() {

        companion object {
            @JvmStatic
            @Parameterized.Parameters(name = "Arabic = {0}, Roman = {1}")
            fun data(): Collection<Array<Any>> {
                return arrayListOf(
                    arrayOf(2, "II")
                )
            }
        }

        @Test
        fun convert_numbers_that_have_two_letters() {
            assertConversion(arabic, roman)
        }
    }

    @RunWith(Parameterized::class)
    open class Examples_3(val arabic: Int, val roman: String) : RomanNumeralsTest() {

        companion object {
            @JvmStatic
            @Parameterized.Parameters(name = "Arabic = {0}, Roman = {1}")
            fun data(): Collection<Array<Any>> {
                return arrayListOf(
                    arrayOf(7, "VII"),
                    arrayOf(8, "VIII")
                )
            }
        }

        @Test
        fun convert_numbers_with_more_letters_simple_cases() {
            assertConversion(arabic, roman)
        }
    }

    @RunWith(Parameterized::class)
    open class Examples_4(val arabic: Int, val roman: String) : RomanNumeralsTest() {

        companion object {
            @JvmStatic
            @Parameterized.Parameters(name = "Arabic = {0}, Roman = {1}")
            fun data(): Collection<Array<Any>> {
                return arrayListOf(
                    arrayOf(10 - 1, "IX"),
                    arrayOf(10 + 10 - 1, "XIX"),
                    arrayOf(50 - 10, "XL"),
                    arrayOf(100 - 10, "XC"),
                    arrayOf(500 - 100, "CD"),
                    arrayOf(1000 - 100, "CM")
                )
            }
        }

        @Test
        fun convert_numbers_with_more_letters_exceptional_cases() {
            assertConversion(arabic, roman)
        }
    }

    @RunWith(Parameterized::class)
    open class Examples_5(val arabic: Int, val roman: String) : RomanNumeralsTest() {

        companion object {
            @JvmStatic
            @Parameterized.Parameters(name = "Arabic = {0}, Roman = {1}")
            fun data(): Collection<Array<Any>> {
                return arrayListOf(
                    arrayOf(2000, "MM")
                )
            }
        }

        @Test
        fun can_repeat_letters() {
            assertConversion(arabic, roman)
        }
    }

    @RunWith(Parameterized::class)
    open class Examples_6(val arabic: Int, val roman: String) : RomanNumeralsTest() {

        companion object {
            @JvmStatic
            @Parameterized.Parameters(name = "Arabic = {0}, Roman = {1}")
            fun data(): Collection<Array<Any>> {
                return arrayListOf(
                    arrayOf(3927, "MMMCMXXVII"),
                    arrayOf(3999, "MMMCMXCIX")
                )
            }
        }

        @Test
        fun acceptance_cases() {
            assertConversion(arabic, roman)
        }
    }

}

@RunWith(JUnitQuickcheck::class)
class Properties : RomanNumeralsTest() {
    @Property(trials = 1000)
    fun can_convert_back_and_forth(@InRange(minInt = 1, maxInt = 3999) arabic: Int) {
        assertThat(romanNumeralConverter.convert(romanNumeralConverter.convert(arabic))).isEqualTo(arabic)
    }

    @Property(trials = 1000)
    fun always_the_biggest_roman_available(@InRange(minInt = 1, maxInt = 3999) arabic: Int) {
        val convert = romanNumeralConverter.convert(arabic)
        // Example: MCMC is not a valid Roman, because it should be written MM
        // testing that CMC = 1000 = M != CMC
        for (i in 0.rangeTo(convert.length)) {
            val subst = convert.substring(i)
            val convert1 = romanNumeralConverter.convert(subst)
            var convert2 = romanNumeralConverter.convert(convert1)
            assertThat(convert2).isEqualTo(subst).withFailMessage("$arabic, $subst")
        }
    }

    @Property(trials = 1000)
    fun cannot_have_4_equal_letters_in_a_row(@InRange(minInt = 1, maxInt = 3999) arabic: Int) {
        val convert = romanNumeralConverter.convert(arabic)
        for (configuration in romanNumeralConverter.configurations) {
            val contains =
                convert.contains(configuration.roman + configuration.roman + configuration.roman + configuration.roman)
            if (contains) {
                fail<String>("$arabic contains 4 times the same letter: $convert")
            }
            assertThat(contains).isFalse()
        }
    }

}

