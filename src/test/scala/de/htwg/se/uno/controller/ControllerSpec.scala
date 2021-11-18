package de.htwg.se.uno
package controller

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

import model.Game
import model.Card._

class ControllerSpec extends AnyWordSpec {
  "Controller" should {
    val game = new Game("p1", "p2", 0)
    game.midCard.karten = game.midCard.karten.updated(0, R0)
    val c = new Controller(game)
    "contain a game" in {
      c.game.p1.karten.size shouldBe (0)
      c.game.p2.karten.size shouldBe (0)
      c.game.midCard.karten.size shouldBe (1)
      c.game.midCard.karten(0) shouldBe (R0)
    }
    "hava a method add(String, String), that calls the add function in game" in {
      c.add("p1", "B0")
      c.add("p4", "G0") shouldEqual ("!!!Ungültige Karte!!!\n")
    }
  }
}
