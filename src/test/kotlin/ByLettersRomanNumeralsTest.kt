import com.example.kata.romannumerals.ByAllConfigurationsConverter
import com.example.kata.romannumerals.ByLettersRomanNumeralConverter
import com.example.kata.romannumerals.RomanNumeralConverter
import com.example.kata.romannumerals.test.RomanNumeralsTest

class ByLettersRomanNumeralsTest : RomanNumeralsTest() {
    override fun obtainRomanNumeralsConverter(): RomanNumeralConverter {
        return ByLettersRomanNumeralConverter()
    }
}
