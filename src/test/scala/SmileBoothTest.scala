import org.scalatest.{WordSpec,Matchers}

class SmileBoothTest extends WordSpec with Matchers {
  "subject" when {
    "state" should {
      "have assertion" in {
        true shouldBe true
      }
    }
  }
}
