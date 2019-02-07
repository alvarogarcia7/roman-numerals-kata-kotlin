import com.example.kata.romannumerals.ByAllConfigurationsConverter
import com.example.kata.romannumerals.RomanNumeralConverter
import com.example.kata.romannumerals.test.RomanNumeralsTest

class ByAllConfigurationsRomanNumeralsTest : RomanNumeralsTest() {
    override fun obtainRomanNumeralsConverter(): RomanNumeralConverter {
        return ByAllConfigurationsConverter()
    }
}
