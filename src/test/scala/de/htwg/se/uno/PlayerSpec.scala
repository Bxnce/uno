package de.htwg.se.uno
package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class PlayerSpec extends AnyWordSpec {
  "Player" should {
    "have name in form of a String" in {
      val p1 = Player("Spieler1")
      p1.name shouldBe ("Spieler1")
    }
    "have a Vector with the Players Cards" in {}
  }
}
