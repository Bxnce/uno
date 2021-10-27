package de.htwg.se.uno

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class FeldSpec extends AnyWordSpec {
  "Feld" should {
    "have a row as String of form '+--'" in {
      row() should be("+--")
    }
  }

}
