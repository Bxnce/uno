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
    val tui = TUI(controller)

    "created with the given parameters " should {
      "have the following values" in {
        controller.toString shouldEqual (
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
        //vllt einfach einen case new bei dem dann erst ein neues Spiel erstellt wird.
      }
      "have a method run(String) that executes the commands" in {
        tui.convertinputString("") shouldBe (tui.ERROR)
        tui.convertinputString("a") shouldBe (tui.ERROR)
        tui.convertinputString("add p1") shouldBe (tui.ERROR)
        tui.convertinputString("help") shouldBe (tui.SUCCESS)

        tui.convertinputString("add p1 R3") shouldBe (tui.SUCCESS)
        tui.convertinputString("add p2 G3") shouldBe (tui.SUCCESS)
        controller.game.p1.karten.size shouldBe (4)
        controller.game.p2.karten.size shouldBe (4)
        controller.game.p1.karten(3) shouldBe (R3)
        controller.game.p2.karten(3) shouldBe (G3)

        tui.convertinputString("+") shouldBe (tui.SUCCESS)
        controller.game.p1.karten.size shouldBe (5)

        tui.convertinputString("- 1") shouldBe (tui.SUCCESS)
        controller.game.p1.karten.size shouldBe (4)
        controller.game.midCard.karten(0) shouldBe (R0)

        tui.convertinputString("n") shouldBe (tui.SUCCESS)
        controller.game.playerDiff shouldBe (1)
      }
      "Have a method printhelp() that prints out the help message" in {}
      "override th method update" in {}
    }
  }
}
