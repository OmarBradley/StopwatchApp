import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row
import omarbradley.com.util.date.convertToHHmmssFormat

class TimeFormatterTest : StringSpec({

    "test convertToHHmmssFormat method" {
        forall(
            row(100L, "00:00:00"),
            row(1000L, "00:00:01"),
            row(2001L, "00:00:02"),
            row(60001L, "00:01:00")
        ) { milliseconds, HHmmssText ->
            milliseconds.convertToHHmmssFormat() shouldBe HHmmssText
        }
    }

})
