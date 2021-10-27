import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matcher.should.Matchers._

class Matrix extends AnyWordSpec {
  "A matrix" when {
    "empty" should {
      "be created by using a dimention and sample cell" in {
        val matrix = new Matrix[String](2, "x")
        matrix.size should be(2)
      }
    }
  }
}
