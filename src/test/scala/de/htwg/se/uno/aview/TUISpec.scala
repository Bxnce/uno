package de.htwg.se.uno
package aview

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

import model.Game
import model.Game._
import model.Card._
import model.CardLayout._
import controller.Controller
import util._

class TUISpec extends AnyWordSpec {
  "TUI" when {
    val game = Game("Bence", "Timo", 0)
    val controller = Controller(game)
    controller.game.add("P1", Y6) //liegt auf midStack
    controller.game.add("P1", R0)
    controller.game.add("P1", R1)
    controller.game.add("P1", R2)
    controller.game.add("P2", G0)
    controller.game.add("P2", G1)
    controller.game.add("P2", G2)
    controller.next() // p1
    controller.place(0)
    controller.next() // p2n

    val tui = TUI(controller)

    "created with the given parameters " should {
      "have the following values" in {
        controller.toString shouldEqual (
          "Bence\n" +
            "+--+\n" +
            "| 3|\n" +
            "+--+\n" +
            "\n" +
            "+--+\n" +
            "|Y6|\n" +
            "+--+\n" +
            "\n" +
            "+--+\n" +
            "| 3|\n" +
            "+--+\n" +
            "Timo\n"
        )
      }
      "have a method run(String) that checks the output of convertInputString" in {
        tui.run("") shouldBe (tui.printhelp())
      }
      "have a method convertinputString(String) that calls the controller to execute the command" in {
        tui.convertinputString("") shouldBe (tui.ERROR)
        tui.convertinputString("a") shouldBe (tui.ERROR)
        tui.convertinputString("help") shouldBe (tui.SUCCESS)

        tui.convertinputString("n") shouldBe (tui.SUCCESS) //p2s
        tui.convertinputString("n") shouldBe (tui.SUCCESS) //p1n
        tui.convertinputString("n") shouldBe (tui.SUCCESS) //p1

        tui.convertinputString("+") shouldBe (tui.SUCCESS)
        controller.game.p1.karten.size shouldBe (4)

        tui.convertinputString("-") shouldBe (tui.ERROR)
        tui.convertinputString("- 1") shouldBe (tui.SUCCESS)
        controller.game.p1.karten.size shouldBe (3)
        controller.game.midCard.karten(0) shouldBe (R0)

        tui.convertinputString("n") shouldBe (tui.SUCCESS) //p2n
        tui.convertinputString("+") shouldBe (tui.ERROR)
        tui.convertinputString("- 1") shouldBe (tui.ERROR)

        tui.convertinputString("n") shouldBe (tui.SUCCESS) //p2s
        controller.game.currentstate shouldBe (game.p2s)
      }
      "Have a method printhelp() that prints out the help message" in {}
      "override the method update" in {}
    }
  }
}
