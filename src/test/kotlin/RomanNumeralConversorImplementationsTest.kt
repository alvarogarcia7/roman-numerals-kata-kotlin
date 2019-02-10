import com.example.kata.romannumerals.*
import com.example.kata.romannumerals.test.RomanNumeralsTest

class ByAllConfigurationsRomanNumeralsTest : RomanNumeralsTest() {
    override fun obtainRomanNumeralsConverter(): RomanNumeralConverter {
        return ByAllConfigurationsConverter()
    }
}

class ByLettersRomanNumeralsTest : RomanNumeralsTest() {
    override fun obtainRomanNumeralsConverter(): RomanNumeralConverter {
        return ByLettersRomanNumeralConverter()
    }
}

class ByDigitsUsingFormulaAndConversionTableRomanNumeralsTest : RomanNumeralsTest() {
    override fun obtainRomanNumeralsConverter(): RomanNumeralConverter {
        return ByDigitsUsingFormulaAndConversionTableRomanNumeralsConverter()
    }
}

class ByDigitsCompleteConversionTableRomanNumeralsTest : RomanNumeralsTest() {
    override fun obtainRomanNumeralsConverter(): RomanNumeralConverter {
        return ByDigitsCompleteConversionTableConverter()
    }
}
class ByFixingBrokenBusinessRulesRomanNumeralsTest : RomanNumeralsTest() {
    override fun obtainRomanNumeralsConverter(): RomanNumeralConverter {
        return ByFixingBrokenBusinessRulesRomanNumeralsConverter()
    }
}
