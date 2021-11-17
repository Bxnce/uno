package de.htwg.se.uno
package aview

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

import model.Game
import model.Card._
import model.CardLayout._
import controller.Controller

class TUISpec extends AnyWordSpec {
  "TUI" when {
    val game = Game("Bence", "Timo", 3)
    val controller = Controller(game)
    controller.game.midCard.karten =
      controller.game.midCard.karten.updated(0, Y6)
    controller.game.p1.karten = controller.game.p1.karten.updated(0, R0)
    controller.game.p1.karten = controller.game.p1.karten.updated(1, R1)
    controller.game.p1.karten = controller.game.p1.karten.updated(2, R2)
    controller.game.p2.karten = controller.game.p2.karten.updated(0, G0)
    controller.game.p2.karten = controller.game.p2.karten.updated(1, G1)
    controller.game.p2.karten = controller.game.p2.karten.updated(2, G2)

    "created with the given parameters " should {
      "have the following values" in {
        val tui = TUI(controller) shouldEqual (
          "\n\nWillkommen zu Uno! Um zur Uebersicht der Befehle zu kommen bitte help eingeben\n\n" +
            "Bence\n" +
            "+--+--+--+\n" +
            "|R0|R1|R2|\n" +
            "+--+--+--+\n" +
            "\n" +
            "+--+\n" +
            "|Y6|\n" +
            "+--+\n" +
            "\n" +
            "+--+--+--+\n" +
            "|G0|G1|G2|\n" +
            "+--+--+--+\n" +
            "Timo\n"
        )
      }
    }
  }
}
