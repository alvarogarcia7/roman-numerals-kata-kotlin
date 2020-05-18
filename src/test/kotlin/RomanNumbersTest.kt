import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.generator.InRange
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck
import demo.RomanNumbers
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


@RunWith(JUnitQuickcheck::class)
class RomanNumbersTest {

    @Test
    // TODO: convert this to parameters :)
    //@Parameter(1, "I")
    fun `convert decimal to roman, using a single letter`() {
        assertConversion(1, "I")
        assertConversion(5, "V")
        assertConversion(10, "X")
        assertConversion(50, "L")
        assertConversion(100, "C")
        assertConversion(500, "D")
        assertConversion(1000, "M")
    }

    @Test
    // TODO: convert this to parameters :)
    fun `convert decimal to roman, using multiple letter`() {
        assertConversion(2, "II")
    }

    private fun assertConversion(arabic: Int, roman: String) {
        assertThat(RomanNumbers().toRoman(arabic)).isEqualTo(roman)
    }


    @Property(trials = 1000)
    fun cannot_have_4_equal_letters_in_a_row(@InRange(minInt = 1, maxInt = 3999) arabic: Int) {
        val romanNumber: String = RomanNumbers().toRoman(arabic)

        assertThat(romanNumber.contains("MMMM")).isFalse()
        assertThat(romanNumber.contains("CCCC")).isFalse()

    }


    @Property(trials = 1000)
    fun `round trip the conversion`(@InRange(minInt = 1, maxInt = 3999) arabic: Int) {
        val romanNumbers = RomanNumbers()
        val roman = romanNumbers.toRoman(arabic)
        val convertedArabic = romanNumbers.toArabic(roman)

        assertThat(convertedArabic).isEqualTo(arabic)
    }

    //convert the numbers
//    9 -> VIIII



    //cannot have the 4 same letters in a row

    //sometimes, letters have intra-order: M is before C
    //if a number appears, after the chain breaks

    //round trip: 1 -> I -> 1; 2 -> II -> 2
    //in roman, no arabic numbers are allowed
}
