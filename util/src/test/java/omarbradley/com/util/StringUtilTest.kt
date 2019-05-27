package omarbradley.com.util

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class StringUtilTest : Spek({

    describe(".splitBar") {

        context("사이사이 마다 '|'가 있는 스트링을 잘라서") {

            val barString = "1|2|3|".splitBar()

            it("리스트로 반환해준다") {
                assertThat(barString, equalTo(listOf("1", "2", "3")))
            }
        }
    }
})
